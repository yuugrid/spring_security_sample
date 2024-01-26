package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	http.formLogin()
    		.loginProcessingUrl("/login")
    		.loginPage("/loginForm")
    		.usernameParameter("email")
    		.passwordParameter("password")
    		.defaultSuccessUrl("/home", true)
    		.failureUrl("/loginForm?error");
    	
    	http.logout()
    		.logoutUrl("/logout")
    		.logoutSuccessUrl("/loginForm");
    	
        http.authorizeRequests()
        	.antMatchers("/loginForm").permitAll()
        	.antMatchers("/admin").hasAuthority("ADMIN")
        	.anyRequest().authenticated();		
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}