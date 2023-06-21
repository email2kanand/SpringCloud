package com.micro.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.micro.entity.UserCredential;
import com.micro.repository.UserCredentialRepository;

@Component
public class CustomUserDetailService implements UserDetailsService {
	
	@Autowired
	private UserCredentialRepository userCredentialRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserCredential> optional = userCredentialRepository.findByName(username);
		return optional.map(CustomUserDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("user not found with name:" + username));
	}

}
