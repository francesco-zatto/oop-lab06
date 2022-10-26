package it.unibo.exceptions.fakenetwork.exceptions;

import java.io.IOException;

public class NetworkException extends IOException {

    public NetworkException() {
        System.err.println("Network error: no response.");
    }

    public NetworkException(final String message) {
        System.err.println("Network error while sending message: " + message);
    }
    
}
