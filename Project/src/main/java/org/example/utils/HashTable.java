package org.example.utils;

import org.example.domain.HashNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.Math.abs;

public class HashTable<T, K> {
    private final List<List<HashNode<T, K>>> list;
    private final int capacity;

    public HashTable(int capacity) {
        this.capacity = capacity;
        list = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; ++i) {
            list.add(null);
        }
    }

    public Optional<K> get(T key) {
        int index = abs(key.hashCode()) % capacity;
        List<HashNode<T, K>> nodes = list.get(index);
        if (nodes == null) {
            return Optional.empty();
        }

        for (HashNode<T, K> node : nodes) {
            if (node.getKey().equals(key)) {
                return Optional.of(node.getValue());
            }
        }

        return Optional.empty();
    }

    public Optional<HashNode<T, K>> getNode(T key) {
        int index = abs(key.hashCode()) % capacity;
        List<HashNode<T, K>> nodes = list.get(index);
        if (nodes == null) {
            return Optional.empty();
        }

        for (HashNode<T, K> node : nodes) {
            if (node.getKey().equals(key)) {
                return Optional.of(node);
            }
        }

        return Optional.empty();
    }

    public void put(T key, K value) {
        int index = abs(key.hashCode()) % capacity;
        List<HashNode<T, K>> nodes = list.get(index);

        if (nodes == null) {
            nodes = new ArrayList<>();
            list.set(index, nodes);
        }

        for (HashNode<T, K> node : nodes) {
            if (node.getKey().equals(key)) {
                throw new IllegalArgumentException("Key already exists");
            }
        }

        nodes.add(new HashNode<>(key, value));
    }

    public Optional<K> remove(T key) {
        int index = abs(key.hashCode()) % capacity;
        List<HashNode<T, K>> nodes = list.get(index);
        if (nodes == null) {
            return Optional.empty();
        }

        for (HashNode<T, K> node : nodes) {
            if (node.getKey().equals(key)) {
                nodes.remove(node);
                return Optional.of(node.getValue());
            }
        }

        return Optional.empty();
    }
}
