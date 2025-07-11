package com.shubham.tictacarena.utils;

import com.shubham.tictacarena.models.Pattern;
import java.util.ArrayList;
import java.util.List;

public class PatternGenerator {

    private static final List<Pattern> patterns = new ArrayList<>();

    static {
        // Classic 3x3
        patterns.add(new Pattern(
                "Classic",
                "Win with 3 in a row (horizontal, vertical, or diagonal)",
                new int[][]{
                        {0,0, 0,1, 0,2}, // Top row
                        {1,0, 1,1, 1,2}, // Middle row
                        {2,0, 2,1, 2,2}, // Bottom row
                        {0,0, 1,0, 2,0}, // Left column
                        {0,1, 1,1, 2,1}, // Middle column
                        {0,2, 1,2, 2,2}, // Right column
                        {0,0, 1,1, 2,2}, // Diagonal \
                        {0,2, 1,1, 2,0}  // Diagonal /
                },
                "X | X | X\n---------\n  |   |  \n---------\n  |   |  "
        ));

        // Cross pattern
        patterns.add(new Pattern(
                "Cross",
                "Win with center row, column or diagonals",
                new int[][]{
                        {1,0, 1,1, 1,2}, // Middle row
                        {0,1, 1,1, 2,1}, // Middle column
                        {0,0, 1,1, 2,2}, // Diagonal \
                        {0,2, 1,1, 2,0}  // Diagonal /
                },
                "  | X |  \n---------\nX | X | X\n---------\n  | X |  "
        ));

        // Add more patterns as needed
    }

    public static Pattern getPatternByName(String name) {
        for (Pattern pattern : patterns) {
            if (pattern.getName().equals(name)) {
                return pattern;
            }
        }
        return patterns.get(0); // Default to Classic
    }

    public static List<Pattern> getAllPatterns() {
        return new ArrayList<>(patterns);
    }
}