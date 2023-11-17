package org.example.domain;

public class State {
    private final String name;

    public State(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof State other) {
            return this.name.equals(other.name);
        }
        return false;
    }
}
