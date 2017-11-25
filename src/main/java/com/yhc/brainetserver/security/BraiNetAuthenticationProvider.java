package com.yhc.brainetserver.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.mathworks.toolbox.javabuilder.MWException;
import com.mathworks.toolbox.javabuilder.MWNumericArray;

import java.util.ArrayList;

import com.yhc.brainetserver.model.ApplicationUser;
import com.yhc.brainetserver.repository.ApplicationUserRepository;
import com.yhc.brainetserver.util.Base64DoubleEncoder;

/**
 * A custom authentication provider
 */
public class BraiNetAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String encodedSignal = authentication.getCredentials().toString();

        ApplicationUser user = applicationUserRepository.findByUsername(username);

        if (user != null) {

            // Get target feature from database
            String encodedTargetFeature = user.getPassword();
            double[] targetFeature = Base64DoubleEncoder.decode(encodedTargetFeature);

            // Get test feature from the encoded signal
            double[] testFeature = null;
            try {
                double[] signal = Base64DoubleEncoder.decode(encodedSignal);
                FeatureExt.Class1 featureExtractor = new FeatureExt.Class1();
                Object[] ret = featureExtractor.FeatureExt(1, signal);
                testFeature = ((MWNumericArray) ret[0]).getDoubleData();
            } catch (MWException e) {
                e.printStackTrace();
            }

            // Compare the test feature with the target feature
            boolean isAuthenticated = false;
            try {
                Comparator.Class1 featureComparator = new Comparator.Class1();
                Object[] ret = featureComparator.Comparator(1, targetFeature, testFeature, 120);
                double[] res = ((MWNumericArray) ret[0]).getDoubleData();
                isAuthenticated = ((int) res[0] == 1);
            } catch (MWException e) {
                e.printStackTrace();
            }

            // Authentication succeed
            if (isAuthenticated) {
                return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), new ArrayList<>());
            }
        }

        // Authentication failed
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
