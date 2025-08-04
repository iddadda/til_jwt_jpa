package com.gallery_jwt_jpa.config.jwt;

import com.gallery_jwt_jpa.config.constants.ConstJwt;
import com.gallery_jwt_jpa.config.model.JwtUser;
import com.gallery_jwt_jpa.config.model.UserPrincipal;
import com.gallery_jwt_jpa.config.util.CookieUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

// JWT 총괄 책임자
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenManager {
    private final ConstJwt constJwt;
    private final CookieUtils cookieUtils;
    private final JwtTokenProvider jwtTokenProvider;   // 토큰 만들 수 있는 필드

    public void issue(HttpServletResponse response, JwtUser jwtUser) {
        setAccessTokenInCookie(response, jwtUser); // 15분으로 설정함 // 실제 인증시 사용하는 토큰
        setRefreshTokenInCookie(response, jwtUser); // 15일로 설정함  // 토큰 재발행시 사용하는 토큰
    }

    public String generateAccessToken(JwtUser jwtUser) {
        return jwtTokenProvider.generateToken(jwtUser, constJwt.getAccessTokenValidityMilliseconds());
    }

    public void setAccessTokenInCookie(HttpServletResponse response, JwtUser jwtUser) {
        setAccessTokenInCookie(response, generateAccessToken(jwtUser));
    }

    public void setAccessTokenInCookie(HttpServletResponse response, String accessToken) {
        cookieUtils.setCookie(response, constJwt.getAccessTokenCookieName(), accessToken
                , constJwt.getAccessTokenCookieValiditySeconds(), constJwt.getAccessTokenCookiePath());
    }

    //    액세스 토큰 삭제
    public void deleteAccessTokenInCookie(HttpServletResponse response) {
        cookieUtils.deleteCookie(response, constJwt.getAccessTokenCookieName(), constJwt.getAccessTokenCookiePath());
    }


    public String generateRefreshToken(JwtUser jwtUser) {
        return jwtTokenProvider.generateToken(jwtUser, constJwt.getRefreshTokenValidityMilliseconds());
    }

    public void setRefreshTokenInCookie(HttpServletResponse response, JwtUser jwtUser) {
        setRefreshTokenInCookie(response, generateRefreshToken(jwtUser));
    }

    public void setRefreshTokenInCookie(HttpServletResponse response, String refreshToken) {
        cookieUtils.setCookie(response, constJwt.getRefreshTokenCookieName(), refreshToken, constJwt.getRefreshTokenCookieValiditySeconds(), constJwt.getRefreshTokenCookiePath());
    }

    //    리프레시 토큰 삭제
    public void deleteRefreshTokenInCookie(HttpServletResponse response) {
        cookieUtils.deleteCookie(response, constJwt.getRefreshTokenCookieName(), constJwt.getRefreshTokenCookiePath());
    }

    public JwtUser getJwtUserFromToken(String token) {
        return jwtTokenProvider.getJwtUserFromToken(token);
    }

    public String getRefreshTokenFromCookie(HttpServletRequest request) {
        return cookieUtils.getValue(request, constJwt.getRefreshTokenCookieName());
    }

    public String getAccessTokenFromCookie(HttpServletRequest request) {
        return cookieUtils.getValue(request, constJwt.getAccessTokenCookieName());
    }

    public void reissue(HttpServletRequest request, HttpServletResponse response) {
        //request에서 refreshToken을 얻는다.
        String refreshToken = getRefreshTokenFromCookie(request);

        //refreshToken에서 jwtUser를 만든다.
        JwtUser jwtUser = getJwtUserFromToken(refreshToken);

        //jwtUser로 accessToken을 발행한다.
        String accessToken = generateAccessToken(jwtUser);

        //accessToken을 쿠키에 담는다.
        setAccessTokenInCookie(response, accessToken);
    }

    public void logout(HttpServletResponse response) {
        deleteAccessTokenInCookie(response);
        deleteRefreshTokenInCookie(response);
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        String accessToken = getAccessTokenFromCookie(request);
        if(accessToken == null) {return null;}
        JwtUser jwtUser = getJwtUserFromToken(accessToken);
        if (jwtUser == null) {return null;}  // 앞에서 예외처리를 안 해줘서 임시로 넣은 코드
        UserPrincipal userPrincipal = new UserPrincipal(jwtUser.getSignedUserId(), jwtUser.getRoles());
        return new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
    }
}