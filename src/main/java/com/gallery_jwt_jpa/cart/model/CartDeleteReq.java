package com.gallery_jwt_jpa.cart.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
//@Setter
@AllArgsConstructor
public class CartDeleteReq {
    private long memberId;
    private int cartId;

}
