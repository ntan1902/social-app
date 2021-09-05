package com.socialapp.backend.config;

import com.socialapp.backend.exception.handler.AuthEntryPointJwt;
import com.socialapp.backend.filter.CustomAuthenticationFilter;
import com.socialapp.backend.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled = true
)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final AuthEntryPointJwt authEntryPointJwt;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // Get AuthenticationManager Bean
        return super.authenticationManagerBean();
    }

    @Bean
    public CustomAuthorizationFilter authorizationFilter() {
        return new CustomAuthorizationFilter();
    }

    @Bean
    public CustomAuthenticationFilter authenticationFilter() throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter();
        customAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/v1/users/login");

        return customAuthenticationFilter;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addExposedHeader(HttpHeaders.AUTHORIZATION);
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().and()
                .exceptionHandling().authenticationEntryPoint(authEntryPointJwt).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                    .antMatchers("/api/v1/users/login").permitAll()
                    .antMatchers("/api/v1/users/register").permitAll()
                    .antMatchers("/api/v1/users/refresh-token").permitAll()
                    .antMatchers("/api/v1/posts/images/**").permitAll()
                .anyRequest().authenticated().and()
                .addFilter(authenticationFilter())
                .addFilterBefore(authorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
