package com.ezra.lua;

import com.ezra.Application;
import com.ezra.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Collections;

@Slf4j
@Aspect
@Configuration
public class LimitAspect {


    @Autowired
    private RedisTemplate  redisTemplate;
    @Autowired
    private DefaultRedisScript<Number> redisScript;


    @Around("execution(* com.ezra.controller ..*(..) )")
    public Object interceptor(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Class<?> declaringClass = method.getDeclaringClass();
        RateLimit annotation = method.getAnnotation(RateLimit.class);
        String key = null;
        if (annotation !=null) {
            int count = annotation.count();
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String ipAddress = getIpAddr(request);
            StringBuffer keyBuffer = new StringBuffer();
            keyBuffer.append(ipAddress).append("-")
                    .append(declaringClass.getName()).append("-")
                    .append(method.getName()).append("-")
                    .append(annotation.key());
            key = keyBuffer.toString();
            Number number = (Number) redisTemplate.execute(redisScript, Collections.singletonList(key), count, annotation.time());
            if (number != null && number.intValue()!=0&&number.intValue() <= count) {
                //还没有达到限制，放行
                log.info("限流时间段内第{}次访问",number.intValue());
                return joinPoint.proceed();
            }
        }else {
            //没有限流的方法，放行
            return joinPoint.proceed();
        }

        //达到限流次数
        Long expire =  redisTemplate.getExpire("rate:limit:"+key);
        String message = expire >0 ? String.format("已经到设置限流次数,请%d秒后再试",expire) : "已经到设置限流次数";
        return Result.fail(message);
    }

    /**
     * 获取请求方的ip地址
     * @param request
     * @return
     */
    private String getIpAddr(HttpServletRequest request) {

        String ipAddress = null;
        ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress==null || ipAddress.length()==0||"unknown".equals(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress==null || ipAddress.length()==0||"unknown".equals(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress==null || ipAddress.length()==0||"unknown".equals(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }

        if (ipAddress!=null && ipAddress.length() >15) {
            if (ipAddress.indexOf(",")>0) {
                ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }


}
