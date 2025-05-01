package com.example.flashcard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FlashcardApp {

    public void run(CardOrder order, int repetitions, boolean invertCards) {
        // Sample flashcards
        List<Flashcard> flashcards = new ArrayList<>();
        flashcards.add(new Flashcard("What is Java?", "A programming language"));
        flashcards.add(new Flashcard("What is a Flashcard?", "A learning tool"));

        // Sort flashcards based on the order
        if (order == CardOrder.RANDOM) {
            Collections.shuffle(flashcards); // Shuffle if the order is random
        } else if (order == CardOrder.RECENT_MISTAKES_FIRST) {
            flashcards = RecentMistakesFirstSorter.sort(flashcards); // Sort based on recent mistakes
        }

        // Loop for repetitions
        for (int i = 0; i < repetitions; i++) {
            for (Flashcard flashcard : flashcards) {
                if (invertCards) {
                    // Swap question and answer if invertCards is true
                    System.out.println("Question: " + flashcard.getAnswer());
                    System.out.println("Answer: " + flashcard.getQuestion());
                } else {
                    // Default display of question and answer
                    System.out.println("Question: " + flashcard.getQuestion());
                    System.out.println("Answer: " + flashcard.getAnswer());
                }
            }
        }
    }

    public static void main(String[] args) {
        // Parse command-line arguments to set the order, repetitions, and invertCards flag
        CardOrder order = CardOrder.RANDOM; // Default order
        int repetitions = 1; // Default repetitions
        boolean invertCards = false; // Default invertCards value

        // Example logic to parse command-line arguments (simplified)
        for (String arg : args) {
            if (arg.equals("--order random")) {
                order = CardOrder.RANDOM;
            } else if (arg.equals("--order worst-first")) {
                order = CardOrder.WORST_FIRST;
            } else if (arg.equals("--order recent-mistakes-first")) {
                order = CardOrder.RECENT_MISTAKES_FIRST;
            } else if (arg.startsWith("--repetitions")) {
                repetitions = Integer.parseInt(arg.split(" ")[1]);
            } else if (arg.equals("--invertCards")) {
                invertCards = true;
            }
        }

        // Run the flashcards with the parsed options
        FlashcardApp app = new FlashcardApp();
        app.run(order, repetitions, invertCards);
    }
}
