package com.onexshield.wmm.authentication_configuration.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class securityConfigurationFilter {

    private final jwtAuthenticationFIlter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{

        httpSecurity
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers(
                        "/api/v1/account/register",
                        "/api/v1/account/password-recovery/*/*",
                        "/api/v1/account/authenticate",
                        "/api/v1/questions",
                        "/swagger-ui/index.html#",
                        "/v2/api-docs",
                        "/v3/api-docs",
                        "/v3/api-docs/**",
                        "/swagger-ressources",
                        "/swagger-ressources/**",
                        "/configuration/ui",
                        "/configuration/security",
                        "/swagger-ui/**",
                        "/webjars/**",
                        "/swagger-ui.html"
                ) 
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)



                .logout()
                .logoutUrl("/api/v1/account/logout")
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler(
                        (request,
                         response,
                         authentication) ->
                        SecurityContextHolder.clearContext());

        // the following code the exclude login and registration from authentication
        // .authorizeHttpRequests(
        // (request) ->
        //             requests
        //             .requestMatchers(HttpMethod.POST,"/login",
        //                                              "/register")
        //              .permitAll()
        //              .anyRequest().authenticated()...)
        return httpSecurity.build();
    }
}
