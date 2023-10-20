package org.example.domain;

public class HashNode<T, K> {
    private T key;
    private K value;

    public HashNode(T key, K value) {
        this.key = key;
        this.value = value;
    }

    public T getKey() {
        return key;
    }

    public K getValue() {
        return value;
    }
}
