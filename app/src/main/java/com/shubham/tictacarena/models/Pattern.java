package com.shubham.tictacarena.models;

public class Pattern {
    private String name;
    private String rule;
    private int[][] winningLines;

    public Pattern(String name, String rule, int[][] winningLines) {
        this.name = name;
        this.rule = rule;
        this.winningLines = winningLines;
    }

    public String getName() { return name; }
    public String getRule() { return rule; }
    public int[][] getWinningLines() { return winningLines; }
}