package com.moka.auth.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class PersnlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {  // (1)  우리가 직접 정의하는 Custom AuthenticationSuccessHandler는 (1)과 같이 AuthenticationSuccessHandler 인터페이스를 구현해야 합니다.
 // AuthenticationSuccessHandler 인터페이스에는 onAuthenticationSuccess() 추상 메서드가 정의되어 있으며, onAuthenticationSuccess() 메서드를 구현해서 추가 처리를 하면 됩니다.

    // (2) Authentication 객체에 사용자 정보를 얻은 후, HttpServletResponse로 출력 스트림을 생성하여 response를 전송할 수 있다는 사실을 기억하기 바랍니다.
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        // 인증 성공 후, 로그를 기록하거나 사용자 정보를 response로 전송하는 등의 추가 작업을 할 수 있다.
        log.info("# Authenticated successfully!");
    }
}