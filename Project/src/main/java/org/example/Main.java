package org.example;

import org.example.utils.SymbolTable;

public class Main {
    public static void main(String[] args) {

        SymbolTable symbolTable = new SymbolTable();

        String x = "x";
        String y = "y";

        symbolTable.putIdentifier(x);
        symbolTable.putIdentifier(y);

        String stingConst1 = "\"stingConst1\"";
        String stingConst2 = "\"stingConst2\"";

        symbolTable.putStringConst(stingConst1);
        symbolTable.putStringConst(stingConst2);

        int intConst1 = 123;
        int intConst2 = 456;

        symbolTable.putIntConst(String.valueOf(intConst1));
        symbolTable.putIntConst(String.valueOf(intConst2));

        System.out.println(symbolTable);
    }
}