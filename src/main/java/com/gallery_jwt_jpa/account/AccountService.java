package com.gallery_jwt_jpa.account;

import com.gallery_jwt_jpa.account.model.AccountJoinReq;
import com.gallery_jwt_jpa.account.model.AccountLoginReq;
import com.gallery_jwt_jpa.account.model.AccountLoginRes;
import com.gallery_jwt_jpa.config.model.JwtUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountMapper accountMapper;

    public int join(AccountJoinReq req) {
//        비밀번호 암호화
        String hashedPw = BCrypt.hashpw(req.getLoginPw(), BCrypt.gensalt());
//        암호화가 된 비밀번호를 갖는 AccountJoinReq 객체 생성 (아이디, 이름도 포함)
        AccountJoinReq changedReq = new AccountJoinReq(req.getName(), req.getLoginId(), hashedPw);
        return accountMapper.save(changedReq);
    }

    public AccountLoginRes login(AccountLoginReq req) {
        AccountLoginRes res = accountMapper.findByLoginId(req);
// 비밀번호 체크 -> 암호화 전 비밀번호와 암호화된 비밀번호를 인자로 주어 비교하는 메소드
//        비밀번호가 다르면 "return null;" 처리
        if (res == null || !BCrypt.checkpw(req.getLoginPw(), res.getLoginPw())) {
            return null;
        }
//        로그인 성공!
//        로그인 한 사용자의 role 가져오기
        List<String> roles = accountMapper.findAllRolesByMemberId(res.getId());
        JwtUser jwtUser = new JwtUser(res.getId(), roles);
        res.setJwtUser(jwtUser);
        return res;
    }
}
