package com.Rady.PhoneShop.Config.Security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthUser implements UserDetails {

    private String username;
    private String password;
    private Set<? extends GrantedAuthority> authorities;
    private boolean isAccountNonExpired;
    private boolean isEnabled;
    private boolean isAccountNonLocked;

    private boolean isCredentialsNonExpired;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities () {
        return authorities;
    }

    @Override
    public String getPassword () {
        return password;
    }

    @Override
    public String getUsername () {
        return username;
    }

    @Override
    public boolean isAccountNonExpired () {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked () {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired () {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled () {
        return isEnabled;
    }


}
