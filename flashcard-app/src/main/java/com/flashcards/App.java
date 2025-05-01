package com.flashcards;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class App {
    private static Map<String, String> flashcards = new LinkedHashMap<>();
    private static String order = "random";  // Default order
    private static int repetitions = 1;      // Default repetitions
    private static boolean invertCards = false; // Default invertCards flag

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Command-line argument parsing
        for (int i = 0; i < args.length; i++) {
            if ("--order".equals(args[i])) {
                order = args[i + 1];
                i++;
            } else if ("--repetitions".equals(args[i])) {
                repetitions = Integer.parseInt(args[i + 1]);
                i++;
            } else if ("--invertCards".equals(args[i])) {
                invertCards = true;
            } else if ("--help".equals(args[i])) {
                displayHelp();
                return;
            }
        }

        // Load flashcards from file (if any)
        loadFlashcards("flashcards.txt");

        // Start interactive command-line session
        System.out.println("Flashcard app is running. Type 'exit' to quit.");
        while (true) {
            System.out.print("Enter a command: ");
            String input = scanner.nextLine().trim().toLowerCase();

            if ("exit".equals(input)) {
                break;
            } else if ("next".equals(input)) {
                displayNextFlashcard();
            } else if ("show".equals(input)) {
                showAllFlashcards();
            } else {
                System.out.println("Unknown command. Try 'next', 'show', or 'exit'.");
            }
        }
    }

    private static void displayNextFlashcard() {
        if (!flashcards.isEmpty()) {
            // Randomly select the first flashcard
            for (Map.Entry<String, String> entry : flashcards.entrySet()) {
                if (invertCards) {
                    System.out.println("Question: " + entry.getValue());  // Inverted
                    System.out.println("Answer: " + entry.getKey());  // Inverted
                } else {
                    System.out.println("Question: " + entry.getKey());
                    System.out.println("Answer: " + entry.getValue());
                }
                break; // Show only one flashcard per "next" command
            }
        } else {
            System.out.println("No flashcards available.");
        }
    }

    private static void showAllFlashcards() {
        if (!flashcards.isEmpty()) {
            for (Map.Entry<String, String> entry : flashcards.entrySet()) {
                if (invertCards) {
                    System.out.println("Question: " + entry.getValue());  // Inverted
                    System.out.println("Answer: " + entry.getKey());  // Inverted
                } else {
                    System.out.println("Question: " + entry.getKey());
                    System.out.println("Answer: " + entry.getValue());
                }
                System.out.println("-----");
            }
        } else {
            System.out.println("No flashcards available.");
        }
    }

    private static void loadFlashcards(String filename) {
        try (Scanner fileScanner = new Scanner(new File(filename))) {
            while (fileScanner.hasNextLine()) {
                String term = fileScanner.nextLine();
                if (fileScanner.hasNextLine()) {
                    String definition = fileScanner.nextLine();
                    flashcards.put(term, definition);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error loading flashcards from file.");
        }
    }

    private static void displayHelp() {
        System.out.println("Usage: flashcard <cards-file> [options]");
        System.out.println("Options:");
        System.out.println(" --help                 Display this help message.");
        System.out.println(" --order <order>        Set order type: random (default).");
        System.out.println(" --repetitions <num>    Number of repetitions required for a card (default: 1).");
        System.out.println(" --invertCards          If set, the question and answer will be swapped (default: false).");
    }
}
