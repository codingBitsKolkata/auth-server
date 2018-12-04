//package com.orastays.authserver.security;
//
//import java.util.ArrayList;
//import java.util.Collection;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@Component
//@RefreshScope
//public class CustomAuthenticationProvider implements AuthenticationProvider {
//
//	private static final Logger logger = LogManager.getLogger(CustomAuthenticationProvider.class);
//	
//	@Override
//	public Authentication authenticate(Authentication authentication) {
//		
//		if (logger.isInfoEnabled()) {
//			logger.info("authenticate -- START");
//		}
//		
//		String username = authentication.getName();
//		String password = authentication.getCredentials().toString();
//		Collection<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();
//		
//		// TODO check the User Credentials in DB.
//		
//		String roleName = "";
//		grantedAuthoritiesList.add(new SimpleGrantedAuthority(roleName));
//		if (grantedAuthoritiesList.isEmpty())
//			throw new BadCredentialsException("Invalid Username Or Password");
//		return new UsernamePasswordAuthenticationToken(username,
//				new BCryptPasswordEncoder().encode(password), grantedAuthoritiesList);
//		
//	}
//
//	@Override
//	public boolean supports(Class<?> authentication) {
//		return authentication.equals(UsernamePasswordAuthenticationToken.class);
//	}
//}