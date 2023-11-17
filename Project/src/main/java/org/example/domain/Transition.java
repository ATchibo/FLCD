package org.example.domain;

public class Transition {
    private final State fromState;
    private final State toState;
    private final Symbol symbol;

    public Transition(State fromState, State toState, Symbol symbol) {
        this.fromState = fromState;
        this.toState = toState;
        this.symbol = symbol;
    }

    public State getFromState() {
        return fromState;
    }

    public State getToState() {
        return toState;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return fromState + " -> " + toState + " with " + symbol;
    }
}
