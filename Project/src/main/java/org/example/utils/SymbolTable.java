package org.example.utils;

import org.example.domain.HashNode;

import java.util.Optional;

public class SymbolTable<T> {
    private final HashTable<T, Integer> hashTable;
    private int count;

    public SymbolTable(int capacity) {
        hashTable = new HashTable<>(capacity);
    }

    public SymbolTable() {
        this(100);
    }

    public void put(T key) {
        hashTable.put(key, count++);
    }

    public Optional<Integer> get(T key) {
        return hashTable.get(key);
    }

    public Optional<HashNode<T, Integer>> getNode(T key) {
        return hashTable.getNode(key);
    }

    public boolean isKeyPresent(T key) {
        return hashTable.get(key).isPresent();
    }
}
