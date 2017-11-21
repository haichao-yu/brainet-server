package com.yhc.brainetserver.service;

/**
 * Created by yhc on 11/21/17.
 */

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.yhc.brainetserver.model.ApplicationUser;
import com.yhc.brainetserver.repository.ApplicationUserRepository;

import static java.util.Collections.emptyList;

/**
 * Spring Security doesn't come with a concrete implementation of UserDetailsService that we could use out of the box with our in-memory database.
 * Therefore, we create a new class called UserDetailsServiceImpl in the com.yhc.brainetserver.service package to provide one.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private ApplicationUserRepository applicationUserRepository;

    public UserDetailsServiceImpl(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    /**
     * When a user tries to authenticate, this method receives the username, searches the database for a record containing it, and (if found) returns an instance of User.
     * The properties of this instance (username and password) are then checked against the credentials passed by the user in the login request.
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(applicationUser.getUsername(), applicationUser.getPassword(), emptyList());
    }
}
