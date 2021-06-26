package com.example.minishop_spring.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String message = "";

        log.info("Failed to authenticate.");

        if (exception instanceof AuthenticationServiceException) {
            message = "존재하지 않는 사용자입니다.";
        } else if (exception instanceof BadCredentialsException) {
            message = "이메일 또는 비밀번호가 올바르지 않습니다.";
        } else if (exception instanceof LockedException) {
            message = "잠긴 계정입니다.";
        } else if (exception instanceof DisabledException) {
            message = "비활성화된 계정입니다.";
        } else if (exception instanceof AccountExpiredException) {
            message = "만료된 계정입니다.";
        } else if (exception instanceof CredentialsExpiredException) {
            message = "비밀번호가 만료되었습니다.";
        }

        // response.sendRedirect("/signin?message=" + URLEncoder.encode(message, "utf-8"));

        request.setAttribute("message", message);
        request.getRequestDispatcher("/signin").forward(request, response);
    }
}
