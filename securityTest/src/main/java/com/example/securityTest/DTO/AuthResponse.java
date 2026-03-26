//package com.example.securityTest.DTO;
//
//public class AuthResponse {
//	
//    private String token;
//    private String username;
//    private String role;
//    
//
//    public AuthResponse(String token, String username, String role) {
//        this.token = token;
//        this.username = username;
//        this.role = role;
//    }
//
//    public String getToken() { return token; }
//    public String getUsername() { return username; }
//    public String getRole() { return role; }
//
//
//}

package com.example.securityTest.DTO;

public class AuthResponse {

    private String accessToken;
    private String refreshToken;
    private String username;
    private String role;

    public AuthResponse(String accessToken, String refreshToken, String username, String role) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.username = username;
        this.role = role;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
}
