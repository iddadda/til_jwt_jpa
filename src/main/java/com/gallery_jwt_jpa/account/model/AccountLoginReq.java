package com.gallery_jwt_jpa.account.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountLoginReq {
    private String loginId;
    private String loginPw;
}
