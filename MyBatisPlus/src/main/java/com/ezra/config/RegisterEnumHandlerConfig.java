package com.ezra.config;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.ezra.constant.BaseEnum;
import com.ezra.util.ClassUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class RegisterEnumHandlerConfig implements ConfigurationCustomizer {

    @Override
    public void customize(Configuration configuration) {
        log.debug("ConfigurationCustomizer init....");
        TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();

        try {
            final List<Class<?>> allAssignedClass = ClassUtil.getAllAssignedClass(BaseEnum.class);
            allAssignedClass.forEach((clazz) -> typeHandlerRegistry.register(clazz, GeneralTypeHandler.class));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}