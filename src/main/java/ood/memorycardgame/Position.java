package ood.memorycardgame;

import java.util.Objects;

public class Position {
    private final int row;
    private final int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    // Getters for row and column indices

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean isEqual(Position other) {
       return this.row == other.row && this.column == other.column;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Position position = (Position) obj;
        return row == position.row && column == position.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}

