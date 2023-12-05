package com.lolmunchul.web.security;
//
import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
//
@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    // 캐싱
    private final RequestCache requestCache = new HttpSessionRequestCache();
    // 리다이렉션
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        SavedRequest savedRequest = requestCache.getRequest(request, response);
        String uri = "/";

        if (savedRequest != null) {
            uri = savedRequest.getRedirectUrl();
        } else {
            uri = determineTargetUrl(request);
        }
        redirectStrategy.sendRedirect(request, response, uri);

    }
    private String determineTargetUrl(HttpServletRequest request) {
        String prevPage = (String) request.getSession().getAttribute("prevPage");

        if (prevPage != null) {
            request.getSession().removeAttribute("prevPage");

            if (prevPage.contains("/user/join")) {
                return "/";
            } else {
                return prevPage;
            }
        }

        return "/";
    }


    // 로그인 실패 후 로그인 성공 -> 인증 실패 관련 (에러) 세션값 제거
    protected void removeSession(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session != null) {
            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }

    }
}
