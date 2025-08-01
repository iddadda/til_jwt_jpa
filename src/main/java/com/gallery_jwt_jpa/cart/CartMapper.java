package com.gallery_jwt_jpa.cart;


import com.gallery_jwt_jpa.cart.model.CartDeleteReq;
import com.gallery_jwt_jpa.cart.model.CartGetRes;
import com.gallery_jwt_jpa.cart.model.CartPostReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {
    int save(CartPostReq req);

    List<CartGetRes> findAllWithItemByMemberId(int memberId);

    int deleteByCartIdAndMemberId(CartDeleteReq req);

    int deleteByMemberId(int memberId);


}
