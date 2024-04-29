package com.boostmytool.beststore.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.boostmytool.beststore.services.UserDetailsLogger;

@Component
public class UserLoggerRunner implements CommandLineRunner {

    @Autowired
    private UserDetailsLogger userDetailsLogger;

    @Override
    public void run(String... args) {
        userDetailsLogger.logAllUserDetails();
    }
}
