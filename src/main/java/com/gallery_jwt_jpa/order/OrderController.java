package com.gallery_jwt_jpa.order;

import com.gallery_jwt_jpa.config.model.UserPrincipal;
import jakarta.servlet.http.HttpServletRequest;
import com.gallery_jwt_jpa.account.etc.AccountConstants;
import com.gallery_jwt_jpa.config.util.HttpUtils;
import com.gallery_jwt_jpa.order.model.OrderDetailGetReq;
import com.gallery_jwt_jpa.order.model.OrderDetailGetRes;
import com.gallery_jwt_jpa.order.model.OrderGetRes;
import com.gallery_jwt_jpa.order.model.OrderPostReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<?> add(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody OrderPostReq req) {
        log.info("req: {}",req);
        int result = orderService.saveOrder(req, userPrincipal.getMemberId());
        return ResponseEntity.ok(result);
    }

//    주문 목록 가져오기
    @GetMapping
    public ResponseEntity<?> findAllOrder(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        List<OrderGetRes> result = orderService.findAllByMemberId(userPrincipal.getMemberId());
        return ResponseEntity.ok(result);
    }

    //    주문 상세보기
    @GetMapping("{orderId}")
    public ResponseEntity<?> findDetail(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable int orderId) {
        OrderDetailGetReq req = OrderDetailGetReq.builder()
                .orderId(orderId)
                .memberId(userPrincipal.getMemberId())
                .build();

        OrderDetailGetRes result = orderService.detail(req);
        return ResponseEntity.ok(result);
    }
}
