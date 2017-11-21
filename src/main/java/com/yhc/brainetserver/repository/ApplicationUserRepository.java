package com.yhc.brainetserver.repository;

/**
 * Created by yhc on 11/19/17.
 */

import org.springframework.data.jpa.repository.JpaRepository;

import com.yhc.brainetserver.model.ApplicationUser;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
    ApplicationUser findByUsername(String username);
}
