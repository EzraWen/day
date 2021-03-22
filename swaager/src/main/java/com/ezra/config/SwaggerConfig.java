package com.ezra.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by wxwujinghong on 2019/10/30.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    public static final String SWAGGER_SCAN_BASE_PACKAGE = "com.gf.apiservice.controller";
    public static final String VERSION = "1.0.0";

    @Value("${swagger.enable:true}")
    private boolean enableSwagger;

    @Bean
    public Docket createRestApi() {

//        ParameterBuilder ticketPar = new ParameterBuilder();
//        List<Parameter> pars = new ArrayList<Parameter>();
//        ticketPar.name("Cookie").description("token")
//                .modelRef(new ModelRef("string")).parameterType("header")
//                .required(false).build(); //header中的cookie参数非必填，传空也可以
//        pars.add(ticketPar.build());    //根据每个方法名也知道当前方法在设置什么参数

        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enableSwagger)
                .select()
                .apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_BASE_PACKAGE))//api接口包扫描路径
                .paths(PathSelectors.any())//可以根据url路径设置哪些请求加入文档，忽略哪些请// 求
                .build()
//                .globalOperationParameters(pars)
                .apiInfo(apiInfo());
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("api后台接口")//设置文档的标题
                .description("供页面调用接口")//设置文档的描述->1.Overview
                .version(VERSION)//设置文档的版本信息-> 1.1 Version information
//                .contact(new Contact("ABC Boot", "http://www.abc.comt", ""))//设置文档的联系方式->1.2 Contact information
//                .termsOfServiceUrl("www.abc.com")//设置文档的License信息->1.3 License information
                .build();
    }
}
