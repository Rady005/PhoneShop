    package com.Rady.PhoneShop.Config.Security;

    import lombok.RequiredArgsConstructor;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.stereotype.Service;

    import java.util.List;
    import java.util.Optional;

    @Service
    @RequiredArgsConstructor
    public class UserSerivceFakeImpl implements UserService{
        private final PasswordEncoder passwordEncoder;



        @Override
        public Optional<AuthUser> findUserByUsername (String username) {
           List<AuthUser>users=List.of(
                   new AuthUser("Dy",passwordEncoder.encode("dy1234"),Role.Admin.getAuthorities(), true,true,true,true),
                   new AuthUser("Ra",passwordEncoder.encode("ra1234"),Role.Sale.getAuthorities(), true,true,true,true));

            return users.stream().filter(user -> user.getUsername().equals(username)).findFirst();
        }
    }
