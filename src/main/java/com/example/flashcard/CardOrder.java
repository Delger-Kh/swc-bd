package com.example.flashcard;

public enum CardOrder {
    RANDOM,
    WORST_FIRST,
    RECENT_MISTAKES_FIRST;


    public static CardOrder fromString(String order) {
        switch (order.toLowerCase()) {
            case "random":
                return RANDOM;
            case "recent-mistakes-first":
                return RECENT_MISTAKES_FIRST;
            default:
                throw new IllegalArgumentException("Invalid CardOrder: " + order);
        }
    }
}
