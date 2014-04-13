package com.goparty.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.goparty.data.model.User;
import com.goparty.data.repository.IUserDataRepository;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private IUserDataRepository userDataRepository;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = userDataRepository.findByUsername(username);
		if(user==null){
			throw new UsernameNotFoundException("User is not found by username:"+username);
		}
		
		return new UserDetailsImpl(user);
	}
}
