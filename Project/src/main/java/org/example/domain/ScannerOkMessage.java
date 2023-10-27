package org.example.domain;

public class ScannerOkMessage extends ScannerMessage{
    public ScannerOkMessage(String message) {
        super(message);
    }

    public ScannerOkMessage() {
        super("Program is lexically correct");
    }
}
