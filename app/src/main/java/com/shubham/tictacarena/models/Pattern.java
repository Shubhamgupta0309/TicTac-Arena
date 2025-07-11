package com.shubham.tictacarena.models;

public class Pattern {
    private String name;
    private String rule;
    private int[][] winningLines;
    private String visualRepresentation;

    public Pattern(String name, String rule, int[][] winningLines, String visualRepresentation) {
        this.name = name;
        this.rule = rule;
        this.winningLines = winningLines;
        this.visualRepresentation = visualRepresentation;
    }

    public String getName() { return name; }
    public String getRule() { return rule; }
    public int[][] getWinningLines() { return winningLines; }
    public String getVisualRepresentation() { return visualRepresentation; }
}