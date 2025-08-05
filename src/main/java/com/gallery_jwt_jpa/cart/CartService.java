package com.gallery_jwt_jpa.cart;

import com.gallery_jwt_jpa.cart.model.CartDeleteReq;
import com.gallery_jwt_jpa.cart.model.CartGetRes;
import com.gallery_jwt_jpa.cart.model.CartPostReq;
import com.gallery_jwt_jpa.entity.Carts;
import com.gallery_jwt_jpa.entity.Items;
import com.gallery_jwt_jpa.entity.Members;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Member;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartService {
    private final CartMapper cartMapper;
    private final CartRepository cartRepository;

    public void save(CartPostReq req) {
        try {
            Items items = new Items();
            items.setId(req.getItemId());
            Members members = new Members();
            members.setId(req.getMemberId());

            Carts carts = new Carts();
            carts.setMembers(members);
            carts.setItems(items);

            cartRepository.save(carts);

//        cartMapper.save(req);
        }  catch (DuplicateKeyException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이미 카트에 등록되어 있습니다.");
        }

    }

    public List<CartGetRes> findAll(long memberId) {
        return cartMapper.findAllWithItemByMemberId(memberId);
    }

    public int remove(CartDeleteReq req) {
        return cartMapper.deleteByCartIdAndMemberId(req);
    }

    public int removeAll(long memberId) {
        return cartMapper.deleteByMemberId(memberId);
    }
}
