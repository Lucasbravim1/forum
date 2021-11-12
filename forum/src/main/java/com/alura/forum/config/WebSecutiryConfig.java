
package com.alura.forum.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.alura.forum.service.UserAutenticationService;

@Configuration

@EnableWebSecurity
public class WebSecutiryConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserAutenticationService userAutenticationService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		auth.userDetailsService(userAutenticationService).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/topic/*").permitAll()
				.antMatchers(HttpMethod.POST, "/topic/*").permitAll().antMatchers(HttpMethod.POST, "/user/*")
				.permitAll().antMatchers(HttpMethod.POST, "/login").permitAll().anyRequest().authenticated().and()
				.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
	}

}
