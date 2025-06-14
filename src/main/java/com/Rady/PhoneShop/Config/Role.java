package com.Rady.PhoneShop.Config;

import lombok.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import java.util.Set;
import java.util.stream.Collectors;

import static com.Rady.PhoneShop.Config.PermissionEnum.*;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Role {
    Admin(Set.of(BRAND_WRITE,BRAND_READ,MODEL_WRITE,MODEL_READ)),
    Sale(Set.of(BRAND_READ,MODEL_READ));
    private Set<PermissionEnum>permissions;

    public Set<SimpleGrantedAuthority>getAuthorities(){
        Set<SimpleGrantedAuthority> grantedAuthorities = this.permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getDescription()))
                .collect(Collectors.toSet());

        SimpleGrantedAuthority role= new SimpleGrantedAuthority("ROLE_"+this.name());
        grantedAuthorities.add(role);

        System.out.println(grantedAuthorities);

        return grantedAuthorities;
    }
}
