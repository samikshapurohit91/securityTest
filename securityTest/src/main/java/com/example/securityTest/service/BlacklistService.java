package com.example.securityTest.service;

public interface BlacklistService {
	
	void blacklistToken(String token);

    boolean isBlacklisted(String token);

}
