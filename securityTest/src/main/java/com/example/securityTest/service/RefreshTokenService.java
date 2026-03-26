package com.example.securityTest.service;

import com.example.securityTest.entity.RefreshToken;

public interface RefreshTokenService {
	
	RefreshToken createRefreshToken(String username);

    boolean isValid(RefreshToken token);

    RefreshToken findByToken(String token);

}
