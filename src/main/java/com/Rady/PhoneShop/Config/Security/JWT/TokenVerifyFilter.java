package com.Rady.PhoneShop.Config.Security.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import io.jsonwebtoken.Jwts;
public class TokenVerifyFilter extends OncePerRequestFilter {
    String secretKey="secretKey123456789secretKeysecret74185296KeysecretKey74108520963.";
    SecretKey key
 = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    @Override
    protected void doFilterInternal (HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        if (Objects.isNull(authorizationHeader) || ! authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = authorizationHeader.replace("Bearer ", "");

        Jws<Claims> claims=Jwts.parser().setSigningKey(key).build().parseSignedClaims(token);
        Claims body = claims.getBody();
        String username = body.getSubject();
        List<Map<String,String>> authorities = (List<Map<String, String>>) body.get("authorities");

        Set<SimpleGrantedAuthority> grantedAuthority = authorities.stream().
                map(x -> new SimpleGrantedAuthority(x.get("authority")))
                .collect(Collectors.toSet());


        Authentication authentication=new UsernamePasswordAuthenticationToken(username,null,grantedAuthority);
             SecurityContextHolder.getContext().setAuthentication(authentication);
             filterChain.doFilter(request,response);


    }
}

