package com.lolmunchul.web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthenticationSuccessHandler authenticationSuccessHandler;

    public SecurityConfig(AuthenticationSuccessHandler authenticationSuccessHandler) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

    // 권한을 위한 필터 객체
    // HttpSecurity가 필터를 만들 수 있도록 하는 class
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // cross origin에 대해 403을 띄우는 것 방지(안전 모드를 해제하는 느낌)
                .csrf(AbstractHttpConfigurer::disable)
                // h2-console 옵션 disable
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                //액세스 권한 설정
                .authorizeHttpRequests(
                        auth->auth
                                .requestMatchers("/user/my-page/**").hasAnyRole("ADMIN", "MEMBER")
//                                .requestMatchers("/user/login/**").hasAnyRole("ADMIN", "MEMBER")
                                .anyRequest().permitAll()) // 이외의 요청은 전부 승인함
                .formLogin(
                        login -> login
                                .loginPage("/user/login") // GET 요청
                                .loginProcessingUrl("/user/login") // POST 요청
                                .usernameParameter("email")
                                .defaultSuccessUrl("/") // 다른 페이지에서 온 게 아니라 바로 로그인 버튼 눌렀을 때
                                .successHandler(authenticationSuccessHandler)) // 다른 페이지에서 왔을 때
                .logout(
                        logout -> logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/user/login"));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 콩자루에 담는 작업
        return new BCryptPasswordEncoder();
    }
}
