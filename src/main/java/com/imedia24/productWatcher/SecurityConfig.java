package com.imedia24.productWatcher;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/swagger-ui/**", "/swagger-ui.html", "/webjars/**", "/v2/api-docs", "/h2-console/**")
                .permitAll()
            .and()
            .csrf().disable()  // Disable CSRF for simplicity. Enable for production.
            .headers().frameOptions().disable();  // Disable X-Frame-Options for H2 Console
    }
}
