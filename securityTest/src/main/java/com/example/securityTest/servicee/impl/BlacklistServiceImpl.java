package com.example.securityTest.servicee.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.securityTest.Repository.BlacklistRepository;
import com.example.securityTest.entity.BlacklistedToken;
import com.example.securityTest.service.BlacklistService;

@Service
public class BlacklistServiceImpl implements BlacklistService{
	
	@Autowired
    private BlacklistRepository repo;

    @Override
    public void blacklistToken(String token) {

        BlacklistedToken bt = new BlacklistedToken();
        bt.setToken(token);
        bt.setBlacklistedAt(java.time.Instant.now());

        repo.save(bt);
    }

    @Override
    public boolean isBlacklisted(String token) {
        return repo.existsByToken(token);
    }

}
