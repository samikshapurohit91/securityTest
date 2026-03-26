package com.example.securityTest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.securityTest.exception.CustomAuthenticationEntryPoint;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableMethodSecurity
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
//	@Autowired
//	private JwtFilter jwtFilter;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private CustomAuthenticationEntryPoint entryPoint;

	
	private final JwtFilter jwtFilter;

	public SecurityConfig(JwtFilter jwtFilter) {
	    this.jwtFilter = jwtFilter;
	}
	
	 @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

//	    @Bean
//	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
////	        http.csrf().disable()
////	            .authorizeHttpRequests(auth -> auth
////	                .requestMatchers("/auth/**").permitAll()
////	                .requestMatchers("/admin/**").hasRole("ADMIN")
////	                .requestMatchers("/user/**").hasRole("USER")
////	                .anyRequest().authenticated()
////	            )
////	            .httpBasic();
////
////	        return http.build();
////	    }
//	    	
//	        http
//	        .csrf(csrf -> csrf.disable())
//	        .authorizeHttpRequests(auth -> auth
//	            .requestMatchers("/auth/**").permitAll()
//	            .requestMatchers("/admin/**").hasRole("ADMIN")
//	            .requestMatchers("/user/**").hasRole("USER")
//	            .anyRequest().authenticated()
//	        )
//	        .httpBasic(httpBasic -> {});   // ✅ FIXED
//
//	    return http.build();
//
//	    }
//}
	 
//	 @Bean
//	 public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//	     http
//	         .csrf(csrf -> csrf.disable())
//	         .authorizeHttpRequests(auth -> auth
//	             .requestMatchers("/auth/**").permitAll()
//	             .requestMatchers("/admin/**").hasRole("ADMIN")
//	             .requestMatchers("/user/**").hasRole("USER")
//	             .anyRequest().authenticated()
//	         )
//	         .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) // 🔥 IMPORTANT
//	         .httpBasic(withDefaults());
//
//	     return http.build();
//	 }
//}
	 
	 @Bean
	 public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

	     http
	         .csrf(csrf -> csrf.disable())
	         .exceptionHandling(ex -> ex
	                 .authenticationEntryPoint(entryPoint)
	             )

	         .authorizeHttpRequests(auth -> auth
	             .requestMatchers("/auth/**", "/oauth2/**").permitAll()
	             .requestMatchers("/admin/**").hasRole("ADMIN")
	             .requestMatchers("/user/**").hasRole("USER")
	             .anyRequest().authenticated()
	         )

	         // 🔥 JWT Filter
	         .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)

	         // 🔥 OAuth2 Login
	         .oauth2Login(oauth -> oauth
	             .successHandler((request, response, authentication) -> {

	                 String username = authentication.getName();

	                 // 👉 JWT generate
	                 String token = jwtUtil.generateToken(username, "ROLE_USER");

	                 response.setContentType("application/json");
	                 response.getWriter().write(
	                         "{\"accessToken\":\"" + token + "\"}"
	                 );
	             })
	         );

	     return http.build();
	 }
}
