package org.example;

import org.example.domain.Position;
import org.example.domain.SymbolInfo;
import org.example.domain.ValueTypes;
import org.example.utils.SymbolTable;

public class Main {
    public static void main(String[] args) {

        SymbolTable symbolTable = new SymbolTable();

        String x = "x";
        String y = "y";
        String stingConst1 = "\"stingConst1\"";
        String stingConst2 = "\"stingConst2\"";
        int intConst1 = 123;
        int intConst2 = 456;

        Position p1 = symbolTable.put(new SymbolInfo(x, ValueTypes.IDENTIFIER));
        Position p2 = symbolTable.put(new SymbolInfo(y, ValueTypes.IDENTIFIER));
        Position p3 = symbolTable.put(new SymbolInfo(stingConst1, ValueTypes.STRING_CONST));
        Position p4 = symbolTable.put(new SymbolInfo(stingConst2, ValueTypes.STRING_CONST));
        Position p5 = symbolTable.put(new SymbolInfo(intConst1 + "", ValueTypes.INT_CONST));
        Position p6 = symbolTable.put(new SymbolInfo(intConst2 + "", ValueTypes.INT_CONST));

        System.out.println(symbolTable);

        assert p1.equals(symbolTable.getPosition(new SymbolInfo(x, ValueTypes.IDENTIFIER)).get());
        assert p2.equals(symbolTable.getPosition(new SymbolInfo(y, ValueTypes.IDENTIFIER)).get());
        assert p3.equals(symbolTable.getPosition(new SymbolInfo(stingConst1, ValueTypes.STRING_CONST)).get());
        assert p4.equals(symbolTable.getPosition(new SymbolInfo(stingConst2, ValueTypes.STRING_CONST)).get());
        assert p5.equals(symbolTable.getPosition(new SymbolInfo(intConst1 + "", ValueTypes.INT_CONST)).get());
        assert p6.equals(symbolTable.getPosition(new SymbolInfo(intConst2 + "", ValueTypes.INT_CONST)).get());

        try {
            symbolTable.put(new SymbolInfo(x, ValueTypes.IDENTIFIER));
            System.out.println("Test failed");
            return;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        assert symbolTable.getByPosition(p1).get().equals(new SymbolInfo(x, ValueTypes.IDENTIFIER));
        assert symbolTable.getByPosition(p4).get().equals(new SymbolInfo(stingConst2, ValueTypes.STRING_CONST));
        assert symbolTable.getByPosition(new Position(-1, -1)).isEmpty();

        assert symbolTable.size() == 6;

        assert symbolTable.contains(new SymbolInfo(x, ValueTypes.IDENTIFIER));

        System.out.println("All tests passed");
    }
}