package com.gary.demo.crud.config;

import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static springfox.documentation.builders.PathSelectors.regex;
import static springfox.documentation.builders.RequestHandlerSelectors.withClassAnnotation;
import static springfox.documentation.builders.RequestHandlerSelectors.withMethodAnnotation;

@Configuration
@EnableSwagger2
public class SwaggerConfig {


    @Value("${app.client.id}")
    private String clientId;
    @Value("${app.client.secret}")
    private String clientSecret;

    @Value("${host.full.dns.auth.link}")
    private String authLink;


    @Autowired
    private AppProperties appProperties;

    @Bean
    public Docket mainApi() {

        return getCommonDocketBuilder(appProperties.getSwaggerProperties().getGroup()).build()
                //.securitySchemes(Collections.singletonList(securitySchema()))
                .securitySchemes(Lists.newArrayList(apiKey()))
                .useDefaultResponseMessages(false); // Do NOT use default response messages (otherwise, it adds 201 and other responses to Swagger docs)
    }


    private ApiSelectorBuilder getCommonDocketBuilder(String groupName) {

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(groupName)
                .apiInfo(apiInfo())
                .select()
                .apis(withClassAnnotation(Api.class))
                .apis(withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any());
    }


   private ApiKey apiKey() {
        return new ApiKey("apiKey", "Authorization", "header");
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
