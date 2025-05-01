package com.flashcards;

import java.util.Map;

public class AchievementsTracker {
    public static void checkAchievements(Map<String, Flashcard> flashcards) {
        boolean correct = true;
        for (Flashcard flashcard : flashcards.values()) {
            // Checking CORRECT achievement (all cards answered correctly in a round)
            if (flashcard.getCorrectAnswersCount() == 0) {
                correct = false;
            }

            // Checking REPEAT achievement (answered a card more than 5 times)
            if (flashcard.getRepeatCount() > 5) {
                System.out.println("Achievement Unlocked: REPEAT - " + flashcard.getTerm() + " has been answered more than 5 times!");
            }

            // Checking CONFIDENT achievement (answered a card correctly at least 3 times)
            if (flashcard.getConfidentCount() >= 3) {
                System.out.println("Achievement Unlocked: CONFIDENT - " + flashcard.getTerm() + " has been answered correctly 3 times!");
            }
        }

        // Checking if CORRECT achievement is earned
        if (correct) {
            System.out.println("Achievement Unlocked: CORRECT - You answered all cards correctly in this round!");
        }
    }
}
