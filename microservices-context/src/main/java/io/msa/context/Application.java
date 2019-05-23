package io.msa.context;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@SpringBootApplication
@EnableJpaRepositories
@EnableDiscoveryClient
public class Application extends RepositoryRestMvcConfiguration {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Configuration
	@EnableResourceServer
	static class ResourceServer extends ResourceServerConfigurerAdapter {

		@Override
		public void configure(HttpSecurity http) throws Exception {
			http.requestMatchers().and().authorizeRequests().antMatchers(HttpMethod.GET, "X/context/**")
					.access("#oauth2.hasScope('read_context') and (hasRole('ROLE_ADMIN') or hasRole('ROLE_USER'))")
					.antMatchers(HttpMethod.POST, "X/context**")
					.access("#oauth2.hasScope('write_context') and hasRole('ROLE_ADMIN')")
					.antMatchers(HttpMethod.PUT, "X/context**")
					.access("#oauth2.hasScope('write_context') and hasRole('ROLE_ADMIN')")
					.antMatchers(HttpMethod.DELETE, "X/context**")
					.access("#oauth2.hasScope('write_reviews') and hasRole('ROLE_ADMIN')");

		}
	}

}
