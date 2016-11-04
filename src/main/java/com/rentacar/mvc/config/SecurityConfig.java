package com.rentacar.mvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Equivalent for XML configuration in /webcontext/Security-config.xml
 * I wrote both XML and Java class for better understanding Spring mixed configurations rules
 */

@ImportResource("classpath:/webcontext/Root-config.xml")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(11);
	}
	
	@Autowired
	public UserDetailsService jdbcDetailsServiceBean;
	
	
	@Override  
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {  
      auth
      	.userDetailsService(jdbcDetailsServiceBean)
      	.passwordEncoder(passwordEncoder());
  }  
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
			authorizeRequests()
				.antMatchers("/welcome", "/cars", "/registration").permitAll()
				.antMatchers("/cars/add").hasRole("ADMIN")
				.antMatchers("/cars/car").access("hasRole('ADMIN') OR hasRole('USER')")
				.and()
			.formLogin()
				.loginPage("/login").permitAll()
				.usernameParameter("email")
				.passwordParameter("password")
				.failureUrl("/loginfailed")
				.and()
			.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login")
				.invalidateHttpSession(true)
				.and()
			.csrf().disable();
	}
	
}
