package io.msa.authserver;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.SessionAttributes;

import io.msa.authserver.oauth.CustomUserDetails;
import io.msa.authserver.repositories.UserRepository;
import io.msa.authserver.service.UserService;

@SpringBootApplication
@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableZuulProxy
@EnableHystrix
@Controller
@SessionAttributes("authorizationRequest")
@EnableDiscoveryClient
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	DataSource dataSource;

	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository repository,
			UserService service) throws Exception {
		builder.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder)
				.usersByUsernameQuery("SELECT username,password,1 AS enabled FROM user WHERE username=?")
				.authoritiesByUsernameQuery(
						"SELECT u.username AS username, r.name AS role FROM user u ,role r, user_roles ur "
								+ " WHERE u.id=ur.user_id AND r.id=ur.roles_id AND u.username=?");
		builder.userDetailsService(userDetailsService(repository)).passwordEncoder(passwordEncoder);

	}

	private UserDetailsService userDetailsService(final UserRepository repository) {
		return username -> new CustomUserDetails(repository.findByUsername(username));
	}

}
