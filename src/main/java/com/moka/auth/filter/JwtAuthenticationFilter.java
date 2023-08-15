package com.moka.auth.filter;

import com.moka.auth.dto.LoginDto;
import com.moka.auth.jwt.JwtTokenizer;
import com.moka.persnl.entity.MokaPersnl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {  // (1)
//    UsernamePasswordAuthenticationFilter를 상속하고 있습니다. UsernamePasswordAuthenticationFilter는 폼 로그인 방식에서 사용하는 디폴트 Security Filter로써, 폼 로그인이 아니더라도 Username/Password 기반의 인증을 처리하기 위해 UsernamePasswordAuthenticationFilter를 확장해서 구현할 수 있습니다.

    private final AuthenticationManager authenticationManager;
    private final JwtTokenizer jwtTokenizer;

    // (2)
//    (2)에서는 AuthenticationManager와 JwtTokenizer를 DI 받고 있습니다.
//    DI 받은 AuthenticationManager는 로그인 인증 정보(Username/Password)를 전달받아 UserDetailsService와 인터랙션 한 뒤 인증 여부를 판단합니다.
//    DI 받은 JwtTokenizer는 클라이언트가 인증에 성공할 경우, JWT를 생성 및 발급하는 역할을 합니다.
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenizer jwtTokenizer) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenizer = jwtTokenizer;
    }

    // (3)
//(3)의 attemptAuthentication()는 메서드 이름에서도 알 수 있듯이 메서드 내부에서 인증을 시도하는 로직을 구현하면 됩니다.

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {

        ObjectMapper objectMapper = new ObjectMapper();    // (3-1)클라이언트에서 전송한 Username과 Password를 DTO 클래스로 역직렬화(Deserialization) 하기 위해 ObjectMapper 인스턴스를 생성합니다.
        LoginDto loginDto = objectMapper.readValue(request.getInputStream(), LoginDto.class); // (3-2) objectMapper.readValue(request.getInputStream(), LoginDto.class)를 통해 ServletInputStream을 LoginDto 클래스의 객체로 역직렬화(Deserialization)

        // (3-3) Username과 Password 정보를 포함한 UsernamePasswordAuthenticationToken을 생성합니다.
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        return authenticationManager.authenticate(authenticationToken);  // (3-4) UsernamePasswordAuthenticationToken을 AuthenticationManager에게 전달하면서 인증 처리를 위임
    }

    // (4) successfulAuthentication() 메서드는 클라이언트의 인증 정보를 이용해 인증에 성공할 경우 호출
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws ServletException, IOException {
        MokaPersnl mokaPersnl = (MokaPersnl) authResult.getPrincipal();  // (4-1) authResult.getPrincipal()로 Member 엔티티 클래스의 객체를 얻습니다.

        String accessToken = delegateAccessToken(mokaPersnl);   // (4-2) delegateAccessToken(member) 메서드를 이용해 Access Token을 생성합니다.
        String refreshToken = delegateRefreshToken(mokaPersnl); // (4-3) delegateRefreshToken(member) 메서드를 이용해 Refresh Token을 생성합니다.

        response.setHeader("Authorization", "Bearer " + accessToken);  // (4-4)  response header(Authorization)에 Access Token을 추가합니다. Access Token은 클라이언트 측에서 백엔드 애플리케이션 측에 요청을 보낼 때마다 request header에 추가해서 클라이언트 측의 자격을 증명하는 데 사용됩니다.
        response.setHeader("Refresh", refreshToken);                   // (4-5) response header(Refresh)에 Refresh Token을 추가합니다. Refresh Token은 Access Token이 만료될 경우, 클라이언트 측이 Access Token을 새로 발급받기 위해 클라이언트에게 추가적으로 제공될 수 있으며 Refresh Token을 Access Token과 함께 클라이언트에게 제공할지 여부는 애플리케이션의 요구 사항에 따라 달라질 수 있습니다.

        this.getSuccessHandler().onAuthenticationSuccess(request, response, authResult);  // (1) 추가
//        JWT를 생성해서 response header에 추가한 뒤, (1)과 같이 AuthenticationSuccessHandler의 onAuthenticationSuccess() 메서드를 호출하고 있습니다.
//                이렇게 onAuthenticationSuccess() 메서드를 호출하면 앞에서 우리가 구현한 MemberAuthenticationSuccessHandler의 onAuthenticationSuccess() 메서드가 호출됩니다.
//        AuthenticationFailureHandler는 별도의 코드를 추가하지 않아도 로그인 인증에 실패하면 우리가 구현한 MemberAuthenticationFailureHandler의 onAuthenticationFailure() 메서드가 알아서 호출됩니다.
//
    }

    // (5)  Access Token과 Refresh Token을 생성하는 구체적인 로직입니다.
    private String delegateAccessToken(MokaPersnl mokaPersnl) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", mokaPersnl.getPersnlId());
        claims.put("roles", mokaPersnl.getRoles());

        String subject = mokaPersnl.getPersnlId();
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMinutes());

        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        String accessToken = jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);

        return accessToken;
    }

    // (6) Access Token과 Refresh Token을 생성하는 구체적인 로직입니다.
    private String delegateRefreshToken(MokaPersnl mokaPersnl) {
        String subject = mokaPersnl.getPersnlId();
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getRefreshTokenExpirationMinutes());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        String refreshToken = jwtTokenizer.generateRefreshToken(subject, expiration, base64EncodedSecretKey);

        return refreshToken;
    }
}