package com.rest.demo.config;

import static com.rest.demo.utils.Roles.ROLE_ADMIN;
import static com.rest.demo.utils.Roles.ROLE_USER;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private BasicAuthenticationEntryPoint authenticationEntryPoint;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.cors().configurationSource(corsConfigurationSource())
		.and()
		.authorizeRequests()
		.antMatchers("/auth/**").permitAll()
		.antMatchers("/producto", "/producto/**").hasAnyRole(ROLE_ADMIN, ROLE_USER)
		.antMatchers("/categoria", "/categoria/**").hasAnyRole(ROLE_ADMIN, ROLE_USER)
		.anyRequest().authenticated()
		.and()
		.httpBasic().authenticationEntryPoint(authenticationEntryPoint)
		.and()
		.formLogin().defaultSuccessUrl("/browser", true) 
		.and()
		.csrf().ignoringAntMatchers("/**")
		.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()); 
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
		auth.userDetailsService(inMemoryUserDetailsManager());
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/h2-console/**");
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
	
	@Bean
	public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
		return new InMemoryUserDetailsManager(
			User.withUsername("test")
			.password(passwordEncoder().encode("secret"))
			.roles(ROLE_ADMIN)
			.build()
		);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder(11);
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(Arrays.asList("*"));
		config.setAllowedMethods(Arrays.asList("*"));
		config.setAllowedHeaders(Arrays.asList("*"));
		config.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
    }
	
}
