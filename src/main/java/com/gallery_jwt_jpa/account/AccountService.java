package com.gallery_jwt_jpa.account;

import com.gallery_jwt_jpa.account.model.AccountJoinReq;
import com.gallery_jwt_jpa.account.model.AccountLoginReq;
import com.gallery_jwt_jpa.account.model.AccountLoginRes;
import com.gallery_jwt_jpa.config.model.JwtUser;
import com.gallery_jwt_jpa.entity.Members;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountMapper accountMapper;
    private final AccountRepository accountRepository;

    public int join(AccountJoinReq req) {
//        비밀번호 암호화
        String hashedPw = BCrypt.hashpw(req.getLoginPw(), BCrypt.gensalt());
//        암호화가 된 비밀번호를 갖는 AccountJoinReq 객체 생성 (아이디, 이름도 포함)
//        AccountJoinReq changedReq = new AccountJoinReq(req.getName(), req.getLoginId(), hashedPw);
        Members members = new Members();
        members.setLoginId(req.getLoginId());
        members.setLoginPw(hashedPw);
        members.setName(req.getName());

        members.addRole("ROLE_USER_1");

        accountRepository.save(members);
        //        return accountMapper.save(changedReq);
    return 1;
    }

    public AccountLoginRes login(AccountLoginReq req) {
        Members members = accountRepository.findByLoginId(req.getLoginId());
// 비밀번호 체크 -> 암호화 전 비밀번호와 암호화된 비밀번호를 인자로 주어 비교하는 메소드
//        비밀번호가 다르면 "return null;" 처리
        if (members == null || !BCrypt.checkpw(req.getLoginPw(), members.getLoginPw())) {
            return null;
        }
//        로그인 성공!
//        로그인 한 사용자의 role(권한) 가져오기
        List<String> roles = members.getRoles().stream().map(item -> item.getMembersRolesIds()
                                                                                        .getRoleName())
                                                                                        .collect(Collectors.toList());

        JwtUser jwtUser = new JwtUser(members.getId(), roles);

        AccountLoginRes accountLoginRes = new AccountLoginRes();
        accountLoginRes.setJwtUser(jwtUser);
        accountLoginRes.setId(members.getId());

        return accountLoginRes;
    }
}
