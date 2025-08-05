package com.gallery_jwt_jpa.account;

import com.gallery_jwt_jpa.entity.Members;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Members,Long> {
    Members findByLoginId(String loginId);
}
