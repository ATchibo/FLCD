package org.example;

import org.example.utils.SymbolTable;

public class Main {
    public static void main(String[] args) {

        SymbolTable<String> identifiersSymbolTable = new SymbolTable<>();
        SymbolTable<String> stringConstSymbolTable = new SymbolTable<>();
        SymbolTable<Integer> intConstSymbolTable = new SymbolTable<>();

        identifiersSymbolTable.put("x");
        identifiersSymbolTable.put("y");
        identifiersSymbolTable.put("z");

        stringConstSymbolTable.put("\"Hello\"");
        stringConstSymbolTable.put("\"World\"");

        intConstSymbolTable.put(1);
        intConstSymbolTable.put(2);

        System.out.println("My symbol table: ");
        System.out.println("x: " + identifiersSymbolTable.get("x").get());
        System.out.println("y: " + identifiersSymbolTable.get("y").get());
        System.out.println("z: " + identifiersSymbolTable.get("z").get());

        System.out.println("Hello: " + stringConstSymbolTable.get("\"Hello\"").get());
        System.out.println("World: " + stringConstSymbolTable.get("\"World\"").get());

        System.out.println("1: " + intConstSymbolTable.get(1).get());
        System.out.println("2: " + intConstSymbolTable.get(2).get());

        System.out.println("Is x present? " + identifiersSymbolTable.isKeyPresent("x"));
        System.out.println("Is X present? " + identifiersSymbolTable.isKeyPresent("X"));

        System.out.println("Is \"Hello\" present? " + stringConstSymbolTable.isKeyPresent("\"Hello\""));
        System.out.println("Type of \"Hello\": " + stringConstSymbolTable.getNode("\"Hello\"").get().getKey().getClass().getSimpleName());

        System.out.println("Is 1 present? " + intConstSymbolTable.isKeyPresent(1));
        System.out.println("Type of 1: " + intConstSymbolTable.getNode(1).get().getKey().getClass().getSimpleName());
    }
}