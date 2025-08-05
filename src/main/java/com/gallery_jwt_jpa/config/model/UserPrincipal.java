package com.gallery_jwt_jpa.config.model;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
public class UserPrincipal implements UserDetails {
    private final long memberId;
    private final Collection<? extends GrantedAuthority> authorities;  // 인증 권한

    public UserPrincipal(long memberId, List<String> roles) {
        this.memberId = memberId;
//        방법1
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for(String role : roles){
            list.add(new SimpleGrantedAuthority(role));
        }
        this.authorities = list;
//        방법2
//        this.authorities = roles.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
    }

    //    권한

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }
}
