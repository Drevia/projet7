package com.nnk.springboot.config;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    /**
     * Configuration de la creation de spring security (celle ci se créée uniquement si necessaire)
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

       http.sessionManagement(httpSecuritySessionManagementConfigurer ->
               httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                       .maximumSessions(1));
       http.authorizeHttpRequests((auth) ->
               auth.requestMatchers("/login")
                       .permitAll()
                        .anyRequest()
                       .authenticated())
               .formLogin((formLogin) ->
                       formLogin
                               .loginPage("/login")
                               .defaultSuccessUrl("/home", true));
       /*http.formLogin((formLogin) ->
               formLogin.loginPage("/login")
                       .passwordParameter("password")
                       .usernameParameter("username")
                       .defaultSuccessUrl("/home")
                       .permitAll()).csrf().disable()
               .authorizeHttpRequests((auth) ->
                       auth.requestMatchers("/rating/list").authenticated()
                               .requestMatchers("/css/bootstrap.min.css").permitAll());*/
       return http.build();
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    @Autowired
    void configure(AuthenticationManagerBuilder auth, DaoAuthenticationProvider authenticationProvider) {
        auth.authenticationProvider(authenticationProvider);
    }
}
