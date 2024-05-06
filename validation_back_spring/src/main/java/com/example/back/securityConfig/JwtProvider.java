package com.example.back.securityConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class JwtProvider {

    static KeyGenerator keyGenerator;

    static {
        try {
            keyGenerator = KeyGenerator.getInstance("HmacSHA256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    static SecretKey key = keyGenerator.generateKey();
    public JwtProvider() throws NoSuchAlgorithmException {
    }

    public static String generateToken(Authentication auth) {
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        String roles = populateAuthorities(authorities);
        @SuppressWarnings("deprecation")
        String jwt = Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 86400000)) // 24 hours
                .claim("email", auth.getName()) // Replace with auth.getName()
                .claim("authorities", roles) // Replace with roles
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
        return jwt;

    }

    private static String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Set<String> auths = new HashSet<>();
        for(GrantedAuthority authority: authorities) {
            auths.add(authority.getAuthority());
        }
        return String.join(",",auths);
    }


    @SuppressWarnings("deprecation")
    public static String getEmailFromJwtToken(String jwt) {
        jwt = jwt.substring(7); // Assuming "Bearer " is removed from the token
        try {
            //Claims claims=Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
            Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();
            String email = String.valueOf(claims.get("email"));

            return email;
        } catch (Exception e) {
            System.err.println("Error extracting email from JWT: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static Authentication parseToken(String jwt) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(jwt)
                    .getBody();

            String email = String.valueOf(claims.get("email"));
            String authorities = String.valueOf(claims.get("authorities"));
            List<GrantedAuthority> authorityList = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);

            return new UsernamePasswordAuthenticationToken(email, null, authorityList);
        } catch (Exception e) {
            throw new RuntimeException("Error parsing JWT token", e);
        }
    }
}
