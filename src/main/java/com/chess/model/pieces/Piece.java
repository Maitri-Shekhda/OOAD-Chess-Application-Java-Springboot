package com.chess.model.pieces;

import com.chess.model.Board;
import com.chess.model.Move;

import java.io.Serializable;
import java.util.List;

public abstract class Piece implements Serializable {
    private static final long serialVersionUID = 1L;

    protected PieceType type;
    protected PieceColor color;
    protected int row;
    protected int column;
    private boolean hasMoved = false;

    public Piece(PieceColor color, int row, int column) {
        this.color = color;
        this.row = row;
        this.column = column;
    }

    // Custom getter and setter for hasMoved
    public boolean hasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    // Method to get all valid moves for this piece on the given board
    public abstract List<Move> getValidMoves(Board board);

    // Method to check if a move is valid for this piece
    public abstract boolean isValidMove(Board board, int targetRow, int targetColumn);

    // Common utility method to check if a position is within board boundaries
    protected boolean isWithinBounds(int row, int column) {
        return row >= 0 && row < 8 && column >= 0 && column < 8;
    }

    // Update piece position after a move
    public void move(int targetRow, int targetColumn) {
        this.row = targetRow;
        this.column = targetColumn;
        this.hasMoved = true;
    }

    @Override
    public String toString() {
        return color.toString().charAt(0) + getType().toString().substring(0, 1);
    }

    // Getters and setters for other fields (optional if needed)
    public PieceType getType() {
        return type;
    }

    public void setType(PieceType type) {
        this.type = type;
    }

    public PieceColor getColor() {
        return color;
    }

    public void setColor(PieceColor color) {
        this.color = color;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
