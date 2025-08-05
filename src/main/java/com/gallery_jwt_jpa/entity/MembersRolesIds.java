package com.gallery_jwt_jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@EqualsAndHashCode
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class MembersRolesIds implements Serializable { // 복합키 역할 class 에 꼭 구현
    private long memberId;

    @Column(length = 20)
    private String roleName;

    public MembersRolesIds(String roleName) {
        this.roleName = roleName;
    }
}
