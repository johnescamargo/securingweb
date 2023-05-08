package com.example.securingweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Autowired
	private UserRepository repository;
	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//	    return new BCryptPasswordEncoder();
//	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf().disable()
					.authorizeHttpRequests()
					.requestMatchers("/","/login", "/home", "/css/**")
					.permitAll()
					.and()
					.authorizeHttpRequests()
					.requestMatchers("/hello")
					.authenticated()
					.anyRequest()
					.permitAll()
					.and()
					.formLogin(form -> form 
							.loginPage("/login")
							.defaultSuccessUrl("/hello")
							.loginProcessingUrl("/login")
							.failureUrl("/login")
							.permitAll()
							)
					.logout((logout) -> logout.permitAll());
							

		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user = User.withDefaultPasswordEncoder().username("user").password("123").roles("USER").build();

		return new InMemoryUserDetailsManager(user);
	}
	
	public DbUser getUser (String name) {
		DbUser user = new DbUser();
		user = repository.findByUname(name);
		
		return user;
	}

}