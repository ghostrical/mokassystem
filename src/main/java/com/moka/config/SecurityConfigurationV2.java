package com.moka.config;

import com.moka.auth.filter.JwtAuthenticationFilter;
import com.moka.auth.jwt.JwtTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

//@Configuration
public class SecurityConfigurationV2 {

    private final JwtTokenizer jwtTokenizer;

    public SecurityConfigurationV2(JwtTokenizer jwtTokenizer) {
        this.jwtTokenizer = jwtTokenizer;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().sameOrigin() // H2 웹 콘솔의 화면 자체가 내부적으로 태그를 사용하고 있기 때문에 개발 환경에서는 H2 웹 콘솔을 정상적으로 사용할 수 있도록 (1)과 같이 .frameOptions().sameOrigin() 을 추가
                .and()
                .csrf().disable()  // CSRF(Cross-Site Request Forgery) 공격에 대한 Spring Security에 대한 설정을 비활성화합니다. 로컬이라 비활성화.
                .cors(withDefaults()) // .cors(withDefaults()) 일 경우, corsConfigurationSource라는 이름으로 등록된 Bean을 이용합니다.
                .formLogin().disable() // CSR(Client Side Rendering) 방식에서 주로 사용하는 JSON 포맷으로 Username과 Password를 전송하는 방식을 사용할 것이므로 (4)와 같이 폼 로그인 방식을 비활성화
                .httpBasic().disable() // HTTP Basic 인증은 request를 전송할 때마다 Username/Password 정보를 HTTP Header에 실어서 인증을 하는 방식으로 우리 코스에서는 사용하지 않으므로 (5)와 같이 HTTP Basic 인증 방식을 비활성화
                .apply(new CustomFilterConfigurer()) // (1) apply() 메서드에 Custom Configurer를 추가해 커스터마이징(customizations)된 Configuration을 추가할 수 있습니다.
                .and()
                .authorizeHttpRequests( authorize -> authorize.anyRequest().permitAll()
                );

        return http.build();

    }

    // (7)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    // (8) CorsConfigurationSource Bean 생성을 통해 구체적인 CORS 정책을 설정
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));   // (8-1)  setAllowedOrigins()을 통해 모든 출처(Origin)에 대해 스크립트 기반의 HTTP 통신을 허용하도록 설정합니다.
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "PATCH", "DELETE"));  // (8-2) setAllowedMethods()를 통해 파라미터로 지정한 HTTP Method에 대한 HTTP 통신을 허용

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();   // (8-3) CorsConfigurationSource 인터페이스의 구현 클래스인 UrlBasedCorsConfigurationSource 클래스의 객체를 생성
        source.registerCorsConfiguration("/**", configuration);      // (8-4)     모든 URL에 앞에서 구성한 CORS 정책(CorsConfiguration)을 적용
        return source;
    }

    // (2) Custom Configurer인 CustomFilterConfigurer 클래스입니다. CustomFilterConfigurer는 우리가 구현한 JwtAuthenticationFilter를 등록하는 역할을 합니다.
    public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {  // (2-1) AbstractHttpConfigurer를 상속해서 Custom Configurer를 구현
        @Override
        public void configure(HttpSecurity builder) throws Exception {  // (2-2) configure() 메서드를 오버라이드해서 Configuration을 커스터마이징할 수 있습니다.
            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);  // (2-3) getSharedObject(AuthenticationManager.class)를 통해 AuthenticationManager의 객체를 얻을 수 있습니다.
// getSharedObject()를 통해서 Spring Security의 설정을 구성하는 SecurityConfigurer 간에 공유되는 객체를 얻을 수 있습니다.

            JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager, jwtTokenizer);  // (2-4) JwtAuthenticationFilter를 생성하면서 JwtAuthenticationFilter에서 사용되는 AuthenticationManager와 JwtTokenizer를 DI 해줍니다.
            jwtAuthenticationFilter.setFilterProcessesUrl("/v1/auth/login");          // (2-5) setFilterProcessesUrl() 메서드를 통해 디폴트 request URL인 “/login”을 “/v11/auth/login”으로 변경합니다.
                                                                                    // 이걸 주목!!!!!!!!!
            builder.addFilter(jwtAuthenticationFilter);  // (2-6)  addFilter() 메서드를 통해 JwtAuthenticationFilter를 Spring Security Filter Chain에 추가합니다.
        }
    }
}
