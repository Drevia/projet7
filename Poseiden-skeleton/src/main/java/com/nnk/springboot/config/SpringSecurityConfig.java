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
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Autowired
    private UserRepository userRepository;

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
               httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));
       return http.build();
    }

    /**
     *
     * @return
     */
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    @Autowired
    protected void configure(HttpSecurity http, AuthenticationManagerBuilder auth) throws Exception {
        http.sessionManagement(httpSecuritySessionManagementConfigurer ->
                httpSecuritySessionManagementConfigurer.maximumSessions(1));

        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(username -> {
            User user = userRepository.findByUsername(username);

            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    AuthorityUtils.createAuthorityList(user.getRole())
            );
        });
        authProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return authProvider;
    }
}
