package com.example.securityTest.servicee.impl;

import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.securityTest.Repository.RefreshTokenRepository;
import com.example.securityTest.entity.RefreshToken;
import com.example.securityTest.service.RefreshTokenService;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService{
	
	@Autowired
    private RefreshTokenRepository repo;

    @Override
    public RefreshToken createRefreshToken(String username) {

        RefreshToken token = new RefreshToken();
        token.setUsername(username);
        token.setToken(UUID.randomUUID().toString());
        token.setExpiryDate(Instant.now().plusSeconds(604800)); // 7 days

        return repo.save(token);
    }

    @Override
    public boolean isValid(RefreshToken token) {
        return token.getExpiryDate().isAfter(Instant.now());
    }

    @Override
    public RefreshToken findByToken(String token) {
        return repo.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid Refresh Token"));
    }

}
