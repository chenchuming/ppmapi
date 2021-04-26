package io.swagger.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-04-25T21:01:43.979Z[GMT]")
@Configuration
public class SwaggerDocumentationConfig {

    @Bean
    public Docket customImplementation(){
        return new Docket(DocumentationType.OAS_30)
                .select()
                    .apis(RequestHandlerSelectors.basePackage("io.swagger.api"))
                    .build()
                .directModelSubstitute(org.threeten.bp.LocalDate.class, java.sql.Date.class)
                .directModelSubstitute(org.threeten.bp.OffsetDateTime.class, java.util.Date.class)
                .apiInfo(apiInfo());
    }

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("RESTful API")
            .description("No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)")
            .license("License - CC BY 4.0")
            .licenseUrl("http://creativecommons.org/licenses/by/4.0/")
            .termsOfServiceUrl("")
            .version("1.0.0")
            .contact(new Contact("","", "chenc@udel.edu"))
            .build();
    }

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
            .info(new Info()
                .title("RESTful API")
                .description("No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)")
                .termsOfService("")
                .version("1.0.0")
                .license(new License()
                    .name("License - CC BY 4.0")
                    .url("http://creativecommons.org/licenses/by/4.0/"))
                .contact(new io.swagger.v3.oas.models.info.Contact()
                    .email("chenc@udel.edu")));
    }

}
