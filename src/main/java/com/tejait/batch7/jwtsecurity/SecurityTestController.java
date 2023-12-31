package com.tejait.batch7.jwtsecurity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
//@RequestMapping("/jwt")
public class SecurityTestController {
	
	 @Autowired
	    private JWTUtility jwtUtility;

	    @Autowired
	    private AuthenticationManager authenticationManager;

	    @Autowired
	    private UserService userService;

	    @GetMapping("/")
	    public String home() {
	        return "Welcome to Teja IT!!";
	    }

	    @PostMapping("/authenticate")
	    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception{
System.out.println("authenticate method.."+jwtRequest.getUsername()+"  "+jwtRequest.getPassword());
	        try {
	            authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(
	                            jwtRequest.getUsername(),
	                            jwtRequest.getPassword()
	                    )
	            );
	        } catch (BadCredentialsException e) {
	            throw new Exception("INVALID_CREDENTIALS", e);
	        }

	        final UserDetails userDetails
	                = userService.loadUserByUsername(jwtRequest.getUsername());

	        final String token =
	                jwtUtility.generateToken(userDetails);

	        return  new JwtResponse(token);
	    }
	}

