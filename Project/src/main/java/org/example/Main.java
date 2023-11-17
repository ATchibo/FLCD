package org.example;

import org.example.domain.FiniteAutomata;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {

        FiniteAutomata fa;

        try {
            fa = new FiniteAutomata("src/main/resources/fa.in");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println(fa);
    }
}