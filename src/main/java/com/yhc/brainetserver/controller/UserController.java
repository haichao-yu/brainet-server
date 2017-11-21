package com.yhc.brainetserver.controller;

/**
 * Created by yhc on 11/19/17.
 */

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yhc.brainetserver.model.ApplicationUser;
import com.yhc.brainetserver.repository.ApplicationUserRepository;

@RestController
@RequestMapping("/user")
public class UserController {

    private ApplicationUserRepository applicationUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(ApplicationUserRepository applicationUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.applicationUserRepository = applicationUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    private class SignupResult {
        /**
         * Must contains getter/setter to be returned as JSON document
         */
        private String username;
        private boolean res;
        public SignupResult(String username, boolean res) {
            this.username = username;
            this.res = res;
        }
        public String getUsername() {
            return username;
        }
        public void setUsername(String username) {
            this.username = username;
        }
        public boolean getRes() {
            return res;
        }
        public void setRes(boolean res) {
            this.res = res;
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupResult> signup(@RequestBody ApplicationUser user) {

        if (applicationUserRepository.findByUsername(user.getUsername()) != null) {
            // If the username already exists
            return new ResponseEntity<>(new SignupResult(user.getUsername(), false), HttpStatus.UNPROCESSABLE_ENTITY);
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        applicationUserRepository.save(user);
        return new ResponseEntity<>(new SignupResult(user.getUsername(), true), HttpStatus.OK);
    }
}
