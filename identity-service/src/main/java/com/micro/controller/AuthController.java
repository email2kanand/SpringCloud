package com.micro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.micro.dto.AuthRequest;
import com.micro.entity.UserCredential;
import com.micro.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private AuthenticationManager  authenticationManager;
	
	
	@PostMapping("/register")
	public String addNewUser(@RequestBody UserCredential user) {
		authService.save(user);
		return "User added to the system";
	}
	
	@PostMapping("/token")
	public String getToken(@RequestBody AuthRequest authRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		if(authentication.isAuthenticated()) {
		 return authService.generateToken(authRequest.getUsername());
		}else {
			throw new RuntimeException("Invalid Access");
		} 
	}
	
	@GetMapping("/validate")
	public String validateToken(@RequestParam String token) {
		authService.validateToken(token);
		return "Token is validated";
	}



}
