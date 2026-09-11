package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider(UserDetailsService userDetailsService,
			BCryptPasswordEncoder passwordEncoder) {

		DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);

		provider.setPasswordEncoder(passwordEncoder);

		return provider;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable())

				.authorizeHttpRequests(auth -> auth

						.requestMatchers("/user/**").hasRole("USER")

						.requestMatchers("/**")
						.permitAll()

						.anyRequest().permitAll())

				.formLogin(form -> form.loginPage("/signin").loginProcessingUrl("/userLogin")
						.defaultSuccessUrl("/user/addNotes", true).permitAll());

		return http.build();
	}
}