package org.example.domain;

public abstract class ScannerMessage {
    private final String message;

    public ScannerMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
