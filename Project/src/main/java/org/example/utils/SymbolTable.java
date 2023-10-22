package org.example.utils;

import org.example.domain.Position;
import org.example.domain.SymbolInfo;

import java.util.Optional;

public class SymbolTable {
    private final HashTable<SymbolInfo> symbolTableEntries;

    public SymbolTable(int capacity) {
        symbolTableEntries = new HashTable<>(capacity);
    }

    public SymbolTable() {
        this(100);
    }

    public Position put(SymbolInfo value) {
        return symbolTableEntries.put(value);
    }

    public Optional<Position> getPosition(SymbolInfo key) {
        return symbolTableEntries.getPosition(key);
    }

    public Optional<SymbolInfo> getByPosition(Position key) {
        return symbolTableEntries.getByPosition(key);
    }

    public boolean contains(SymbolInfo key) {
        return symbolTableEntries.isElementPresent(key);
    }

    public int size() {
        return symbolTableEntries.size();
    }

    @Override
    public String toString() {
        return "ST\n" +
                "Size: " + symbolTableEntries.size() + "\n" +
                symbolTableEntries;
    }
}
