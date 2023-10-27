package org.example.service;

import org.example.domain.ScannerErrorMessage;
import org.example.domain.ScannerMessage;
import org.example.domain.ScannerOkMessage;
import org.example.domain.SymbolInfo;
import org.example.domain.ValueTypes;
import org.example.utils.PIF;
import org.example.utils.SymbolTable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProgramScanner {

    String tokenFilePath = "src/main/resources/token.in";
    private final List<String> tokens = new ArrayList<>();
    private final List<String> reservedWords = new ArrayList<>();

    private String currentLine;
    private int currentLineIndex = 0;
    private int lineCount = 0;

    private final SymbolTable symbolTable = new SymbolTable();
    private final PIF pif = new PIF();

    private final Pattern regexForIdentifier = Pattern.compile("^([a-zA-Z_][a-zA-Z0-9_]*)");
    private final Pattern regexForNumber = Pattern.compile("^(\\d+)");
    private final Pattern regexForString = Pattern.compile("^\"([^\"]*)\"");

    private final Pattern regexForChar = Pattern.compile("^'([a-zA-Z0-9_])'");

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
        lineCount = 0;
        currentLineIndex = 0;
        currentLine = null;

        File programFile = new File(programFilePath);
        Scanner scanner = new Scanner(programFile);
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentLineIndex = 0;
            ScannerMessage message = interpretLine();
            if (message instanceof ScannerErrorMessage) {
                return message;
            }
        }

        return new ScannerOkMessage();
    }

    private ScannerMessage interpretLine() {
        ++lineCount;

        while (currentLineIndex < currentLine.length()) {
            skipWhiteSpaces();

            if (interpretIdentifier())
                continue;
            if (interpretConstant())
                continue;
            if (interpretToken())
                continue;

            return new ScannerErrorMessage(lineCount, currentLineIndex + 1);
        }

        return new ScannerOkMessage();
    }

    private void skipWhiteSpaces() {
        for (; currentLineIndex < currentLine.length(); ++currentLineIndex) {
            if (!Character.isWhitespace(currentLine.charAt(currentLineIndex))) {
                return;
            }
        }
    }

    private boolean interpretIdentifier() {
        Matcher matcher = regexForIdentifier.matcher(currentLine.substring(currentLineIndex));
        if (!matcher.find()) {
            return false;
        }
        String word = matcher.group(1);

        if (reservedWords.contains(word)) {
            pif.add(word, -1);
        } else {
            Integer position = symbolTable.put(new SymbolInfo(word, ValueTypes.IDENTIFIER));
            pif.add(word, position);
        }

        currentLineIndex += word.length();

        return true;
    }

    private boolean interpretConstant() {
        //String const
        Matcher matcher = regexForString.matcher(currentLine.substring(currentLineIndex));
        if (matcher.find()) {
            String word = matcher.group(1);
            Integer position = symbolTable.put(new SymbolInfo(word, ValueTypes.STRING_CONST));
            pif.add(word, position);
            currentLineIndex += word.length() + 2;
            return true;
        }

        //Char const
        matcher = regexForChar.matcher(currentLine.substring(currentLineIndex));
        if (matcher.find()) {
            String word = matcher.group(1);
            Integer position = symbolTable.put(new SymbolInfo(word, ValueTypes.CHAR_CONST));
            pif.add(word, position);
            currentLineIndex += word.length();
            return true;
        }

        //Number const
        matcher = regexForNumber.matcher(currentLine.substring(currentLineIndex));
        if (matcher.find()) {
            String word = matcher.group(1);
            Integer position = symbolTable.put(new SymbolInfo(word, ValueTypes.INT_CONST));
            pif.add(word, position);
            currentLineIndex += word.length();
            return true;
        }

        return false;
    }

    private boolean interpretToken() {
        for (String token : tokens) {
            if (currentLine.startsWith(token, currentLineIndex)) {
                pif.add(token, -1);
                currentLineIndex += token.length();
                return true;
            }
        }

        return false;
    }
}
