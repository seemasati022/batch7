package com.tejait.batch7.jwtsecurity;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {      
    	UserDB user=new UserDB("Teja IT","Your Java Stop!");
    	
    	  return new User(user.getUsername(),user.getPassword(),new ArrayList<>());
    }
}