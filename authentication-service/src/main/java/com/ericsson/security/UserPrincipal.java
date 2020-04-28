package com.ericsson.security;

import java.util.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.ericsson.model.User;

public class UserPrincipal implements OAuth2User, UserDetails{
 private Long id;
 private String email;
 private String password;
 private Collection<? extends GrantedAuthority> authorities;
 private Map<String, Object> attributes;
 
 public UserPrincipal(Long id, String email, String password, Collection<? extends GrantedAuthority> authorities) {
	
	this.id = id;
	this.email = email;
	this.password = password;
	this.authorities = authorities;
}
 
 public static UserPrincipal create (User user) {
	 List<GrantedAuthority> authorities =Collections.
			 singletonList(new SimpleGrantedAuthority("ROLE_USER"));
	 
	 return new UserPrincipal(
			 user.getId(),
			 user.getEmail(),
			 user.getPassword(),
			 authorities);
 }


public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public void setPassword(String password) {
	this.password = password;
}

public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
	this.authorities = authorities;
}

public static UserPrincipal create(User user, Map<String, Object> attributes) {
 UserPrincipal userPrincipal = UserPrincipal.create(user);
 userPrincipal.setAttributes(attributes);
 return userPrincipal;
}

@Override
public Map<String, Object> getAttributes() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public String getName() {
	// TODO Auto-generated method stub
	return null;
}

public void setAttributes(Map<String, Object> attributes) {
	this.attributes = attributes;
}

@Override
public String getPassword() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public String getUsername() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public boolean isAccountNonExpired() {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean isAccountNonLocked() {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean isCredentialsNonExpired() {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean isEnabled() {
	// TODO Auto-generated method stub
	return false;
}
}