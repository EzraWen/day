package com.ezra.config;

import com.ezra.entity.BaseApplicationRuleMultiSource;
import com.ezra.rule.BaseApplicationRuleMapper;
import com.ezra.util.DBSelectUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@Aspect
@Component
@Slf4j
public class DataSourceAspect {

    @Autowired
    DBHelper dbHelper;
    @Autowired
    private BaseApplicationRuleMapper baseApplicationRuleMapper;

    /**
     * mapper下的所有方法
     */
    @Pointcut("execution(* com.ezra.mapper.*.*(..))")
    public void JoinPoint() {
    }

    //使用定义切点表达式的方法进行点表达式的引入
    @Before("JoinPoint()")
    public void setDataSourceKey(JoinPoint point) throws Exception {
        //更改为获取配置表数据 使用工具中的方法获取使用库
        Object[] methodArgs = point.getArgs();
        String dbType = null;
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method method = methodSignature.getMethod();
        Class<?> declaringClass = method.getDeclaringClass();
        String prefixCondition = declaringClass.getSimpleName() + "." + method.getName();
        dbHelper.setDBType(DBHelper.DB_TYPE_NEW);
        BaseApplicationRuleMultiSource rule = baseApplicationRuleMapper.getByClassMethod(prefixCondition);

        if (rule == null) {
            //没有配置查询规则 查新库
            dbHelper.setDBType(dbType = DBHelper.DB_TYPE_NEW);
            log.info("查询库:{}", dbType);
            return;
        }

        //判断条件
        String conditions = rule.getConditions();
        //工具类判断规则
        String source = rule.getSource();
        Object value = null;
        if (conditions.indexOf("\\|") < 0) {
            dbHelper.setDBType(dbType = DBHelper.DB_TYPE_NEW);
        } else {
            String valueMethod = conditions.split("\\|")[1];
            //vo get方式
            if (valueMethod.indexOf(".") > 0) {
                String[] split = valueMethod.split("\\.");
                String objectType = split[0];
                String getMethod = split[1];
                Parameter[] parameters = method.getParameters();
                for (int i = 0; i < parameters.length; i++) {
                    Parameter parameter = parameters[i];
                    if (parameter.getName().equals(objectType)) {
                        //实体
                        Object entity = methodArgs[i];
                        String typeName = parameter.getParameterizedType().getTypeName();
                        Class<?> entityClass = Class.forName(typeName);
                        Method classMethod = entityClass.getMethod(getMethod);
                        value = classMethod.invoke(entity);
                        break;
                    }
                }
            } else {
                //属性名方式
                Parameter[] parameters = method.getParameters();
                for (int i = 0; i < parameters.length; i++) {
                    if (parameters[i].getName().equals(valueMethod)) {
                        value = methodArgs[i];
                    }
                }
            }

        }


        dbHelper.setDBType(dbType = DBSelectUtil.getByRule(source,value));
        log.info("查询库:{}", dbType);
    }


    @After("JoinPoint()")
    public void removeDataSourceKey() {
        dbHelper.clearDBType();
    }
}