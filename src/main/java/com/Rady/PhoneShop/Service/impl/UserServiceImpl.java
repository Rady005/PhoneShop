package com.Rady.PhoneShop.Service.impl;

import com.Rady.PhoneShop.Config.Security.AuthUser;
import com.Rady.PhoneShop.Config.Security.UserService;
import com.Rady.PhoneShop.Enitity.Role;
import com.Rady.PhoneShop.Enitity.User;
import com.Rady.PhoneShop.Repository.UserRepository;
import com.Rady.PhoneShop.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;



@Primary
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public Optional<AuthUser> findUserByUsername (String username) {
        User user = userRepository.findByUsername(username).
                orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "User not Found."));

        AuthUser authUser=AuthUser.builder()
                .username(user.getUsername())
                .password(user.getPassword())
               .authorities(getAuthorities(user.getRoles()))
                .isAccountNonExpired(user.isAccountNonExpired())
                .isAccountNonLocked(user.isAccountNonLocked())
                .isEnabled(user.isEnabled())
                .isCredentialsNonExpired(user.isCredentialsNonExpired())
                .build();


        return Optional.ofNullable(authUser);
    }

    private Set<SimpleGrantedAuthority> getAuthorities(Set<Role>roles) {
        Set<SimpleGrantedAuthority> authories1 = roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .collect(Collectors.toSet());
        Set<SimpleGrantedAuthority> authories = roles.stream().flatMap(this::toStream).collect(Collectors.toSet());

        authories.addAll(authories1);
        return authories;

    }

    private Stream<SimpleGrantedAuthority>toStream(Role role) {

       return role.getPermissions().stream().
                map(permission -> new SimpleGrantedAuthority(permission.getName()));

    }


}
