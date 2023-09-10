package com.eazybytes.soringsecuritybasic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class ProjectSecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests()

                .requestMatchers("/loan").hasAuthority("VIEWLOANS")
                .requestMatchers("/card").hasAuthority("VIEWCARDS")
                .requestMatchers("/balance").hasAnyAuthority("VIEWBALANCE","VIEWACCOUNT")
                .requestMatchers("/account").hasAuthority("VIEWACCOUNT")
                .requestMatchers("/contact","/notice","/register").permitAll();
        http.formLogin();
        http.httpBasic();
        return (SecurityFilterChain)http.build();
    }

    //In Memory password implementation
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("meenu")
//                .password("123")
//                .authorities("read")
//                .build();
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("pilu")
//                .password("321")
//                .authorities("admin")
//                .build();
//        return new InMemoryUserDetailsManager(admin, user);
//    }

    //Jdbc password implementation
//    @Bean
//    public UserDetailsService jdbcDetailsService(DataSource datasource) {
//        return new JdbcUserDetailsManager(datasource);
//    }

    //NoOpPasswordEncode Used
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
