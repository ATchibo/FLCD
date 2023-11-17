package org.example;

import org.example.domain.FiniteAutomata;
import org.example.service.ProgramScanner;
import org.example.ui.Fa_UI;
import org.example.utils.SymbolTable;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {

//        Fa_UI ui = new Fa_UI();
//        ui.run();

        try {
            ProgramScanner scanner = new ProgramScanner("src/main/resources/token.in");

            System.out.println(scanner.scan("src/main/resources/p1.txt").getMessage());
            System.out.println(scanner.scan("src/main/resources/p2.txt").getMessage());
            System.out.println(scanner.scan("src/main/resources/p3.txt").getMessage());
            System.out.println(scanner.scan("src/main/resources/p1err.txt").getMessage());

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}