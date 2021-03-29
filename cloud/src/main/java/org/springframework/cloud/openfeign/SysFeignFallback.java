package org.springframework.cloud.openfeign;

import com.ezra.response.MsgCode;
import com.ezra.response.Result;
import com.ezra.util.JsonUtil;
import com.ezra.util.StringUtil;
import com.ezra.util.ToolUtil;
import feign.FeignException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.lang.Nullable;

import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * fallback 代理处理
 */
@Slf4j
@AllArgsConstructor
public class SysFeignFallback<T> implements MethodInterceptor {
    private final Class<T> targetType;
    private final String targetName;
    private final Throwable cause;

    @Nullable
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Class<?> returnType = method.getReturnType();
        if (Result.class != returnType) return null;
        log.error("Feign调用服务出错！ cause detailMessage={}", cause.getMessage());
        if (cause instanceof FeignException) {
            FeignException exception = (FeignException) cause;
            byte[] content = exception.content();
            String str = StringUtil.str(content, StandardCharsets.UTF_8);
            log.error("org.springframework.cloud.openfeign.SysFeignFallback:[{}.{}] serviceId:[{}] message:[{}]", targetType.getName(), method.getName(), targetName, str);
            if (str != null) {
                Result result = JsonUtil.parse(str, Result.class);
                if (ToolUtil.isNotEmpty(result.getCode())) {
                    return result;
                }
            }
        }
        return Result.builder().code(MsgCode.CODE_BUSINESS_FAIL)
                .msg(MsgCode.MSG_BUSINESS_FAIL).build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        org.springframework.cloud.openfeign.SysFeignFallback<?> that = (org.springframework.cloud.openfeign.SysFeignFallback<?>) o;
        return targetType.equals(that.targetType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(targetType);
    }
}
