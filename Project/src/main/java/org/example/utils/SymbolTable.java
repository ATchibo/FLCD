package org.example.utils;

import org.example.domain.HashNode;

import java.util.Optional;

public class SymbolTable {
    private final HashTable<String, Integer> identifiersHashTable;
    private final HashTable<String, Integer> stingConstHashTable;
    private final HashTable<String, Integer> intConstHashTable;
    private int count;

    public SymbolTable(int capacity) {
        identifiersHashTable = new HashTable<>(capacity);
        stingConstHashTable = new HashTable<>(capacity);
        intConstHashTable = new HashTable<>(capacity);
    }

    public SymbolTable() {
        this(100);
    }

    public Optional<Integer> getIdentifier(String key) {
        return identifiersHashTable.get(key);
    }

    public Optional<Integer> getStringConst(String key) {
        return stingConstHashTable.get(key);
    }

    public Optional<Integer> getIntConst(String key) {
        return intConstHashTable.get(key);
    }

    public void putIdentifier(String key) {
        identifiersHashTable.put(key, count++);
    }

    public void putStringConst(String key) {
        stingConstHashTable.put(key, count++);
    }

    public void putIntConst(String key) {
        intConstHashTable.put(key, count++);
    }

    public boolean isIdentifierPresent(String key) {
        return identifiersHashTable.isKeyPresent(key);
    }

    public boolean isStringConstPresent(String key) {
        return stingConstHashTable.isKeyPresent(key);
    }

    public boolean isIntConstPresent(String key) {
        return intConstHashTable.isKeyPresent(key);
    }

    public Optional<HashNode<String, Integer>> getIdentifierNode(String key) {
        return identifiersHashTable.getNode(key);
    }

    public Optional<HashNode<String, Integer>> getStringConstNode(String key) {
        return stingConstHashTable.getNode(key);
    }

    public Optional<HashNode<String, Integer>> getIntConstNode(String key) {
        return intConstHashTable.getNode(key);
    }

    public HashTable<String, Integer> getIdentifiersHashTable() {
        return identifiersHashTable;
    }

    public HashTable<String, Integer> getStringConstHashTable() {
        return stingConstHashTable;
    }

    public HashTable<String, Integer> getIntConstHashTable() {
        return intConstHashTable;
    }

    @Override
    public String toString() {
        return "ST\n" +
                "Size: " + count + "\n" +
                identifiersHashTable +
                stingConstHashTable +
                intConstHashTable;
    }
}
