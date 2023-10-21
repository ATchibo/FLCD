package org.example.utils;

import org.example.domain.HashNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static java.lang.Math.abs;

public class HashTable<T, K> implements Iterable<HashNode<T, K>> {
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

    public boolean isKeyPresent(T key) {
        int index = abs(key.hashCode()) % capacity;
        List<HashNode<T, K>> nodes = list.get(index);
        if (nodes == null) {
            return false;
        }

        for (HashNode<T, K> node : nodes) {
            if (node.getKey().equals(key)) {
                return true;
            }
        }

        return false;
    }

    private List<HashNode<T, K>> getFirstEntry() {
        for (List<HashNode<T, K>> nodes : list) {
            if (nodes != null) {
                return nodes;
            }
        }

        return null;
    }
    @Override
    public Iterator<HashNode<T, K>> iterator() {
        return new Iterator<>() {

            private List<HashNode<T, K>> firstEntry = getFirstEntry();
            private int index = list.indexOf(firstEntry);

            private Iterator<HashNode<T, K>> nodesIterator =
                    firstEntry == null ? null : firstEntry.iterator();

            @Override
            public boolean hasNext() {
                if (nodesIterator == null) {
                    return false;
                }

                if (nodesIterator.hasNext()) {
                    return true;
                }

                while (index++ < capacity - 1) {
                    List<HashNode<T, K>> temp = list.get(index);
                    if (temp == null) {
                        continue;
                    }

                    nodesIterator = temp.iterator();
                    if (nodesIterator.hasNext()) {
                        return true;
                    }
                }

                return false;
            }

            @Override
            public HashNode<T, K> next() {
                return nodesIterator.next();
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (HashNode<T, K> node : this) {
            sb.append(node.toString()).append("\n");
        }
        return sb.toString();
    }
}
