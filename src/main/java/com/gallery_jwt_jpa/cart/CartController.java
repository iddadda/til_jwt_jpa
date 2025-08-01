package com.gallery_jwt_jpa.cart;

import jakarta.servlet.http.HttpServletRequest;
import com.gallery_jwt_jpa.account.etc.AccountConstants;
import com.gallery_jwt_jpa.cart.model.CartDeleteReq;
import com.gallery_jwt_jpa.cart.model.CartGetRes;
import com.gallery_jwt_jpa.cart.model.CartPostReq;
import com.gallery_jwt_jpa.config.util.HttpUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> save(HttpServletRequest httpReq, @RequestBody CartPostReq req) {
        log.info("req: {}", req);
        int logginedMemberId = (int) HttpUtils.getSessionValue(httpReq, AccountConstants.MEMBER_ID_NAME);
        req.setMemberId(logginedMemberId);
        int result = cartService.save(req);
        return ResponseEntity.ok(result);
    }

//    상품 목록 조회
    @GetMapping
    public ResponseEntity<?> getCart(HttpServletRequest httpReq) {
        int logginedMemberId = (int) HttpUtils.getSessionValue(httpReq, AccountConstants.MEMBER_ID_NAME);
        List<CartGetRes> result = cartService.findAll(logginedMemberId);
        return ResponseEntity.ok(result);
    }

//    @DeleteMapping
//    public ResponseEntity<?> deleteItem(HttpServletRequest httpReq, @RequestParam int cartId) {
//        int logginedMemberId = (int) HttpUtils.getSessionValue(httpReq, AccountConstants.MEMBER_ID_NAME);
//        CartDeleteReq req = new CartDeleteReq(logginedMemberId, cartId);
//        int result = cartService.remove(req);
//        return ResponseEntity.ok(result);
//    }

//    장바구니 상품 삭제
    @DeleteMapping("/{cartId}")
    public ResponseEntity<?> deleteMemberItem(HttpServletRequest httpReq, @PathVariable int cartId) {
        int logginedMemberId = (int) HttpUtils.getSessionValue(httpReq, AccountConstants.MEMBER_ID_NAME);
//        req.setMemberId(logginedMemberId);
        CartDeleteReq req = new CartDeleteReq(logginedMemberId, cartId);
        int result = cartService.remove(req);
        return ResponseEntity.ok(result);
    }

//    장바구니 전체 삭제
    @DeleteMapping
    public ResponseEntity<?> deleteItem(HttpServletRequest httpReq) {
        int logginedMemberId = (int) HttpUtils.getSessionValue(httpReq, AccountConstants.MEMBER_ID_NAME);
        int result = cartService.removeAll(logginedMemberId);
        return ResponseEntity.ok(result);
    }


}
