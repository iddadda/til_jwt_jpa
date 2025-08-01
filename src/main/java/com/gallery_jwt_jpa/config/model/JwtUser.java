package com.gallery_jwt_jpa.config.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class JwtUser {
    private int signedUserId;      // jwt는 기본적으로 long 타입 사용함
    private List<String> roles;     // 인가 처리  //  role 이름은 ROLE_아무거나 (ROLE_USER, ROLE_ADMIN, ROLE_MANAGER, ROLE_LEVEL_1, ...)
}
