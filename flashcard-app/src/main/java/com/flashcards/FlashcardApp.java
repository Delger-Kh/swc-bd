package com.flashcards;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FlashcardApp {
    private static Map<String, String> flashcards = new LinkedHashMap<>();
    private static boolean invertCards = false;
    private static String order = "random";
    private static int repetitions = 1;

    public static void main(String[] args) {
        String cardsFile = null;

        // Parse arguments
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "--help":
                    printHelp();
                    return;
                case "--order":
                    if (i + 1 < args.length) {
                        order = args[i + 1];
                    } else {
                        System.out.println("Error: Missing argument for --order");
                        return;
                    }
                    break;
                case "--repetitions":
                    if (i + 1 < args.length) {
                        try {
                            repetitions = Integer.parseInt(args[i + 1]);
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Invalid number for --repetitions");
                            return;
                        }
                    } else {
                        System.out.println("Error: Missing argument for --repetitions");
                        return;
                    }
                    break;
                case "--invertCards":
                    invertCards = true;
                    break;
                default:
                    if (i == 0) {
                        cardsFile = args[i];
                    }
                    break;
            }
        }

        if (cardsFile == null) {
            System.out.println("Error: Missing cards file.");
            return;
        }

        // Load flashcards
        loadFlashcards(cardsFile);

        // Start the flashcard session
        runFlashcardSession();
    }

    private static void printHelp() {
        System.out.println("Flashcard App - Command Line Interface");
        System.out.println("Usage: flashcard <cards-file> [options]");
        System.out.println("Options:");
        System.out.println("  --help                  Show this help message");
        System.out.println("  --order <order>         Set the order of flashcards (default: 'random')");
        System.out.println("                          Options: 'random', 'worst-first', 'recent-mistakes-first'");
        System.out.println("  --repetitions <num>     Set the number of times to ask each flashcard (default: 1)");
        System.out.println("  --invertCards           Invert the cards (question/answer) during display (default: false)");
    }

    private static void loadFlashcards(String fileName) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String term = scanner.nextLine().trim();
                if (scanner.hasNextLine()) {
                    String definition = scanner.nextLine().trim();
                    flashcards.put(term, definition);
                }
            }
            System.out.println(flashcards.size() + " flashcards loaded.");
        } catch (IOException e) {
            System.out.println("Error: Unable to load cards from " + fileName);
        }
    }

    private static void runFlashcardSession() {
        Scanner scanner = new Scanner(System.in);
        List<Map.Entry<String, String>> flashcardList = new ArrayList<>(flashcards.entrySet());

        // Choose flashcards order
        switch (order) {
            case "worst-first":
                // Implement logic for worst-first
                break;
            case "recent-mistakes-first":
                // Implement logic for recent-mistakes-first
                break;
            default:
                Collections.shuffle(flashcardList); // Random order
        }

        // Start the session
        for (Map.Entry<String, String> entry : flashcardList) {
            String question = invertCards ? entry.getValue() : entry.getKey();
            String answer = invertCards ? entry.getKey() : entry.getValue();

            System.out.println("Question: " + question);

            for (int i = 0; i < repetitions; i++) {
                System.out.println("Answer: " + answer);
            }
        }

        scanner.close();
    }
}
