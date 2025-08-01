package com.gallery_jwt_jpa.config.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix =  "constants.jwt")         // yml 에 작성한 거 아래 멤버필드에 쇽쇽 넣을 때 사용(Spring Configurater 사용한다고 설정해서 사용 가능한 것)
@RequiredArgsConstructor
@ToString
public class ConstJwt {
    private final String issuer;
    private final String bearerFormat;

    private final String claimKey;
    private final String secretKey;

    private final String accessTokenCookieName;             // 쿠키에 담을 이름
    private final String accessTokenCookiePath;             // 쿠키에 담을 경로
    private final int accessTokenCookieValiditySeconds;     // 쿠키는 초단위
    private final int accessTokenValidityMilliseconds;

    private final String refreshTokenCookieName;
    private final String refreshTokenCookiePath;
    private final int refreshTokenCookieValiditySeconds;
    private final int refreshTokenValidityMilliseconds;



}
