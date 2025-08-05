package com.gallery_jwt_jpa.cart;

import com.gallery_jwt_jpa.cart.model.CartDeleteReq;
import com.gallery_jwt_jpa.cart.model.CartGetRes;
import com.gallery_jwt_jpa.cart.model.CartPostReq;
import com.gallery_jwt_jpa.config.model.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {
    private final CartService cartService;

//    장바구니 추가
    @PostMapping
    public ResponseEntity<?> save(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody CartPostReq req) {
        log.info("req: {}", req);
        req.setMemberId(userPrincipal.getMemberId());
        cartService.save(req);
        return ResponseEntity.ok(1);
    }

//    상품 목록 조회
    @GetMapping
    public ResponseEntity<?> getCart(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        log.info("logginedMemberId: {}",userPrincipal.getMemberId());  // 로그인한 사용자의 pk 값 확인 로그
        List<CartGetRes> result = cartService.findAll(userPrincipal.getMemberId());
        return ResponseEntity.ok(result);
    }

//    장바구니 상품 삭제
    @DeleteMapping("/{cartId}")
    public ResponseEntity<?> deleteMemberItem(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable int cartId) {
        CartDeleteReq req = new CartDeleteReq(userPrincipal.getMemberId(), cartId);
        int result = cartService.remove(req);
        return ResponseEntity.ok(result);
    }

//    장바구니 전체 삭제
    @DeleteMapping
    public ResponseEntity<?> deleteItem(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        int result = cartService.removeAll(userPrincipal.getMemberId());
        return ResponseEntity.ok(result);
    }


}
