package com.ezra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.elasticsearch.config.ElasticsearchConfigurationSupport;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchCustomConversions;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Elasticsearch LocalDateTime的支持配置
 */
@Configuration
public class ElasticsearchConfiguration extends ElasticsearchConfigurationSupport {
 
  @Bean
  @Override
  public ElasticsearchCustomConversions elasticsearchCustomConversions() {
    List<Converter> converters= new ArrayList<>();
    //从ES中获取数据 String to LocalDateTime yyyy-MM-dd HH:mm:ss
    converters.add(StringToLocalDateTimeConverter.INSTANCE);
    //从ES中获取数据 Long to LocalDateTime  处理历史遗留数据，存储为时间戳的
    converters.add(LongToLocalDateTimeConverter.INSTANCE);
    //数据写入ES Date to LocalDateTime  写入时date转成localdatetime，可以删除的配置，目前都使用LocalDateTime
    converters.add(DateToLocalDateTimeConverter.INSTANCE);
    //数据写入ES LocalDateTime to String yyyy-MM-dd HH:mm:ss
    converters.add(LocalDateTimeToString.INSTANCE);
    return new ElasticsearchCustomConversions(converters);
  }
 
  //保存类型为long类型
  @ReadingConverter
  enum LongToLocalDateTimeConverter implements Converter<Long, LocalDateTime> {
 
    INSTANCE;
 
    @Override
    public java.time.LocalDateTime convert(Long source) {
      return Instant.ofEpochMilli(source).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
 
  }

  //格式化后保存结果为String类型
  @ReadingConverter
  enum StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {
 
    INSTANCE;
 
    @Override
    public java.time.LocalDateTime convert(String source) {
      DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
      return LocalDateTime.parse(source,df);
    }
 
  }
 
  @WritingConverter
  enum DateToLocalDateTimeConverter implements Converter<Date, LocalDateTime> {
 
    INSTANCE;
 
    @Override
    public LocalDateTime convert(Date date) {
      Instant instant = date.toInstant();
      return instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
  }

  @WritingConverter
  enum LocalDateTimeToString implements Converter<LocalDateTime, String> {

    INSTANCE;
    @Override
    public String convert(LocalDateTime localDateTime) {
      DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
      return df.format(localDateTime);
    }
  }
}