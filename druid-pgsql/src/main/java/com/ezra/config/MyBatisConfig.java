package com.ezra.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {"com.ezra.mapper","com.ezra.rule"})
public class MyBatisConfig {

    @Value("${spring.datasource.druid.filters}")
    private String filters;

    @ConfigurationProperties(prefix = "spring.datasource.newdb")
    @Bean(name = "newdb")
    @Primary
    public DataSource newdb() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setFilters(filters);
        return druidDataSource;
    }

    @ConfigurationProperties(prefix = "spring.datasource.historydb")
    @Bean(name = "historydb")
    public DataSource historydb() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setFilters(filters);
        return druidDataSource;
    }


    @Bean(name = "dynamicDataSource")
    public DynamicDataSource dynamicDataSource(@Qualifier("newdb") DataSource newdb,@Qualifier("historydb") DataSource historydb){

        DynamicDataSource dynamicRoutingDataSource = new DynamicDataSource();
        //配置多数据源
        Map<Object, Object> dataSourceMap = new HashMap<>(2);
        dataSourceMap.put("newdb", newdb);
        dataSourceMap.put("historydb", historydb);
        // 将 master 和 slave 数据源作为指定的数据源
        dynamicRoutingDataSource.setTargetDataSources(dataSourceMap);
        return dynamicRoutingDataSource;
    }

    @Bean
    @ConfigurationProperties("mybatis.configuration")
    public org.apache.ibatis.session.Configuration config(){
        return new org.apache.ibatis.session.Configuration();
    }


    @Bean
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource") DynamicDataSource dynamicDataSource,
                                               org.apache.ibatis.session.Configuration config) throws Exception {
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(dynamicDataSource);
        fb.setConfiguration(config);
        //fb.setDataSource(this.dataSource(myTestDbDataSource, myTestDb2DataSource));// 指定数据源(这个必须有，否则报错)
        // 下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
        fb.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*.xml"));
        return fb.getObject();
    }

    /**
     * 配置事务管理器
     */
    @Bean
    public DataSourceTransactionManager transactionManager(@Qualifier("dynamicDataSource")DynamicDataSource dataSource) throws Exception {
        return new DataSourceTransactionManager(dataSource);
    }

}
