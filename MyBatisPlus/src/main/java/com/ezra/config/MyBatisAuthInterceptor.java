package com.ezra.config;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;

public class MyBatisAuthInterceptor implements Interceptor {






    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        //要执行的语句对象
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];

        //原SQL
        String sql = mappedStatement.getBoundSql(invocation.getArgs()[1]).getSql();

        //放行
        invocation.proceed();
        return null;
    }
}
