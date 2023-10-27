package org.example.domain;

public class ScannerErrorMessage extends ScannerMessage {

    public ScannerErrorMessage(int line, int column) {
        super("Lexical error at line " + line + ":" + column);
    }

    public ScannerErrorMessage(String message) {
        super(message);
    }
}
