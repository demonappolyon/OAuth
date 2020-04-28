package com.ericsson.security;



import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ericsson.exception.ResourceNotFoundException;
import com.ericsson.model.User;
import com.ericsson.repository.UserRepository;


@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	@Transactional
	public UserDetails loadUserByUsername(String email)
	throws UsernameNotFoundException{
		User user = userRepository.findByEmail(email)
				.orElseThrow(() ->
				new UsernameNotFoundException("User not found with given email id")
				);
		
		return UserPrincipal.create(user);
	}
	@Transactional
	public UserDetails loadUSerById(Long id) {
		User user = userRepository.findById(id).orElseThrow(
				()-> new ResourceNotFoundException("User", "id", id));
		return UserPrincipal.create(user);
	}
}
