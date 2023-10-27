package org.example.service;

import org.example.domain.ScannerErrorMessage;
import org.example.domain.ScannerMessage;
import org.example.domain.ScannerOkMessage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProgramScanner {

    String tokenFilePath = "src/main/resources/token.in";
    private final List<String> tokens = new ArrayList<>();
    private final List<String> reservedWords = new ArrayList<>();

    private final String tokenRegex = "([a-zA-Z]+)|([0-9]+)|([\\+\\-\\*\\/\\=\\<\\>\\!\\&\\|\\%\\^\\~\\?\\:\\;\\,\\(\\)\\[\\]\\{\\}])";

    private final String TOKENS = "TOKENS";
    private final String RESERVED_WORDS = "RESERVED_WORDS";

    public ProgramScanner(String tokenFilePath) throws FileNotFoundException {
        this.tokenFilePath = tokenFilePath;
        readTokens();
    }

    public ProgramScanner() throws FileNotFoundException {
        readTokens();
    }

    private void readTokens() throws FileNotFoundException {
        String currentCategory = null;

        File tokenFile = new File(tokenFilePath);
        Scanner scanner = new Scanner(tokenFile);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            switch (line) {
                case TOKENS:
                    currentCategory = TOKENS;
                    break;
                case RESERVED_WORDS:
                    currentCategory = RESERVED_WORDS;
                    break;
                default:
                    if (currentCategory == null) {
                        throw new RuntimeException("Invalid token file");
                    }
                    switch (currentCategory) {
                        case TOKENS:
                            tokens.add(line);
                            break;
                        case RESERVED_WORDS:
                            reservedWords.add(line);
                            break;
                    }
            }
        }
    }

    public ScannerMessage scan(String programFilePath) {
        try {
            return interpretProgram(programFilePath);
        } catch (FileNotFoundException e) {
            return new ScannerErrorMessage("File not found");
        }
    }

    private ScannerMessage interpretProgram(String programFilePath) throws FileNotFoundException {
        File programFile = new File(programFilePath);
        Scanner scanner = new Scanner(programFile);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            ScannerMessage message = interpretLine(line);
            if (message instanceof ScannerErrorMessage) {
                return message;
            }
        }

        return new ScannerOkMessage();
    }

    private ScannerMessage interpretLine(String line) {


        return new ScannerOkMessage();
    }
}
