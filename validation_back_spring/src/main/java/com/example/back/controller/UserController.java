package com.example.back.controller;

import com.example.back.model.User;
import com.example.back.model.UserPublicInfo;
import com.example.back.reponse.AuthResponse;
import com.example.back.repository.UserRepository;
import com.example.back.securityConfig.JwtProvider;
import com.example.back.service.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private UserServiceImplementation customUserDetails;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws Exception {
        String email = user.getEmail();
        String password = user.getPassword();
        String fullName = user.getFullName();
        String mobile = user.getMobile();
        String role = user.getRole();

        User isEmailExist = userRepository.findByEmail(email);
        if (isEmailExist != null) {
            throw new Exception("Email Is Already Used With Another Account");
        }
        User createdUser = new User();
        createdUser.setEmail(email);
        createdUser.setFullName(fullName);
        createdUser.setMobile(mobile);
        createdUser.setRole(role);
        createdUser.setPassword(passwordEncoder.encode(password));

        User savedUser = userRepository.save(createdUser);
        userRepository.save(savedUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(email,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = JwtProvider.generateToken(authentication);


        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("Register Success");
        authResponse.setStatus(true);
        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);

    }





    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody User loginRequest) {
        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();



        Authentication authentication = authenticate(username,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = JwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse();
        User user = userRepository.findByEmail(username);
        UserPublicInfo userInfo = new UserPublicInfo();
        userInfo.setFullName(user.getFullName());
        userInfo.setEmail(user.getEmail());
        userInfo.setId(user.getId());
        userInfo.setRole(user.getRole());
        authResponse.setMessage("Login success");
        authResponse.setJwt(token);
        authResponse.setStatus(true);
        authResponse.setUser(userInfo);

        return new ResponseEntity<>(authResponse,HttpStatus.OK);
    }




    private Authentication authenticate(String username, String password) {



        UserDetails userDetails = customUserDetails.loadUserByUsername(username);


        if(userDetails == null) {

            throw new BadCredentialsException("Invalid username and password");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())) {

            throw new BadCredentialsException("Invalid password");

        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

    }
}
