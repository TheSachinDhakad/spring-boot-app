//package com.josh.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.josh.config.JwtProvider;
//import com.josh.model.User;
//import com.josh.repo.UserRepo;
//import com.josh.response.AuthResponse;
//import com.josh.services.UserService;
//
//@RestController
//@RequestMapping("/auth")
//public class AuthController {
//	@Autowired
//	private UserService userService;
//	
//	@Autowired
//	private UserRepo userRepo ;
//	
//	@Autowired
//	private PasswordEncoder passwordEncoder;
//
//
//	@PostMapping("/signup")
//	public AuthResponse createUser(@RequestBody User user) throws Exception {
//
//		User isExist = userRepo.findByEmail(user.getEmail());
//		if(isExist != null) {
//			throw new Exception("Email already exist");
//		}
//		
//		User newUser = new User();
//		newUser.setFirstName(user.getFirstName());
//		newUser.setLastname(user.getLastname());
//		newUser.setEmail(user.getEmail());
//		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
//		newUser.setGender(user.getGender());
//		
//
//		User saveUser = userRepo.save(newUser);
//		
//		Authentication authentication = new UsernamePasswordAuthenticationToken(saveUser.getEmail(), saveUser.getPassword());
//		
//		String token = JwtProvider.getEmailFormJwtToken(authentication);
//		
//		AuthResponse authResponse = new AuthResponse(token , "Register Successfull..");
//		return authResponse;
//	}
//	
//}

package com.josh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.josh.config.JwtProvider;
import com.josh.model.User;
import com.josh.repo.UserRepo;
import com.josh.response.AuthResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
}
