package com.ericsson.exception;

import org.springframework.security.core.AuthenticationException;

public class OAuth2AuthenticationProcessingException extends AuthenticationException{

	public OAuth2AuthenticationProcessingException(String msg, Throwable t) {
		super(msg, t);
		// TODO Auto-generated constructor stub
	}
	public OAuth2AuthenticationProcessingException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}
	
	

}
