package com.shubham.tictacarena.utils;

import com.shubham.tictacarena.models.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PatternGenerator {
    private static final List<Pattern> patterns = new ArrayList<>();
    private static final Random random = new Random();

    static {
        // 1. Classic
        patterns.add(new Pattern(
                "Classic",
                "3 in a row (any direction)",
                new int[][]{
                        // Rows
                        {0,0, 0,1, 0,2}, {1,0, 1,1, 1,2}, {2,0, 2,1, 2,2},
                        // Columns
                        {0,0, 1,0, 2,0}, {0,1, 1,1, 2,1}, {0,2, 1,2, 2,2},
                        // Diagonals
                        {0,0, 1,1, 2,2}, {0,2, 1,1, 2,0}
                }
        ));

        // 2. Cross
        patterns.add(new Pattern(
                "Cross",
                "Center row/column/diagonals",
                new int[][]{
                        {1,0, 1,1, 1,2},  // Middle row
                        {0,1, 1,1, 2,1},   // Middle column
                        {0,0, 1,1, 2,2},   // Diagonal \
                        {0,2, 1,1, 2,0}    // Diagonal /
                }
        ));

        // 3. Corners
        patterns.add(new Pattern(
                "Corners",
                "Any 3 corners",
                new int[][]{
                        {0,0, 0,2, 2,0}, {0,0, 0,2, 2,2},
                        {0,0, 2,0, 2,2}, {0,2, 2,0, 2,2}
                }
        ));

        // 4. L-Shapes
        patterns.add(new Pattern(
                "L-Shapes",
                "Corner + two adjacent",
                new int[][]{
                        // Top-left L's
                        {0,0, 0,1, 1,0}, {0,0, 0,1, 2,0},
                        {0,0, 1,0, 2,0}, {0,0, 1,0, 0,2},
                        // Other corners similarly...
                }
        ));

        // 5. X Only
        patterns.add(new Pattern(
                "X Only",
                "Only main diagonals",
                new int[][]{
                        {0,0, 1,1, 2,2},  // Diagonal \
                        {0,2, 1,1, 2,0}   // Diagonal /
                }
        ));

        // 6. Plus
        patterns.add(new Pattern(
                "Plus",
                "Center + two arms",
                new int[][]{
                        {0,1, 1,1, 2,1},  // Vertical
                        {1,0, 1,1, 1,2},   // Horizontal
                        {0,0, 1,1, 2,2},   // Diagonal \
                        {0,2, 1,1, 2,0}    // Diagonal /
                }
        ));

        // 7. Random
        patterns.add(new Pattern(
                "Random",
                "Random pattern each game",
                null
        ));
    }

    public static List<Pattern> getAllPatterns() {
        // Exclude Random from selection list
        return new ArrayList<>(patterns.subList(0, patterns.size()-1));
    }

    public static Pattern getRandomPattern() {
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