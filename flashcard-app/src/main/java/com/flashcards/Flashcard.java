package com.flashcards;

public class Flashcard {
    private String term;
    private String definition;
    private int correctAnswersCount = 0;
    private int repeatCount = 0;
    private int confidentCount = 0;

    public Flashcard(String term, String definition) {
        this.term = term;
        this.definition = definition;
    }

    public String getTerm() {
        return term;
    }

    public String getDefinition() {
        return definition;
    }

    public int getCorrectAnswersCount() {
        return correctAnswersCount;
    }

    public void incrementCorrectAnswersCount() {
        correctAnswersCount++;
    }

    public int getRepeatCount() {
        return repeatCount;
    }

    public void incrementRepeatCount() {
        repeatCount++;
    }

    public int getConfidentCount() {
        return confidentCount;
    }

    public void incrementConfidentCount() {
        confidentCount++;
    }
}
