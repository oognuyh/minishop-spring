package com.example.minishop_spring.config;

import com.example.minishop_spring.model.Account;
import com.example.minishop_spring.model.Authority;
import com.example.minishop_spring.model.Member;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();

        characterEncodingFilter.setEncoding("utf-8");
        characterEncodingFilter.setForceEncoding(true);

        http
                .authorizeRequests()
                    .antMatchers("/auth/**").authenticated()
                    .anyRequest()
                    .permitAll()
                .and()
                .formLogin()
                    .loginPage("/signin")
                    .loginProcessingUrl("/signin")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .failureHandler(authenticationFailureHandler)
                .and()
                .logout()
                    .logoutUrl("/signout")
                    .logoutSuccessUrl("/signin")
                    .invalidateHttpSession(true)
                .and()
                .addFilterBefore(characterEncodingFilter, CsrfFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/images/**", "/webjars/**", "/js/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .inMemoryAuthentication()
                    .withUser(new Account(Member.builder()
                            .name("admin")
                            .password(passwordEncoder().encode("admin"))
                            .email("admin")
                            .phoneNumber("01000000000")
                            .authority(Authority.ADMIN.getValue())
                            .build()));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
