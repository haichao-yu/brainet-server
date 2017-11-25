package com.yhc.brainetserver.controller;

/**
 * Created by yhc on 11/19/17.
 */

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yhc.brainetserver.model.ApplicationUser;
import com.yhc.brainetserver.repository.ApplicationUserRepository;
import com.yhc.brainetserver.util.Base64DoubleEncoder;

import com.mathworks.toolbox.javabuilder.MWException;
import com.mathworks.toolbox.javabuilder.MWNumericArray;

@RestController
@RequestMapping("/user")
public class UserController {

    private ApplicationUserRepository applicationUserRepository;

    public UserController(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
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

        // Convert the encoded signal (string) to the original signal (double array)
        double[] signal = Base64DoubleEncoder.decode(user.getPassword());

        // Extract the feature from the original signal
        double[] feature = null;
        try {
            FeatureExt.Class1 featureExtractor = new FeatureExt.Class1();
            Object[] ret = featureExtractor.FeatureExt(1, signal);
            feature = ((MWNumericArray) ret[0]).getDoubleData();
        } catch (MWException e) {
            e.printStackTrace();
        }

        // Encode the feature (double array) into a string
        String encodedFeature = Base64DoubleEncoder.encode(feature);

        // Save the user (username, encodedFeature) into PostgreSQL DB
        user.setPassword(encodedFeature);
        applicationUserRepository.save(user);
        return new ResponseEntity<>(new SignupResult(user.getUsername(), true), HttpStatus.OK);
    }
}
