package com.example.securityTest.service;

import com.example.securityTest.DTO.AuthRequest;
import com.example.securityTest.DTO.AuthResponse;

public interface AuthService {
	  String register(AuthRequest request);
	    //String login(AuthRequest request);
	    
	    public AuthResponse login(AuthRequest request);
	
}
