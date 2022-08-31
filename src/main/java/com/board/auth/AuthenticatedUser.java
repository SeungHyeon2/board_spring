package com.board.auth;

import java.util.Collection;
import java.util.Collections;

import javax.security.auth.Subject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class AuthenticatedUser implements Authentication {

	private String email;
	private String nickname;
	
	AuthenticatedUser(String email, String nickname){
		this.email = email;
		this.nickname = nickname;
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.nickname;
	}

	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return this.email;
	}
	
	@Override
	public boolean isAuthenticated() {
		// TODO Auto-generated method stub
		return !StringUtils.isEmpty(email) && !StringUtils.isEmpty(nickname);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return Collections.emptyList();
	}

	@Override
	public boolean implies(Subject subject) {
		return false;
	}
	
	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

}
