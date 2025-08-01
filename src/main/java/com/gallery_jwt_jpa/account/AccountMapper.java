package com.gallery_jwt_jpa.account;

import com.gallery_jwt_jpa.account.model.AccountJoinReq;
import com.gallery_jwt_jpa.account.model.AccountLoginReq;
import com.gallery_jwt_jpa.account.model.AccountLoginRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountMapper {
   int save(AccountJoinReq req);
   AccountLoginRes findByLoginId(AccountLoginReq req);
   List<String> findAllRolesByMemberId(int memberId);

}
