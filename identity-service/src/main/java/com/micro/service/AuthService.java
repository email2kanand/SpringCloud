package com.micro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.micro.entity.UserCredential;
import com.micro.repository.UserCredentialRepository;

@Service
public class AuthService {
	
	@Autowired
	private UserCredentialRepository userCredentialRepository;
	
	@Autowired
	private JwtService jwtService; 
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public UserCredential save(UserCredential userCredential) {
		userCredential.setPassword(passwordEncoder.encode(userCredential.getPassword()));
		return userCredentialRepository.save(userCredential);
	}
	
	
	public String generateToken(String userName) {
		return jwtService.generateToken(userName);
	}


	public void validateToken(String token) {
		jwtService.validateToken(token);
	}

}
