package com.flashcards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecentMistakesFirstSorter implements CardOrganizer {
    private Map<String, Integer> mistakeTracker = new HashMap<>();

    @Override
    public List<Map.Entry<String, String>> organize(Map<String, String> flashcards) {
        List<Map.Entry<String, String>> sortedCards = new ArrayList<>(flashcards.entrySet());
        sortedCards.sort((entry1, entry2) -> {
            int mistakes1 = mistakeTracker.getOrDefault(entry1.getKey(), 0);
            int mistakes2 = mistakeTracker.getOrDefault(entry2.getKey(), 0);
            return Integer.compare(mistakes2, mistakes1); // More mistakes first
        });
        return sortedCards;
    }

    public void markMistake(String term) {
        mistakeTracker.put(term, mistakeTracker.getOrDefault(term, 0) + 1);
    }
}
