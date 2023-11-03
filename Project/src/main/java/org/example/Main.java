package org.example;

import org.example.domain.ScannerMessage;
import org.example.service.ProgramScanner;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {

        ProgramScanner scanner = null;
        try {
            scanner = new ProgramScanner();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        ScannerMessage scannerMessage = scanner.scan("src/main/resources/p1.txt");
        System.out.println("P1: " + scannerMessage.getMessage());

        scannerMessage = scanner.scan("src/main/resources/p2.txt");
        System.out.println("P2: " + scannerMessage.getMessage());

        scannerMessage = scanner.scan("src/main/resources/p3.txt");
        System.out.println("P3: " + scannerMessage.getMessage());

        scannerMessage = scanner.scan("src/main/resources/p1err.txt");
        System.out.println("P1err: " + scannerMessage.getMessage());
    }
}