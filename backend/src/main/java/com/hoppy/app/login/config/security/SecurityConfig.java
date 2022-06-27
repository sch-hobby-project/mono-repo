package com.hoppy.app.login.config.security;

import com.hoppy.app.login.oauth.filter.OAuth2AccessTokenAuthenticationFilter;
import com.hoppy.app.login.oauth.provider.AuthTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final OAuth2AccessTokenAuthenticationFilter oAuth2AccessTokenAuthenticationFilter;

    private final AuthTokenProvider authTokenProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("Security Config Running..");

        http
                .httpBasic().disable()
                .csrf().disable()
                .cors()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
//                .antMatchers(publicApiList).permitAll()
//                .anyRequest().hasRole("USER")
                .anyRequest().permitAll()
                .and()
                .addFilterBefore(oAuth2AccessTokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

}