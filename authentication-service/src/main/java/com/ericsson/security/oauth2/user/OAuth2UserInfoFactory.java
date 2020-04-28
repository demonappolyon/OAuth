package com.ericsson.security.oauth2.user;


import java.util.Map;

import com.ericsson.exception.OAuth2AuthenticationProcessingException;
import com.ericsson.model.AuthProvider;

public class OAuth2UserInfoFactory {
	
	public static OAuth2UserInfo getOAuth2UserInfo(String registerationId,Map<String,Object> attributes) {
		if(registerationId.equalsIgnoreCase(AuthProvider.google.toString())) {
			return new GoogleOAuth2UserInfo(attributes);
		} else if(registerationId.equalsIgnoreCase(AuthProvider.facebook.toString())) {
			return new FacebookOAuth2UserInfo(attributes);
		}else {
			throw new OAuth2AuthenticationProcessingException("Sorry! LOgin with" + registerationId + " is not supported yet.");
		}

	}

}
