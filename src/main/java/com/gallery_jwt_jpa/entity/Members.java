package com.gallery_jwt_jpa.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@EqualsAndHashCode
public class Members extends Created{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String loginId;

    @Column(nullable = false, length = 100)
    private String loginPw;

//    관계설정
    @OneToMany(mappedBy = "members", cascade = CascadeType.ALL)
    private List<MembersRoles> roles = new ArrayList<>();

    public void addRole(String roleName) {
        MembersRolesIds membersRolesIds = new MembersRolesIds(roleName);
        MembersRoles membersRoles = new MembersRoles();
        membersRoles.setMembers(this);
        membersRoles.setMembersRolesIds(membersRolesIds);

        this.roles.add(membersRoles);
    }
}
