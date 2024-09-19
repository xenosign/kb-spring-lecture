package org.example.security;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String requestUri = request.getRequestURI();
        System.out.println("요청 URI: " + requestUri);

        HttpSession session = request.getSession();
        if (session.getAttribute("loginUser") == null) {
            System.out.println("로그인 안됨. 리다이렉트 중: /user/login");
            response.sendRedirect("/user/login");
            return false;
        }
        return true;
    }
}
