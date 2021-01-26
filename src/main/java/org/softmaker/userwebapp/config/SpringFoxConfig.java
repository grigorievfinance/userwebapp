package org.softmaker.userwebapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.server.authentication.HttpBasicServerAuthenticationEntryPoint;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@Configuration
@EnableSwagger2
public class SpringFoxConfig  extends WebMvcConfigurationSupport {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .ignoredParameterTypes(AuthenticationPrincipal.class)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/rest/**"))
                .build()
                .apiInfo(metaData())
                .securitySchemes(List.of(securityScheme()))
                .securityContexts(List.of(securityContext()));
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("REST API documentation")
                .description("Demo Java Enterprise Web Application")
                .version("1.0")
                .license("License GPLv3")
                .licenseUrl("https://www.gnu.org/licenses/gpl-3.0.en.html")
                .build();
    }

    private SecurityScheme securityScheme() {
        return new BasicAuth("basicAuth");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(List.of(new SecurityReference("basicAuth", new AuthorizationScope[0])))
                .build();
    }
}
