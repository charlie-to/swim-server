package com.example.swimServer;

import org.springframework.stereotype.Component;

@Component
public class WelcomeMessage {
    public String getWelcomeMessage() {
        return "Welcome to the SwimServer!";
    }
}
