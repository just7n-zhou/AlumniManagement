package com.boostmytool.beststore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Component
public class UserDetailsLogger {

    private static final Logger LOGGER = Logger.getLogger(UserDetailsLogger.class.getName());

    @Autowired
    private InMemoryUserDetailsManager userDetailsManager;

    public void logAllUserDetails() {
        List<UserDetails> userDetailsList = findAllUsers();
        for (UserDetails userDetails : userDetailsList) {
            // Log or print out user details
            LOGGER.info("Username: " + userDetails.getUsername() + ", Password: " + userDetails.getPassword());
        }
    }

    private List<UserDetails> findAllUsers() {
        try {
            Field usersField = InMemoryUserDetailsManager.class.getDeclaredField("users");
            usersField.setAccessible(true);
            @SuppressWarnings("unchecked")
            Map<String, UserDetails> users = (Map<String, UserDetails>) usersField.get(userDetailsManager);
            return new ArrayList<>(users.values());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            LOGGER.severe("Failed to access user details: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
