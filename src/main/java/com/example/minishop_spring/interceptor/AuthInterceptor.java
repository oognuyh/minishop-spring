package com.example.minishop_spring.interceptor;

import com.example.minishop_spring.model.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Slf4j
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Optional<Member> member = Optional.ofNullable((Member) session.getAttribute("member"));

        if (member.isPresent()) return true;

        String destination = request.getRequestURI();

        log.info("destination: {}", destination);

        session.setAttribute("destination", destination);
        response.sendRedirect("/signin");

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }
}
