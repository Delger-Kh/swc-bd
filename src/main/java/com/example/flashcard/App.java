package com.example.flashcard;

public class App {
    public static void main(String[] args) {
        if (args.length == 0 || args[0].equals("--help")) {
            printHelp();
            return;
        }

        // Default values
        CardOrder order = CardOrder.RANDOM;
        int repetitions = 1;
        boolean invertCards = false;

        // Parse the additional options
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "--help":
                    printHelp();
                    return;
                case "--order":
                    if (i + 1 < args.length) {
                        try {
                            order = CardOrder.fromString(args[++i]);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid order: " + args[i]);
                            return;
                        }
                    } else {
                        System.out.println("--order option is missing a value.");
                        return;
                    }
                    break;
                case "--repetitions":
                    if (i + 1 < args.length) {
                        try {
                            repetitions = Integer.parseInt(args[++i]);
                        } catch (NumberFormatException e) {
                            System.out.println("Repetitions should be a number.");
                            return;
                        }
                    } else {
                        System.out.println("--repetitions option is missing a value.");
                        return;
                    }
                    break;
                case "--invertCards":
                    invertCards = true;
                    break;
                default:
                    System.out.println("Unknown option: " + args[i]);
                    return;
            }
        }

        try {
            // Instead of reading from a file, we are using static flashcards in FlashcardApp.java
            FlashcardApp app = new FlashcardApp();
            app.run(order, repetitions, invertCards);  // No need to pass cardsFile since it's not being used
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }

    private static void printHelp() {
        System.out.println("Usage:");
        System.out.println("  flashcard [options]");
        System.out.println("Options:");
        System.out.println("  --help                  Show help information");
        System.out.println("  --order <order>         Card order type (random, worst-first, recent-mistakes-first)");
        System.out.println("  --repetitions <num>     Number of times to show each card");
        System.out.println("  --invertCards           Invert cards' question and answer");
    }
}
