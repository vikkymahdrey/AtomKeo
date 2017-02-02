package com.agiledge.atom.security;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.agiledge.atom.service.intfc.LoginService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	private static final Logger logger = Logger.getLogger(CustomAuthenticationProvider.class);
 
	@Autowired
    private LoginService loginService;
    
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        String password = authentication.getCredentials().toString();
        logger.debug("user name : "+ userName);
        boolean isAuthenticated = true;
       try{
         //isAuthenticated = loginService.validateUser(userName, password);
        }catch(Exception ex){
        	logger.error("error in authenticating user ",ex);
        	return null;
        }
        if(isAuthenticated){
            List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
            Authentication auth = new UsernamePasswordAuthenticationToken(userName, password, grantedAuths);
           
            return auth;
        }else{
        	return null;
        }
    }
 
    public boolean supports(Class<?> authentication) {
    	return true;
    }
}