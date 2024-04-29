package com.boostmytool.beststore.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.boostmytool.beststore.services.UserDetailsLogger;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class ConsoleCommandListener implements CommandLineRunner {

    @Autowired
    private UserDetailsLogger userDetailsLogger;

    @Override
    public void run(String... args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("Enter commands (type 'logusers' to log user details, 'exit' to quit):");

        while ((line = reader.readLine()) != null) {
            switch (line.trim()) {
                case "logusers":
                    userDetailsLogger.logAllUserDetails();
                    break;
                case "exit":
                    System.out.println("Exiting command listener.");
                    return;
                default:
                    System.out.println("Unknown command.");
            }
            System.out.println("Enter commands (type 'logusers' to log user details, 'exit' to quit):");
        }
    }
}
