package com.example.bogdan.dou_feed.api;

import java.io.IOException;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 30.06.16
 */
public class NetworkException extends IOException {

    public NetworkException() {

    }

    public NetworkException(String message) {
        super(message);
    }
}
