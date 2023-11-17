package org.example.utils;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static java.lang.Math.abs;

public class HashTable<K, T extends Comparable<T>> implements Iterable<Pair<K, T>> {
    private List<List<Pair<K, T>>> list;
    private final int capacity;
    private int size;

    public HashTable(int capacity) {
        this.capacity = capacity;
        clear();
    }

    public Optional<T> getElement(K key) {
        int index = abs(key.hashCode()) % capacity;
        List<Pair<K, T>> nodes = list.get(index);
        if (nodes == null) {
            return Optional.empty();
        }

        for (Pair<K, T> node : nodes) {
            if (node.getKey().equals(key)) {
                return Optional.of(node.getValue());
            }
        }


        return Optional.empty();
    }

    public Optional<K> getKey(T element) {
        for (List<Pair<K, T>> nodes: list) {
            if (nodes == null) {
                continue;
            }

            for (Pair<K, T> node : nodes) {
                if (node.getValue().equals(element)) {
                    return Optional.of(node.getKey());
                }
            }
        }

        return Optional.empty();
    }

    public T put(K key, T element) {
        if (isElementPresent(element))
            return element;

        int index = abs(key.hashCode()) % capacity;
        List<Pair<K, T>> nodes = list.get(index);

        if (nodes == null) {
            nodes = new ArrayList<>();
            list.set(index, nodes);
        }

        for (Pair<K, T> node : nodes) {
            if (node.getKey().equals(key)) {
                return node.getValue();
            }
        }

        nodes.add(new ImmutablePair<>(key, element));
        ++size;

        return element;
    }

    public Optional<T> remove(K key) {
        if (key == null) {
            return Optional.empty();
        }

        int index = abs(key.hashCode()) % capacity;
        List<Pair<K, T>> nodes = list.get(index);

        if (nodes == null) {
            nodes = new ArrayList<>();
            list.set(index, nodes);
        }

        for (Pair<K, T> node : nodes) {
            if (node.getKey().equals(key)) {
                nodes.remove(node);
                return Optional.of(node.getValue());
            }
        }

        return Optional.empty();
    }

    public Optional<T> remove(T element) {
        return remove(getKey(element).get());
    }

    public boolean isElementPresent(T element) {
        return getKey(element).isPresent();
    }

    public boolean contains(K key) {
        return getElement(key).isPresent();
    }

    public int size() {
        return size;
    }

    private List<Pair<K, T>> getFirstEntry() {
        for (List<Pair<K, T>> nodes : list) {
            if (nodes != null) {
                return nodes;
            }
        }

        return null;
    }
    @Override
    public Iterator<Pair<K, T>> iterator() {
        return new Iterator<>() {

            private final List<Pair<K, T>> firstEntry = getFirstEntry();
            private int index = list.indexOf(firstEntry);

            private Iterator<Pair<K, T>> nodesIterator =
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
                    List<Pair<K, T>> temp = list.get(index);
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
            public Pair<K, T> next() {
                return nodesIterator.next();
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Pair<K, T> node : this) {
            sb.append(node.getKey()).append(" | ").append(node.getValue()).append("\n");
        }
        return sb.toString();
    }

    public void clear() {
        list = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; ++i) {
            list.add(null);
        }

        size = 0;
    }
}
