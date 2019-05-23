package io.msa.order.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(getApiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("io.msa.order.controller")).paths(PathSelectors.any())
				.build();

	}

	private ApiInfo getApiInfo() {
		Contact contact = new Contact("HARI KISHORE", "mhari.drop@gmail.com", null);
		return new ApiInfoBuilder().title("Spring Boot REST API").description("SpringBoot With RESTful Using Account")
				.version("1.0.0").license("Apache 2.0").contact(contact).build();
	}

}
