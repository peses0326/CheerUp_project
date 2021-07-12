package com.cheerup.cheerup.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@Profile("!https")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean   // 비밀번호 암호화
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

//    @Override
//    protected void configure(final HttpSecurity http) throws Exception {
//        http.csrf().disable().authorizeRequests()
//                //...
//                .antMatchers(
//                        HttpMethod.GET,
//                        "/index*", "/static/**", "/*.js", "/*.json", "/*.ico", "/v2/api-docs",
//                        "/configuration/ui", "/swagger-resources", "/configuration/security",
//                        "**", "/**", "/swagger/**")
//                .permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/user/login")
//                .failureUrl("/")
//                .defaultSuccessUrl("/")
//                .permitAll();
//    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.authorizeRequests()

                // image 폴더를 login 없이 허용
                .antMatchers("/images/**").permitAll()
                // css 폴더를 login 없이 허용
                .antMatchers("/css/**").permitAll()
                .antMatchers("/user/**").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                // 메인페이지 접근허용
                .antMatchers("/index/**").permitAll()
                // 글 조회 접근허용
                .antMatchers("/detail.html/**").permitAll()
                // api 요청 접근허용
                .antMatchers("/api/**").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/**").permitAll()

                // 그 외 모든 요청은 인증과정 필요
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/user/login")
                .failureUrl("/user/login/error")
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/user/logout")
                .logoutSuccessUrl("/")
                .permitAll();
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}