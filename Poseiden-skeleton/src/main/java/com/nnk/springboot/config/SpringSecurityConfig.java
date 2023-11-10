package com.nnk.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;*/

import javax.sql.DataSource;

/*@Configuration
@EnableWebSecurity*/
public class SpringSecurityConfig {

/*    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll

                ).authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests.requestMatchers("/transfer").fullyAuthenticated()
                                .requestMatchers("/css/login-styles.css").permitAll()
                                .requestMatchers("/css/transfer-styles.css").permitAll());

        return http.build();
    }*/
}
