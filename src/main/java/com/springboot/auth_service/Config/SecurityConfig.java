package com.springboot.auth_service.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.springboot.auth_service.Security.JwtRequestFilter;
import com.springboot.auth_service.Service.MyUserDetailsService;


@Configuration
@EnableWebSecurity
public class SecurityConfig{
	
	  @Autowired
	    private MyUserDetailsService myUserDetailsService;

	    @Autowired
	    private JwtRequestFilter jwtRequestFilter;
	    
	    protected void configure(AuthenticationManagerBuilder auth)	throws Exception{
	    	auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
	    }
	    
	    @Bean
	    public PasswordEncoder passwordEncoder(){
	    	return new BCryptPasswordEncoder();
	    }
	    
	    @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
	        return authenticationConfiguration.getAuthenticationManager();
	    }

	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    	http.csrf().disable()
            .authorizeRequests(authorize -> authorize
                .antMatchers("/api/auth/login", "/api/auth/register", "/api/auth/verify-otp").permitAll()
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            );

	        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

	        return http.build();
	    }
	    
	    

}
