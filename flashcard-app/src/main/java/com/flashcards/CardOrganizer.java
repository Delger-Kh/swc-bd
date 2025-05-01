package com.flashcards;

import java.util.List;
import java.util.Map;

public interface CardOrganizer {
    List<Map.Entry<String, String>> organize(Map<String, String> flashcards);
}
