
package com.ezra.config;

import com.ezra.constant.SystemConstant;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.PackageVersion;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * java 8 时间默认序列化
 *
 * @author EzraWen
 */
public class JavaTimeModule extends SimpleModule {

    public JavaTimeModule() {
        super(PackageVersion.VERSION);
        this.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(SystemConstant.DATETIME_FORMAT));
        this.addDeserializer(LocalDate.class, new LocalDateDeserializer(SystemConstant.DATE_FORMAT));
        this.addDeserializer(LocalTime.class, new LocalTimeDeserializer(SystemConstant.TIME_FORMAT));
        this.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(SystemConstant.DATETIME_FORMAT));
        this.addSerializer(LocalDate.class, new LocalDateSerializer(SystemConstant.DATE_FORMAT));
        this.addSerializer(LocalTime.class, new LocalTimeSerializer(SystemConstant.TIME_FORMAT));

        //Long转string
        this.addSerializer(BigInteger.class, ToStringSerializer.instance);
        this.addSerializer(Long.class, ToStringSerializer.instance);
        this.addSerializer(Long.TYPE, ToStringSerializer.instance);
    }

}
