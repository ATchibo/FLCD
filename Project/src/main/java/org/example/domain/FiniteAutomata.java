package org.example.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FiniteAutomata {
    private List<State> states;
    private List<Symbol> alphabet;
    private List<Transition> transitions;
    private State initialState;
    private List<State> finalStates;


    public FiniteAutomata(List<State> states, List<Symbol> alphabet, List<Transition> transitions, State initialState, List<State> finalStates) {
        this.states = states;
        this.alphabet = alphabet;
        this.transitions = transitions;
        this.initialState = initialState;
        this.finalStates = finalStates;
    }

    public FiniteAutomata(String filePath) throws FileNotFoundException {
        // open file and start reading
        File tokenFile = new File(filePath);
        Scanner scanner = new Scanner(tokenFile);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String lineType = line.split("=")[0];
            switch (lineType) {
                case "states":
                    this.states = new ArrayList<>();
                    // read states
                    String[] states = line.split("\\{")[1].split("\\}")[0].split(",");
                    for (String state : states) {
                        this.states.add(new State(state));
                    }
                    break;

                case "alphabet":
                    this.alphabet = new ArrayList<>();
                    // read alphabet
                    String[] alphabet = line.split("\\{")[1].split("\\}")[0].split(",");
                    for (String symbol : alphabet) {
                        this.alphabet.add(new Symbol(symbol));
                    }

                    break;
                case "transitions":
                    this.transitions = new ArrayList<>();
                    // read transitions
                    String[] transitions = line.split("\\{")[1].split("\\}")[0].split(";");
                    for (String transition : transitions) {
                        String[] transitionComponents = transition.split(",");
                        State fromState = new State(transitionComponents[0]);
                        State toState = new State(transitionComponents[1]);
                        Symbol symbol = new Symbol(transitionComponents[2]);
                        this.transitions.add(new Transition(fromState, toState, symbol));
                    }

                    break;
                case "in_state":
                    // read initial state
                    String initialState = line.split("=")[1];
                    this.initialState = new State(initialState);

                    break;
                case "out_states":
                    this.finalStates = new ArrayList<>();
                    // read final states
                    String[] finalStates = line.split("\\{")[1].split("\\}")[0].split(",");
                    for (String state : finalStates) {
                        this.finalStates.add(new State(state));
                    }

                    break;
            }
        }
    }

    public List<State> getStates() {
        return states;
    }

    public List<Symbol> getAlphabet() {
        return alphabet;
    }

    public List<Transition> getTransitions() {
        return transitions;
    }

    public State getInitialState() {
        return initialState;
    }

    public List<State> getFinalStates() {
        return finalStates;
    }

    @Override
    public String toString() {
        return "FiniteAutomata{" +
                "\nstates=" + states +
                ", \nalphabet=" + alphabet +
                ", \ntransitions=" + transitions +
                ", \ninitialState=" + initialState +
                ", \nfinalStates=" + finalStates +
                "\n}";
    }

    public boolean checkSequence(String sequence) {
        return checkSequence(sequence, 0, this.initialState);
    }

    private boolean checkSequence(String sequence, int currentCharPos, State currentState) {
        if (currentCharPos == sequence.length()) {
            return this.finalStates.contains(currentState);
        }

        Symbol symbol = new Symbol(String.valueOf(sequence.charAt(currentCharPos)));
        for (Transition transition : this.transitions) {
            if (transition.getFromState().equals(currentState) && transition.getSymbol().equals(symbol)) {
                if (checkSequence(sequence, currentCharPos + 1, transition.getToState()))
                    return true;
            }
        }

        return false;
    }
}
