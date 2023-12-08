package com.nnk.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    /**
     * Spring conf to :
     * <br>- all url you can acess when authenticated</br>
     * <br>- use session based</br>
     * <br>- invalidate session when logout</br>
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http.formLogin((formLogin) ->
                        formLogin.defaultSuccessUrl("/admin/home", true)
                                .permitAll())

                .authorizeHttpRequests((auth) -> auth.requestMatchers("/login").permitAll()
                        .requestMatchers("/admin/home").permitAll()
                        .requestMatchers("/css/bootstrap.min.css").permitAll()
                        .requestMatchers("/bidList/**").fullyAuthenticated()
                        .requestMatchers("/user/**").fullyAuthenticated()
                        .requestMatchers("/rating/**").fullyAuthenticated()
                        .requestMatchers("/trade/**").fullyAuthenticated())
                .authenticationManager( authenticationManager())
                .sessionManagement(session -> session.maximumSessions(1))
                .logout(logout -> logout.logoutUrl("/app-logout").invalidateHttpSession(true));
        return http.build();
    }

    /**
     * Provide setting as passwordEncoder we use and our customUserDetailsService
     * @return AuthencationManager with setting provided
     */
    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(customUserDetailsService);
        return new ProviderManager(daoAuthenticationProvider);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
}
