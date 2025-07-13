package ru.serggge.telros_user_api.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@Profile("!dev")
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtFilter jwtFilter;
    private final UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/login")
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return daoAuthenticationProvider;
    }


    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        return new ProviderManager(authenticationProvider());
    }

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
