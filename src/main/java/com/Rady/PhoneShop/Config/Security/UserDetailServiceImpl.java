package com.Rady.PhoneShop.Config.Security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserService userService;
    @Override
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
     return  userService.findUserByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("User not found with username: " + username));

    }
}
