package com.Rady.PhoneShop.Config.Security;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserService {
    Optional<AuthUser>findUserByUsername(String username);
}
