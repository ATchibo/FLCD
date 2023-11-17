package org.example.domain;

public class State {
    private final String name;
    private final boolean isFinal;

    public State(String name, boolean isFinal) {
        this.name = name;
        this.isFinal = isFinal;
    }

    public String getName() {
        return name;
    }

    public boolean isFinal() {
        return isFinal;
    }

    @Override
    public String toString() {
        return name;
    }
}
