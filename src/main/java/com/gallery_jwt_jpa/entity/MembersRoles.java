package com.gallery_jwt_jpa.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@EqualsAndHashCode
public class MembersRoles {
    @EmbeddedId
    private MembersRolesIds membersRolesIds;

//    관계 설정
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @MapsId("memberId")
    private Members members;
}
