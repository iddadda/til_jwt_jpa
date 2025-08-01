package com.gallery_jwt_jpa.account.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gallery_jwt_jpa.config.model.JwtUser;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountLoginRes {
    private int id;

//    json 생성 시 loginPw는 제외됨
    @JsonIgnore
    private String loginPw;

    @JsonIgnore
    private JwtUser jwtUser;
}
