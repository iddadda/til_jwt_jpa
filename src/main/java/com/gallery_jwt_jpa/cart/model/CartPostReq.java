package com.gallery_jwt_jpa.cart.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartPostReq {
    private int memberId;
    private int itemId;
    }

