package edu.fzu.lbs.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket publicAPI() {
        return new Docket(DocumentationType.SWAGGER_2)
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(false)
                .pathMapping("/")
                .select()
                .build()
                .apiInfo(crateApiInfo());
    }

    private ApiInfo crateApiInfo() {
        return new ApiInfoBuilder()
                .title("LBS应用RESTful API文档")
                .contact(new Contact("ZengJinRong", "https://github.com/ZengJinRong", ""))
                .version("0.1.3")
                .description("LBS行车行车应用服务端API文档")
                .build();
    }


}
