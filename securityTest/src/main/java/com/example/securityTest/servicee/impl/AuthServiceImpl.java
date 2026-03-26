package com.example.securityTest.servicee.impl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.securityTest.DTO.AuthRequest;
import com.example.securityTest.DTO.AuthResponse;
import com.example.securityTest.Repository.UserRepository;
import com.example.securityTest.entity.RefreshToken;
import com.example.securityTest.entity.Role;
import com.example.securityTest.entity.User;
import com.example.securityTest.security.JwtUtil;
import com.example.securityTest.service.AuthService;
import com.example.securityTest.service.RefreshTokenService;

@Service
public class AuthServiceImpl implements AuthService{
	
	@Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder encoder;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private RefreshTokenService refreshTokenService;

	
    @Override
    public String register(AuthRequest request) {

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setRole(Role.ROLE_USER);

        repo.save(user);

        return "User Registered Successfully";
    }

//    @Override
//    public String login(AuthRequest request) {
//
//        User user = repo.findByUsername(request.getUsername())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        if (encoder.matches(request.getPassword(), user.getPassword())) {
//            return "Login Successful";
//        } else {
//            throw new RuntimeException("Invalid Password");
//        }
//    }

//    @Override
//    public String login(AuthRequest request) {
//
//        User user = repo.findByUsername(request.getUsername())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        if (encoder.matches(request.getPassword(), user.getPassword())) {
//            return jwtUtil.generateToken(user.getUsername());   // 🔥 TOKEN
//        } else {
//            throw new RuntimeException("Invalid Password");
//        }
//    }

    
//    @Override
//    public AuthResponse login(AuthRequest request) {
//
//        User user = repo.findByUsername(request.getUsername())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        if (encoder.matches(request.getPassword(), user.getPassword())) {
//
//            String token = jwtUtil.generateToken(user.getUsername());
//
////            return new AuthResponse(
////                    token,
////                    user.getUsername(),
////                    user.getRole().name()
////            );
//            
//           
//
//        } else {
//            throw new RuntimeException("Invalid Password");
//        }
//    }
    
    @Override
    public AuthResponse login(AuthRequest request) {

        User user = repo.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (encoder.matches(request.getPassword(), user.getPassword())) {

           
           //String accessToken = jwtUtil.generateToken(user.getUsername());
           
           String accessToken = jwtUtil.generateToken(
                   user.getUsername(),
                   user.getRole().name()
           );
        	
        	

     
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getUsername());

           
            return new AuthResponse(
                    accessToken,
                    refreshToken.getToken(),
                    user.getUsername(),
                    user.getRole().name()
            );

        } else {
            throw new RuntimeException("Invalid Password");
        }
    }


}
