package io.msa.content;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.bind.annotation.CrossOrigin;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@CrossOrigin
@SpringBootApplication
@EnableJpaRepositories
@EnableDiscoveryClient
@EnableSwagger2
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Configuration
	@EnableResourceServer
	static class ResourceServer extends ResourceServerConfigurerAdapter {

		@Override
		public void configure(HttpSecurity http) throws Exception {
			http.requestMatchers().and().authorizeRequests().antMatchers(HttpMethod.POST, "X/content/**")
					.access("#oauth2.hasScope('read_content') and (hasRole('ROLE_ADMIN') or hasRole('ROLE_USER'))")
					.antMatchers(HttpMethod.GET, "X/content/**")
					.access("#oauth2.hasScope('read_content') and (hasRole('ROLE_ADMIN') or hasRole('ROLE_USER'))")
					.antMatchers(HttpMethod.POST, "X/**")
					.access("#oauth2.hasScope('write_content') and hasRole('ROLE_ADMIN')")
					.antMatchers(HttpMethod.PUT, "X/**")
					.access("#oauth2.hasScope('write_content') and hasRole('ROLE_ADMIN')")
					.antMatchers(HttpMethod.DELETE, "X/**")
					.access("#oauth2.hasScope('write_content') and hasRole('ROLE_ADMIN')");
		}
	}

}
