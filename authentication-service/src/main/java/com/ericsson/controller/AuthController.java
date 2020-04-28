package com.ericsson.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ericsson.exception.BadRequestException;
import com.ericsson.model.AuthProvider;
import com.ericsson.model.User;
import com.ericsson.payload.ApiResponse;
import com.ericsson.payload.AuthResponse;
import com.ericsson.payload.LoginRequest;
import com.ericsson.payload.SignUpRequest;
import com.ericsson.repository.UserRepository;
import com.ericsson.security.TokenProvider;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private TokenProvider tokenProvider;
	
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
		Authentication authentication =authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(
					loginRequest.getEmail(),
					loginRequest.getPassword()
					)	
			);
	SecurityContextHolder.getContext().setAuthentication(authentication);
	String token =tokenProvider.createToken(authentication);
	return ResponseEntity.ok(new AuthResponse(token));
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest){
		if(userRepository.existByEmail(signUpRequest.getEmail())) {
			throw new BadRequestException("Email address already in use");
		}
		User user =new User();
		user.setEmail(signUpRequest.getEmail());
		user.setName(signUpRequest.getName());
		user.setProvider(AuthProvider.local);
		user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
		
		User result=userRepository.save(user);
		
		URI location = ServletUriComponentsBuilder.
				fromCurrentContextPath().path("/user/me")
				.buildAndExpand(result.getId()).toUri();
		return ResponseEntity.created(location)
				.body(new ApiResponse(true, "User Registered Successfully"));
	}
}
