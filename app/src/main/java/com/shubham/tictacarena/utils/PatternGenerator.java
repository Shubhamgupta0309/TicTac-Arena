package com.shubham.tictacarena.utils;

import com.shubham.tictacarena.models.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PatternGenerator {
    private static final List<Pattern> patterns = new ArrayList<>();
    private static final Random random = new Random();

    static {
        // Classic 3x3
        patterns.add(new Pattern(
                "Classic",
                "3 in a row (horizontal, vertical, or diagonal)",
                new int[][]{
                        {0,0, 0,1, 0,2}, {1,0, 1,1, 1,2}, {2,0, 2,1, 2,2}, // Rows
                        {0,0, 1,0, 2,0}, {0,1, 1,1, 2,1}, {0,2, 1,2, 2,2}, // Columns
                        {0,0, 1,1, 2,2}, {0,2, 1,1, 2,0}  // Diagonals
                }
        ));

        // Cross
        patterns.add(new Pattern(
                "Cross",
                "Center row, column or diagonals",
                new int[][]{
                        {1,0, 1,1, 1,2}, // Middle row
                        {0,1, 1,1, 2,1}, // Middle column
                        {0,0, 1,1, 2,2}, {0,2, 1,1, 2,0} // Diagonals
                }
        ));

        // Corners
        patterns.add(new Pattern(
                "Corners",
                "Any 3 corners",
                new int[][]{
                        {0,0, 0,2, 2,0}, {0,0, 0,2, 2,2},
                        {0,0, 2,0, 2,2}, {0,2, 2,0, 2,2}
                }
        ));

        // Edge
        patterns.add(new Pattern(
                "Edge",
                "Complete outer square",
                new int[][]{
                        {0,0, 0,1, 0,2}, {2,0, 2,1, 2,2}, // Top and bottom
                        {0,0, 1,0, 2,0}, {0,2, 1,2, 2,2}  // Left and right
                }
        ));

        // Plus
        patterns.add(new Pattern(
                "Plus",
                "Center with any two arms",
                new int[][]{
                        {0,1, 1,1, 2,1}, // Vertical
                        {1,0, 1,1, 1,2}, // Horizontal
                        {0,0, 1,1, 2,2}, {0,2, 1,1, 2,0} // Diagonals
                }
        ));

        // L-Shapes
        patterns.add(new Pattern(
                "L-Shapes",
                "Any L-shaped pattern",
                new int[][]{
                        {0,0, 1,0, 2,0}, {0,0, 0,1, 0,2}, // Top-left L's
                        {0,2, 1,2, 2,2}, {2,0, 2,1, 2,2}  // Bottom-right L's
                }
        ));

        // X Only
        patterns.add(new Pattern(
                "X Only",
                "Only diagonal wins",
                new int[][]{
                        {0,0, 1,1, 2,2}, {0,2, 1,1, 2,0}
                }
        ));

        // Random
        patterns.add(new Pattern(
                "Random",
                "Random pattern each game",
                null // Will be generated dynamically
        ));
    }

    public static List<Pattern> getAllPatterns() {
        return new ArrayList<>(patterns);
    }

    public static Pattern getRandomPattern() {
        // Exclude the "Random" option itself
        return patterns.get(random.nextInt(patterns.size() - 1));
    }

    public static Pattern getPatternByName(String name) {
        if (name.equals("Random")) {
            return getRandomPattern();
        }
        for (Pattern pattern : patterns) {
            if (pattern.getName().equals(name)) {
                return pattern;
            }
        }
        return patterns.get(0); // Default to Classic
    }
}