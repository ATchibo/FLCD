package org.example.domain;

public class Position implements Comparable<Position> {
    private final int line;
    private final int column;

    public Position(int line, int column) {
        this.line = line;
        this.column = column;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public int compareTo(Position position) {
        if (line != position.line)
            return line - position.line;
        return column - position.column;
    }

    @Override
    public String toString() {
        return "(" + line + ", " + column + ")";
    }
}
