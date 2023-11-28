package com.lolmunchul.web.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    // 캐싱
    private final RequestCache requestCache = new HttpSessionRequestCache();
    // 리다이렉션
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        saveUserDetailsToSession(request, authentication);
        removeSession(request);

        // 처음의 요청 정보
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        String prevPage = (String) request.getSession().getAttribute("prevPage");

        // 사용자가 직접 로그인 요청한 경우
        if (prevPage != null) {
            request.getSession().removeAttribute("prevPage");
        }

        String uri = "/";

        // 권한 페이지 로그인 -> 이전 페이지로
        if (savedRequest != null) {
            uri = savedRequest.getRedirectUrl();
        } else if (prevPage != null && !prevPage.equals("")) {
            // 단, 회원가입 -> 로그인 => 인덱스
            if (prevPage.contains("/user/sign-up/policy")) {
                uri = "/";
            } else if (prevPage.contains("/user/sign-up/complete")) {
                uri = "/";
            } else if (prevPage.contains("/user/my-page/edit-info/pwd")) {
                uri = "/";
            } else {
                uri = prevPage;
            }
        }
        redirectStrategy.sendRedirect(request, response, uri);
    }

    
    // 로그인 실패 후 로그인 성공 -> 인증 실패 관련 (에러) 세션값 제거 
    protected void removeSession(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session != null) {
            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
        
    }
    protected void saveUserDetailsToSession(HttpServletRequest request, Authentication authentication) {
        // authentication에서 사용자 정보를 가져와서 세션에 저장
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        request.getSession().setAttribute("nickname", userDetails.getUsername());
    }
}