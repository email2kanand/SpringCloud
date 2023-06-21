package com.kanand.ms.admin;

import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import de.codecentric.boot.admin.server.config.AdminServerProperties;

@Configuration
//@EnableWebSecurity
public class WebSecurityConfig {
    private final AdminServerProperties adminServer;
    private final String adminContextPath;
    public WebSecurityConfig(AdminServerProperties adminServer) {
        this.adminServer = adminServer;
        this.adminContextPath = adminServer.getContextPath();
    }

    
    @Bean
    public SecurityWebFilterChain securityWebFilterChainSecure(ServerHttpSecurity http) {

     return http.authorizeExchange()
      .pathMatchers(adminContextPath + "/assets/**").permitAll()
      .pathMatchers(adminContextPath + "/login").permitAll()
      .anyExchange().authenticated()
      .and().formLogin().loginPage(adminContextPath + "/login")
      .and().logout().logoutUrl(adminContextPath + "/logout")
      .and().httpBasic()
      .and().csrf().disable().build();

    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        SavedRequestAwareAuthenticationSuccessHandler successHandler = 
//          new SavedRequestAwareAuthenticationSuccessHandler();
//        successHandler.setTargetUrlParameter("redirectTo");
//        successHandler.setDefaultTargetUrl(this.adminServer.getContextPath() + "/");
//
//        http
//            .authorizeRequests()
//                .antMatchers(this.adminServer.getContextPath() + "/assets/**").permitAll()
//                .antMatchers(this.adminServer.getContextPath() + "/login").permitAll()
//                .anyRequest().authenticated()
//                .and()
//            .formLogin()
//                .loginPage(this.adminServer.getContextPath() + "/login")
//                .successHandler(successHandler)
//                .and()
//            .logout()
//                .logoutUrl(this.adminServer.getContextPath() + "/logout")
//                .and()
//            .httpBasic()
//                .and()
//            .csrf()
//                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                .ignoringRequestMatchers(
//                  new AntPathRequestMatcher(this.adminServer.getContextPath() + 
//                   "/instances", HttpMethod.POST.toString()), 
//                  new AntPathRequestMatcher(this.adminServer.getContextPath() + 
//                   "/instances/*", HttpMethod.DELETE.toString()),
//                  new AntPathRequestMatcher(this.adminServer.getContextPath() + "/actuator/**"))
//                .and()
//            .rememberMe()
//                .key(UUID.randomUUID().toString())
//                .tokenValiditySeconds(1209600);
//        return http.build();
//    }
}