package com.gary.demo.crud.config;

import java.util.Map;

import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.token.store.JwtClaimsSetVerifier;

public class CustomClaimVerifier implements JwtClaimsSetVerifier {
    @Override
    public void verify(Map<String, Object> claims) throws InvalidTokenException {
        /*for (Map.Entry<String, Object> entry : claims.entrySet()){
            System.out.println("Hello World Before5 " + entry.getKey() + ": " + entry.getValue() + " : " + entry.getValue().getClass());
        }*/
        Long exp = (Long) claims.get("exp");
        long expLong = 0;
        long currentUtc = System.currentTimeMillis();
        try{
            expLong = (exp.longValue()) * 1000;
        }
        catch(Exception e){
            expLong = 0;
        }
        if (expLong < currentUtc) {
            throw new InvalidTokenException("token expired");
        }
    }
}
