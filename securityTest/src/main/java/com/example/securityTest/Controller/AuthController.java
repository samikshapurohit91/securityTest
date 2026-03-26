package com.example.securityTest.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.securityTest.DTO.AuthRequest;
import com.example.securityTest.DTO.AuthResponse;
import com.example.securityTest.Repository.UserRepository;
import com.example.securityTest.entity.RefreshToken;
import com.example.securityTest.entity.User;
import com.example.securityTest.security.JwtUtil;
import com.example.securityTest.service.AuthService;
import com.example.securityTest.service.BlacklistService;
import com.example.securityTest.service.RefreshTokenService;

import jakarta.servlet.http.HttpServletRequest;

//import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	 @Autowired
	    private AuthService service;
	 
	 @Autowired
	 private RefreshTokenService refreshTokenService;
	 
	 @Autowired
	 private JwtUtil jwtUtil;
	 
	 @Autowired
	 private BlacklistService blacklistService;
	 
	 @Autowired
	 UserRepository userRepository;

//	    @PostMapping("/register")
//	    public ResponseEntity<String> register(@RequestBody AuthRequest request) {
//	        return ResponseEntity.ok(service.register(request));
//	    }
	 
	 @PostMapping("/register")
	 public String register(@RequestBody AuthRequest request) {
	     return service.register(request);
	 }

//	    @PostMapping("/login")
//	    public ResponseEntity<String> login(@RequestBody AuthRequest request) {
//	        return ResponseEntity.ok(service.login(request));
//	    }
	 
	 @PostMapping("/login")
	 public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
	     return ResponseEntity.ok(service.login(request));
	 }
	 

//	    @PostMapping("/refresh")
//	    public ResponseEntity<?> refresh(@RequestParam String refreshToken) {
//
//	        RefreshToken token = refreshTokenService.findByToken(refreshToken);
//
//	        if (!refreshTokenService.isValid(token)) {
//	            throw new RuntimeException("Refresh Token Expired");
//	        }
//
//	        String newAccessToken = jwtUtil.generateToken(token.getUsername());
//	        
//	        
//
//	        return ResponseEntity.ok(newAccessToken);
//	    }
	    
	 @PostMapping("/refresh")
	 public ResponseEntity<?> refresh(@RequestParam String refreshToken) {

	     RefreshToken token = refreshTokenService.findByToken(refreshToken);

	     if (!refreshTokenService.isValid(token)) {
	         throw new RuntimeException("Refresh Token Expired");
	     }

	     User user = userRepository.findByUsername(token.getUsername())
	             .orElseThrow(() -> new RuntimeException("User not found"));

	     String newAccessToken = jwtUtil.generateToken(
	             user.getUsername(),
	             user.getRole().name()
	     );

	     return ResponseEntity.ok(newAccessToken);
	 } 
	    @PostMapping("/logout")
	    public ResponseEntity<String> logout(HttpServletRequest request) {

	        String authHeader = request.getHeader("Authorization");

	        if (authHeader != null && authHeader.startsWith("Bearer ")) {
	            String token = authHeader.substring(7);

	            blacklistService.blacklistToken(token);
	        }

	        return ResponseEntity.ok("Logged out successfully");
	    }
	    
//	    @GetMapping("/user/data")
//	    @PreAuthorize("hasRole('USER')")
//	    public String userData() {
//	        return "User data";
//	    }
//
//	    @GetMapping("/admin/data")
//	    @PreAuthorize("hasRole('ADMIN')")
//	    public String adminData() {
//	        return "Admin data";
//	    }
	}


