package com.gallery_jwt_jpa.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration              // 빈등록, Bean 메소드가 있다.
@RequiredArgsConstructor
public class WebSecurityConfiguration {
    private final TokenAuthenticationFilter tokenAuthenticationFilter;
    private final TokenAuthenticationEntryPoint tokenAuthenticationEntryPoint;
/*
    스프링 시큐리티 기능 비활성화 (스프링 시큐리티가 관여하지 않았으면 하는 부분)
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                        .requestMatchers(new AntPathRequestMatcher("/static/**"));
    }
*/

    //    Bean 메소드
    @Bean
    public SecurityFilterChain SecurityFilterChain(HttpSecurity http, TokenAuthenticationFilter tokenAuthenticationFilter) throws Exception {
//        람다식
//        쓸데없는 리소스를 잡아 먹는걸 방지하기 위한 기본 설정이라 생각하면 됨
        return http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //security가 session을 사용하지 말아라
                .httpBasic( httpBasicSpec -> httpBasicSpec.disable())  // 시큐리티가 제공해주는 인증 처리 -> 사용 안 함
                .formLogin(formLoginSpec -> formLoginSpec.disable())  // 시큐리티가 제공해주는 인증 처리 -> 사용 안 함
                .csrf(csrfSpec -> csrfSpec.disable()) // BE: csrf 라는 공격이 있는데 공격을 막는것이 기본으로 활성화 되어 있다.
                                                                                // 세션을 이용한 공격. 세션을 어차피 안 쓰니까 비활성화
//                여기서부터 진짜
                .authorizeHttpRequests(req -> req.requestMatchers("/api/v1/cart").authenticated()
                        .requestMatchers(HttpMethod.POST,"/api/v1/item").hasRole("USER_2")  // "해당 주소에서 post로 들어 왔을 때만 로그인 해야 된다." 라는 의미
                        .anyRequest().permitAll()
                )
                .addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(e -> e.authenticationEntryPoint(tokenAuthenticationEntryPoint))
                .build();
    }
}
