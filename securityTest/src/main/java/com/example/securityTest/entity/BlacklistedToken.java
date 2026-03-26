package com.example.securityTest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "blackListedToken")
public class BlacklistedToken {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    private java.time.Instant blacklistedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public java.time.Instant getBlacklistedAt() {
		return blacklistedAt;
	}

	public void setBlacklistedAt(java.time.Instant blacklistedAt) {
		this.blacklistedAt = blacklistedAt;
	}
    
    

}
