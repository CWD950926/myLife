package com.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author maqh
 * @date 2019-8-21
 */
//@Profile({"local", "dev"})
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        //name表示名称，description表示描述
        ticketPar.name("token").description("登录校验")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build();//required表示是否必填，
        pars.add(ticketPar.build());//
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("standard")
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(true)
                .forCodeGeneration(false)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .build()//把消息头添加;
                .globalOperationParameters(pars);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 设置页面标题
                .title("使用swagger2构建SCRM后端api接口文档")
                // 设置联系人
                //.contact(new Contact("imooc-Nathanhttp://www.imooc.comscau_zns@163.com"))
                // 描述
                .description("欢迎访问SCRM业务接口文档，这里是描述信息")
                // 定义版本号
                .version("1.0").build();
    }
}