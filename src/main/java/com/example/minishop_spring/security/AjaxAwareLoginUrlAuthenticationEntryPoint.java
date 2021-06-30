package com.example.minishop_spring.security;

import com.example.minishop_spring.model.Response;
import com.example.minishop_spring.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class AjaxAwareLoginUrlAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

    public AjaxAwareLoginUrlAuthenticationEntryPoint(String loginFormUrl) {
        super(loginFormUrl);
    }

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
            Map<String, String> redirectMessage = new HashMap<>();
            redirectMessage.put("redirect", "/signin");

            response.setContentType("application/json; charset=utf-8");
            response.getWriter().write(ObjectUtil.asString(Response.ERROR(redirectMessage)));

            HttpSession session = request.getSession();

            session.setAttribute("REDIRECT_TO_PREVIOUS", request.getHeader("referer"));
        } else {
            super.commence(request, response, authException);
        }
    }
}
