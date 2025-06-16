package com.Rady.PhoneShop.Config.Security;

import com.Rady.PhoneShop.Config.Security.JWT.LoginFilter;
import com.Rady.PhoneShop.Config.Security.JWT.TokenVerifyFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true)
public class SecurityConfig {

    private PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig (PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer :: disable)
                .addFilter(new LoginFilter(authenticationManager(http.getSharedObject(AuthenticationConfiguration.class))))
                .addFilterAfter(new TokenVerifyFilter(),LoginFilter.class)
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Define specific URL patterns first
                        .requestMatchers("/","/index.html").permitAll()
                   /*     .requestMatchers("/brands").hasRole("SALES")*/
                        .requestMatchers(HttpMethod.POST,"/brands").hasAuthority(PermissionEnum.BRAND_WRITE.getDescription())
                        .requestMatchers(HttpMethod.GET,"/brands").hasAuthority(PermissionEnum.MODEL_READ.getDescription())
                        .requestMatchers("/models").hasRole(Role.Sale.name())

                        .anyRequest().authenticated()
                );



        return http.build();
    }

    @Bean
    protected UserDetailsService userDetailsService(){
/*        User user1=new User("Rady","rady123", Collections.emptyList());*/

        UserDetails user = User.builder()
                .username("admin")
                .authorities(Role.Admin.getAuthorities())
                .password(passwordEncoder.encode(("admin001")))
                .build();
        UserDetails user1=User.builder()
                .username("rady")
                .authorities(Role.Sale.getAuthorities())
                .password(passwordEncoder.encode("rady123"))
                .build();

        return new InMemoryUserDetailsManager(user,user1);
    }
}