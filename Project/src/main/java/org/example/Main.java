package org.example;

import org.example.domain.ScannerMessage;
import org.example.domain.SymbolInfo;
import org.example.domain.ValueTypes;
import org.example.service.ProgramScanner;
import org.example.utils.SymbolTable;

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

//        SymbolTable symbolTable = new SymbolTable();
//
//        String x = "x";
//        String y = "y";
//        String stingConst1 = "\"stingConst1\"";
//        String stingConst2 = "\"stingConst2\"";
//        int intConst1 = 123;
//        int intConst2 = 456;
//
//        Integer p1 = symbolTable.put(new SymbolInfo(x, ValueTypes.IDENTIFIER));
//        Integer p2 = symbolTable.put(new SymbolInfo(y, ValueTypes.IDENTIFIER));
//        Integer p3 = symbolTable.put(new SymbolInfo(stingConst1, ValueTypes.STRING_CONST));
//        Integer p4 = symbolTable.put(new SymbolInfo(stingConst2, ValueTypes.STRING_CONST));
//        Integer p5 = symbolTable.put(new SymbolInfo(intConst1 + "", ValueTypes.INT_CONST));
//        Integer p6 = symbolTable.put(new SymbolInfo(intConst2 + "", ValueTypes.INT_CONST));
//
//        System.out.println(symbolTable);
//
//        assert p1.equals(symbolTable.getKey(new SymbolInfo(x, ValueTypes.IDENTIFIER)).get());
//        assert p2.equals(symbolTable.getKey(new SymbolInfo(y, ValueTypes.IDENTIFIER)).get());
//        assert p3.equals(symbolTable.getKey(new SymbolInfo(stingConst1, ValueTypes.STRING_CONST)).get());
//        assert p4.equals(symbolTable.getKey(new SymbolInfo(stingConst2, ValueTypes.STRING_CONST)).get());
//        assert p5.equals(symbolTable.getKey(new SymbolInfo(intConst1 + "", ValueTypes.INT_CONST)).get());
//        assert p6.equals(symbolTable.getKey(new SymbolInfo(intConst2 + "", ValueTypes.INT_CONST)).get());
//
//        try {
//            symbolTable.put(new SymbolInfo(x, ValueTypes.IDENTIFIER));
//            System.out.println("Test failed");
//            return;
//        } catch (IllegalArgumentException e) {
//            System.out.println(e.getMessage());
//        }
//
//        assert symbolTable.getByKey(p1).get().equals(new SymbolInfo(x, ValueTypes.IDENTIFIER));
//        assert symbolTable.getByKey(p4).get().equals(new SymbolInfo(stingConst2, ValueTypes.STRING_CONST));
//        assert symbolTable.getByKey(-1).isEmpty();
//
//        assert symbolTable.size() == 6;
//
//        assert symbolTable.contains(new SymbolInfo(x, ValueTypes.IDENTIFIER));
//
//        System.out.println("All tests passed");
    }
}