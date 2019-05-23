package io.msa.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.bind.annotation.CrossOrigin;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@CrossOrigin
@SpringBootApplication
@EnableJpaRepositories
@EnableDiscoveryClient
@EnableSwagger2
public class Application

{
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	public void configure(HttpSecurity http) throws Exception {
		http.requestMatchers().and().authorizeRequests().antMatchers(HttpMethod.GET, "X/catalog/**")
				.access("#oauth2.hasScope('read_catalog') and (hasRole('ROLE_ADMIN') or hasRole('ROLE_USER'))")
				.antMatchers(HttpMethod.GET, "/Xcatalog/**")
				.access("#oauth2.hasScope('read_catalog') and (hasRole('ROLE_ADMIN') or hasRole('ROLE_USER'))")
				.antMatchers(HttpMethod.POST, "/X**")
				.access("#oauth2.hasScope('write_catalog') and hasRole('ROLE_ADMIN')")
				.antMatchers(HttpMethod.PUT, "/**")
				.access("#oauth2.hasScope('write_catalog') and hasRole('ROLE_ADMIN')")
				.antMatchers(HttpMethod.DELETE, "/**")
				.access("#oauth2.hasScope('write_catalog') and hasRole('ROLE_ADMIN')");

	}

}
