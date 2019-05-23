
package io.msa.apigateway;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.mvc.BasicLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.msa.apigateway.services.ContentIntegrationService;
import io.msa.apigateway.services.ContextIntegrationService;
import io.msa.apigateway.services.OrderIntegrationService;
import io.msa.apigateway.services.ProductIntegrationService;
import io.msa.apigateway.services.ReviewsIntegrationService;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableZuulProxy
@EnableHystrix
@RestController
@EnableOAuth2Sso
@EnableDiscoveryClient
@EnableSwagger2
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Autowired
	ProductIntegrationService productIntegrationService;

	@Autowired
	ReviewsIntegrationService reviewsIntegrationService;

	@Autowired
	ContentIntegrationService contentIntegrationService;

	@Autowired
	ContextIntegrationService contextIntegrationService;

	@Autowired
	OrderIntegrationService orderIntegrationService;

	@RequestMapping("/")
	public HttpEntity<RootResource> getRootResource() {
		RootResource resource = new RootResource();
		String baseUri = BasicLinkBuilder.linkToCurrentMapping().toString();

		Link productServiceLink = new Link(new UriTemplate(baseUri + "/product"), RootResource.LINK_NAME_PRODUCT);
		resource.add(productServiceLink);

		Link reviewServiceLink = new Link(new UriTemplate(baseUri + "/review"), RootResource.LINK_NAME_REVIEW);
		resource.add(reviewServiceLink);

		Link orderServiceLink = new Link(new UriTemplate(baseUri + "/order"), RootResource.LINK_NAME_ORDERS);
		resource.add(orderServiceLink);

		Link contentServiceLink = new Link(new UriTemplate(baseUri + "/content"), RootResource.LINK_NAME_CONTENT);
		resource.add(contentServiceLink);

		Link contextServiceLink = new Link(new UriTemplate(baseUri + "/context"), RootResource.LINK_NAME_CONTEXT);
		resource.add(contextServiceLink);


		return new ResponseEntity<>(resource, HttpStatus.OK);
	}

	@Configuration
	@EnableResourceServer
	static class ResourceServer extends ResourceServerConfigurerAdapter {

		@Override
		public void configure(HttpSecurity http) throws Exception {
			http.requestMatchers().and().authorizeRequests()
			
			  
					.antMatchers(HttpMethod.GET, "X/product/**").authenticated()
					.antMatchers(HttpMethod.GET, "X/content/**").authenticated()
					.antMatchers(HttpMethod.GET, "X/context/**").authenticated()
					.antMatchers(HttpMethod.GET, "X/review/**").authenticated()
					.antMatchers(HttpMethod.GET, "X/order/**").authenticated()
					
		
					.antMatchers(HttpMethod.POST, "X/product/**").authenticated()
					.antMatchers(HttpMethod.POST, "X/content/**").authenticated()
					.antMatchers(HttpMethod.POST, "X/context/**").authenticated()
					.antMatchers(HttpMethod.POST, "X/review/**").authenticated()
					.antMatchers(HttpMethod.POST, "X/order/**").authenticated();


		}
	}
	@Bean
	UiConfiguration uiConfig() {
		return new UiConfiguration("validatorUrl", "list", "alpha", "schema",
				UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS, false, true, 60000L);
	}
	
}
