package com.github.espress91.decentralizedShortSale.util;

public class MessageUtils {

    private static MessageUtils instance = new MessageUtils();

    public static MessageUtils getInstance() {
        return instance;
    }

    public String getMessage(String message) {
        return message;
    }

    public String getMessage(String message, Object... params) {
        return String.format(message, params);
    }
}
