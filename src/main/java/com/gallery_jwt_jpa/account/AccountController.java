package com.gallery_jwt_jpa.account;

import com.gallery_jwt_jpa.account.etc.AccountConstants;
import com.gallery_jwt_jpa.account.model.AccountJoinReq;
import com.gallery_jwt_jpa.account.model.AccountLoginReq;
import com.gallery_jwt_jpa.account.model.AccountLoginRes;
import com.gallery_jwt_jpa.config.jwt.JwtTokenManager;
import com.gallery_jwt_jpa.config.util.HttpUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/account")
public class AccountController {
    private final AccountService accountService;
    private final JwtTokenManager jwtTokenManager;

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody AccountJoinReq req) {
//        빈 값을 보내면 에러 발생하도록
        if (!StringUtils.hasLength(req.getName())
                || !StringUtils.hasLength(req.getLoginId())
                || !StringUtils.hasLength(req.getLoginPw())) {
            return ResponseEntity.badRequest().build(); //state: 400
        }
    accountService.join(req);
        return ResponseEntity.ok(1);   //state: 200
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(HttpServletResponse response, @RequestBody AccountLoginReq req) {
        AccountLoginRes result = accountService.login(req);
//        result가 null 이면 notFound 반환, 그게 아니면 ok 반환
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        jwtTokenManager.issue(response, result.getJwtUser());
        //        세션 처리
//        HttpUtils.setSession(httpReq, AccountConstants.MEMBER_ID_NAME, result.getId());
        return ResponseEntity.ok(result);
    }


    //    재발행
    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response) {
        jwtTokenManager.reissue(request, response);
        return ResponseEntity.ok(1);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        jwtTokenManager.logout(response);
        return ResponseEntity.ok(1);
    }
}
