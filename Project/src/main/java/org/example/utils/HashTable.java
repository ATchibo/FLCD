package org.example.utils;

import org.example.domain.Position;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static java.lang.Math.abs;

public class HashTable<T extends Comparable<T>> implements Iterable<T> {
    private final List<List<T>> list;
    private final int capacity;
    private int size;

    public HashTable(int capacity) {
        this.capacity = capacity;
        list = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; ++i) {
            list.add(null);
        }

        size = 0;
    }

    public Optional<T> getByPosition(Position position) {
        List<T> nodes = list.get(position.getLine());
        if (nodes == null || position.getColumn() >= nodes.size()) {
            return Optional.empty();
        }

        return Optional.of(nodes.get(position.getColumn()));
    }

    public Optional<Position> getPosition(T element) {
        int index = abs(element.hashCode()) % capacity;
        List<T> nodes = list.get(index);
        if (nodes == null) {
            return Optional.empty();
        }

        for (T node : nodes) {
            if (node.equals(element)) {
                return Optional.of(new Position(index, nodes.indexOf(node)));
            }
        }

        return Optional.empty();
    }


    public Position put(T element) {
        int index = abs(element.hashCode()) % capacity;
        List<T> nodes = list.get(index);

        if (nodes == null) {
            nodes = new ArrayList<>();
            list.set(index, nodes);
        }

        for (T node : nodes) {
            if (node.equals(element)) {
                throw new IllegalArgumentException("Key already exists");
            }
        }

        nodes.add(element);
        ++size;

        return new Position(index, nodes.size() - 1);
    }

    public Optional<T> remove(T element) {
        int index = abs(element.hashCode()) % capacity;
        List<T> nodes = list.get(index);
        if (nodes == null) {
            return Optional.empty();
        }

        for (T node : nodes) {
            if (node.equals(element)) {
                nodes.remove(node);
                return Optional.of(node);
            }
        }

        return Optional.empty();
    }

    public boolean isElementPresent(T element) {
        int index = abs(element.hashCode()) % capacity;
        List<T> nodes = list.get(index);
        if (nodes == null) {
            return false;
        }

        for (T node : nodes) {
            if (node.equals(element)) {
                return true;
            }
        }

        return false;
    }

    public int size() {
        return size;
    }

    private List<T> getFirstEntry() {
        for (List<T> nodes : list) {
            if (nodes != null) {
                return nodes;
            }
        }

        return null;
    }
    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {

            private final List<T> firstEntry = getFirstEntry();
            private int index = list.indexOf(firstEntry);

            private Iterator<T> nodesIterator =
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
                    List<T> temp = list.get(index);
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
            public T next() {
                return nodesIterator.next();
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T node : this) {
            sb.append(this.getPosition(node).get()).append(" | ").append(node).append("\n");
        }
        return sb.toString();
    }
}
