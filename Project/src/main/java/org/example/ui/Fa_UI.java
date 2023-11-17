package org.example.ui;

import org.example.domain.FiniteAutomata;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Fa_UI {

    private FiniteAutomata fa;

    public Fa_UI() {
        try {
            fa = new FiniteAutomata("src/main/resources/fa.in");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() {

        while (true) {
            // print menu
            System.out.println("0. Exit");
            System.out.println("1. Read FA from file");
            System.out.println("2. Display FA");
            System.out.println("3. Check sequence");

            // read option
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();

            switch (option) {
                case 0:
                    return;
                case 1:
                    System.out.println("Enter file path: ");
                    String filePath = scanner.next();

                    try {
                        fa = new FiniteAutomata(filePath);
                    } catch (FileNotFoundException e) {
                        System.out.println("File not found");
                    }

                    break;
                case 2:
                    System.out.println(fa);

                    break;
                case 3:
                    System.out.println("Enter sequence: ");
                    String sequence = scanner.next();

                    System.out.println(fa.checkSequence(sequence));

                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }
}
