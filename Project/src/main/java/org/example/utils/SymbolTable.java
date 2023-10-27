package org.example.utils;

import org.example.domain.SymbolInfo;

import java.util.Optional;

public class SymbolTable {
    private final HashTable<Integer, SymbolInfo> symbolTableEntries;
    private int count;

    public SymbolTable(int capacity) {
        symbolTableEntries = new HashTable<>(capacity);
        count = 0;
    }

    public SymbolTable() {
        this(100);
    }

    public Integer put(SymbolInfo value) {
        return symbolTableEntries.put(count++, value);
    }

    public Optional<Integer> getKey(SymbolInfo value) {
        return symbolTableEntries.getKey(value);
    }

    public Optional<SymbolInfo> getByKey(Integer key) {
        return symbolTableEntries.getElement(key);
    }

    public boolean contains(SymbolInfo value) {
        return symbolTableEntries.isElementPresent(value);
    }

    public boolean containsKey(Integer key) {
        return symbolTableEntries.contains(key);
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

    public void clear() {
        symbolTableEntries.clear();
    }
}
