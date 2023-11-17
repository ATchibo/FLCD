package org.example.utils;

import org.example.domain.SymbolInfo;

import java.util.Optional;

public class SymbolTable {
    private final HashTable<String, Integer> symbolTableEntries;
    private int count;

    public SymbolTable(int capacity) {
        symbolTableEntries = new HashTable<>(capacity);
        count = 0;
    }

    public SymbolTable() {
        this(100);
    }

    public Integer put(String value) {
        return symbolTableEntries.put(value, count++);
    }

    public Optional<String> getKey(Integer value) {
        return symbolTableEntries.getKey(value);
    }

    public Optional<Integer> getValue(String key) {
        return symbolTableEntries.getElement(key);
    }

    public boolean contains(String key) {
        return symbolTableEntries.contains(key);
    }

    public boolean containsValue(Integer value) {
        return symbolTableEntries.isElementPresent(value);
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
