package com.demoDigital.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().addFilterBefore(new CORSFilter(), ChannelProcessingFilter.class);
        
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .antMatchers("/token/*", "/sign-up", "sign-in").permitAll()
                                .anyRequest().authenticated()
                )
                .oauth2Login(oauth2Login ->
                        oauth2Login.loginPage("/oauth2/authorization/oidc"))
                .oauth2Client(withDefaults());
        return http.build();
    }

}
