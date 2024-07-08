
package com.josh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.josh.config.JwtProvider;
import com.josh.model.User;
import com.josh.repo.UserRepo;
import com.josh.request.LoginRequest;
import com.josh.response.AuthResponse;
import com.josh.services.CustomUserDetielsService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    CustomUserDetielsService customUserDetielsService;

    @PostMapping("/signup")
    public AuthResponse createUser(@RequestBody User user) throws Exception {
        User isExist = userRepo.findByEmail(user.getEmail());
        if (isExist != null) {
            throw new Exception("Email already exists");
        }

        User newUser = new User();
        newUser.setFirstName(user.getFirstName());
        newUser.setLastname(user.getLastname());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setGender(user.getGender());

        User savedUser = userRepo.save(newUser);

        // Generate JWT token
        String token = JwtProvider.generateJwtToken(savedUser.getEmail());

        AuthResponse authResponse = new AuthResponse(token, "Registration Successful");
        return authResponse;
    }
    
    @PostMapping("/login")
    public AuthResponse signin (@RequestBody LoginRequest loginRequest) {
    	
    	Authentication authentication = authenticate(loginRequest.getEmail() , loginRequest.getPassword());
    	String token = JwtProvider.generateJwtToken(authentication.getName());

        AuthResponse authResponse = new AuthResponse(token, "Login Successful");
        return authResponse;
    }


	private Authentication authenticate(String email, String password) {
		// TODO Auto-generated method stub
		UserDetails userDetails = customUserDetielsService.loadUserByUsername(email);
		
		if(userDetails == null) {
			throw new BadCredentialsException("Invalid User ");
		}
		
		if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("Wrong Password ");
		}
		return new UsernamePasswordAuthenticationToken(userDetails , null , userDetails.getAuthorities());
	}
}
