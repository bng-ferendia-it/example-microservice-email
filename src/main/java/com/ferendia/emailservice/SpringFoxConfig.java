package com.ferendia.emailservice;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SpringFoxConfig implements WebMvcConfigurer{
	
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.bngferendia.emailservice")).paths(PathSelectors.any()).build()
				.apiInfo(apiAppInfo()).securitySchemes(Arrays.asList(apiKey()));

	}
	private ApiKey apiKey() {
		return new ApiKey("Bearer", "Authorization", "header");
	}

	private ApiInfo apiAppInfo() {

		return new ApiInfoBuilder().title("Micro Service Email").description("Micro Service Email Description").license("")
				.licenseUrl("http://exemple.com").version("1.0.0").build();
	}
	
	@Override
	  public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.
	        addResourceHandler("/swagger-ui/**")
	        .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
	        .resourceChain(false);
	  }

	  @Override
	  public void addViewControllers(ViewControllerRegistry registry) {
	    registry.addViewController("/swagger-ui/")
	        .setViewName("forward:" + "/swagger-ui/index.html");
	  }
}
