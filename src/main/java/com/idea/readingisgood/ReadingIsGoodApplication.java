package com.idea.readingisgood;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@SpringBootApplication
public class ReadingIsGoodApplication {
    private final static Logger logger = LoggerFactory.getLogger(ReadingIsGoodApplication.class);

    public static void main(String[] args) {
        logger.info(new StringBuilder("Bearer token was here: ").append(getJWTToken()).toString());
        SpringApplication.run(ReadingIsGoodApplication.class, args);
    }

    private static String getJWTToken() {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts.builder()
            .setId("JWT")
            .setSubject("rocking")
            .claim("authorities",
                grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 6000000))
            .signWith(SignatureAlgorithm.HS512, secretKey.getBytes())
            .compact();

        return "Bearer " + token;
    }
}
