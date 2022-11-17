package com.adnan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	private ApiKey apiKey() {
		return new ApiKey("JWT", "Authorization", "Header");
	}

	private List<SecurityReference> defaultAuth() { 
	    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything"); 
	    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1]; 
	    authorizationScopes[0] = authorizationScope; 
	    return Arrays.asList(new SecurityReference("JWT", authorizationScopes)); 
	}
	
	private SecurityContext securityContext() { 
	    return SecurityContext.builder().securityReferences(defaultAuth()).build(); 
	} 
	
	private ApiInfo apiInfo() {
	    return new ApiInfo(
				"E- College Information System",
				"Information System for Students",
				"1.0",
				"",
				new Contact("Mohammad Adnan", "", "adnanjhdelhi@gmail.com"),
				"",
				"",
	      Collections.emptyList());
	}
	
	@Bean
	public Docket api() {
	    return new Docket(DocumentationType.SWAGGER_2)
	      .apiInfo(apiInfo())
	      .securityContexts(Arrays.asList(securityContext()))
	      .securitySchemes(Arrays.asList(apiKey()))
	      .select()
	      .apis(RequestHandlerSelectors.basePackage("com.adnan.controller")).paths(PathSelectors.any())
	      .paths(PathSelectors.any())
	      .build();
	}

}
