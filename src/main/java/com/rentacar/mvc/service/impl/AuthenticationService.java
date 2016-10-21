package com.rentacar.mvc.service.impl;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 
 * Util Class to check if user is logged and name of logged user
 *
 */
public class AuthenticationService {
	
		
	public static String getLoggedUsername(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
	
	public static boolean isUserLogged(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		boolean authorized;
		authorized = authorities.contains(new SimpleGrantedAuthority("ROLE_USER"));
		if (authorized == true){
			return true;
		}else 
		return authorized = authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}
}
