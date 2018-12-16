package edu.kma.iot.service;

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

import edu.kma.iot.dao.UserDAO;
import edu.kma.iot.dao.model.User;

@Component("userAuthPro")
public class UserAuthProvider implements AuthenticationProvider{
	private static final Logger LOG = Logger.getLogger(UserAuthProvider.class);
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName().toString();
		User user = userDAO.getUser(username);
		if(user == null) return null;
		LOG.info("--------------------- > Found " + user + " by " +username +"<-----------------------");
		if(!user.getPassword().equals(authentication.getCredentials())) return null;
		return successful(username, authentication.getCredentials().toString());
	}

	private Authentication successful(String username, String password) {
		List<GrantedAuthority> grantedAuths = new ArrayList<>();
		grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
		return new UsernamePasswordAuthenticationToken(username, password, grantedAuths);
	}

	@Override
	public boolean supports(Class<?> auth) {
		return auth.equals(UsernamePasswordAuthenticationToken.class);
	}

}
