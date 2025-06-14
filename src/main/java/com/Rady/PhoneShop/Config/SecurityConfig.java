package com.Rady.PhoneShop.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
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
import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
public class SecurityConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer :: disable)
                .authorizeHttpRequests(auth -> auth
                        // Define specific URL patterns first
                        .requestMatchers("/","/index.html").permitAll()
                   /*     .requestMatchers("/brands").hasRole("SALES")*/
                        .requestMatchers(HttpMethod.POST,"/brands").hasAuthority(PermissionEnum.BRAND_WRITE.getDescription())
                        .requestMatchers(HttpMethod.GET,"/brands").hasAuthority(PermissionEnum.MODEL_READ.getDescription())
                        .requestMatchers("/models").hasRole(Role.Sale.name())

                        .anyRequest().authenticated()
                )

                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
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