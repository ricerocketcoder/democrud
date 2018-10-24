package com.gary.demo.crud.config;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;
import static springfox.documentation.builders.RequestHandlerSelectors.withClassAnnotation;
import static springfox.documentation.builders.RequestHandlerSelectors.withMethodAnnotation;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String PATH = ".*db.*";

    @Autowired
    private AppProperties appProperties;

    @Bean
    public Docket mainApi() {
        return getCommonDocketBuilder(appProperties.getSwaggerProperties().getGroup(), PATH).build()
                .useDefaultResponseMessages(false); // Do NOT use default response messages (otherwise, it adds 201 and other responses to Swagger docs)
    }


    private ApiSelectorBuilder getCommonDocketBuilder(String groupName, String subPath) {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(groupName)
                .apiInfo(apiInfo())
                .select()
                .apis(withClassAnnotation(Api.class))
                .apis(withMethodAnnotation(ApiOperation.class))
                .paths(regex(PATH));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(appProperties.getSwaggerProperties().getDocsTitle())
                .description(appProperties.getSwaggerProperties().getDocsDescription())
                .termsOfServiceUrl(appProperties.getSwaggerProperties().getSupportUrl())
                .version(appProperties.getSwaggerProperties().getApiVersion())
                .contact(getContact())
                .build();
    }

    private Contact getContact() {
        return new Contact("Gary Ma", appProperties.getSwaggerProperties().getSupportUrl(), appProperties.getSwaggerProperties().getSupportEmail());
    }
}
