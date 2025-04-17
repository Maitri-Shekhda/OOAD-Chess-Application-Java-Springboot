package com.chess.ui;

import com.chess.model.Board;
import com.chess.model.Move;
import com.chess.model.pieces.Piece;
import com.chess.model.pieces.PieceColor;
import com.chess.model.pieces.PieceType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class BoardPanel extends JPanel {
    private static final int TILE_SIZE = 80;
    private static final int ROWS = 8;
    private static final int COLS = 8;

    private Board boardModel;
    private Point selectedTile = null;
    private List<Move> validMoves = null;
    private boolean isWhiteTurn = true;

    public BoardPanel() {
        setPreferredSize(new Dimension(TILE_SIZE * COLS, TILE_SIZE * ROWS));
        this.boardModel = new Board();
        initializeBoard();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleMouseClick(e);
            }
        });
    }

    private void initializeBoard() {
        boardModel.setupInitialPosition();
    }

    private void handleMouseClick(MouseEvent e) {
        int col = e.getX() / TILE_SIZE;
        int row = e.getY() / TILE_SIZE;

        if (selectedTile == null) {
            Piece piece = boardModel.getPiece(row, col);
            if (piece != null && ((piece.getColor() == PieceColor.WHITE) == isWhiteTurn))
 {
                selectedTile = new Point(col, row);
                validMoves = piece.getValidMoves(boardModel);
            }
        } else {
            Piece selectedPiece = boardModel.getPiece(selectedTile.y, selectedTile.x);
            if (selectedPiece != null) {
                for (Move move : validMoves) {
                    if (move.getTargetRow() == row && move.getTargetColumn() == col) {
                        boardModel.executeMove(move);
                
                        isWhiteTurn = !isWhiteTurn;
                        break;
                    }
                }
            }
            selectedTile = null;
            validMoves = null;
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
        highlightValidMoves(g);
        drawPieces(g);
    }

    private void drawBoard(Graphics g) {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                boolean isLight = (row + col) % 2 == 0;
                g.setColor(isLight ? Color.WHITE : Color.GRAY);
                g.fillRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }
    }

    private void highlightValidMoves(Graphics g) {
        if (validMoves != null) {
            g.setColor(new Color(0, 255, 0, 100));
            for (Move move : validMoves) {
                g.fillRect(move.getToCol() * TILE_SIZE, move.getToRow() * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }
    }

    private void drawPieces(Graphics g) {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                Piece piece = boardModel.getPiece(row, col);
                if (piece != null) {
                    String symbol = getUnicodeSymbol(piece);
                    if (piece.getColor() == PieceColor.WHITE) {
                        g.setColor(new Color(255, 223, 0)); // Yellow-ish for white pieces
                    } else {
                        g.setColor(Color.BLACK); // Keep black pieces as black
                    }
                    
                    g.setFont(new Font("Serif", Font.BOLD, 48));
                    g.drawString(symbol, col * TILE_SIZE + 20, row * TILE_SIZE + 55);
                }
            }
        }
    }

    private String getUnicodeSymbol(Piece piece) {
        switch (piece.getType()) {
            case ROOK:
                return piece.getColor() == PieceColor.WHITE ? "♖" : "♜";
            case KNIGHT:
                return piece.getColor() == PieceColor.WHITE ? "♘" : "♞";
            case BISHOP:
                return piece.getColor() == PieceColor.WHITE ? "♗" : "♝";
            case QUEEN:
                return piece.getColor() == PieceColor.WHITE ? "♕" : "♛";
            case KING:
                return piece.getColor() == PieceColor.WHITE ? "♔" : "♚";
            case PAWN:
                return piece.getColor() == PieceColor.WHITE ? "♙" : "♟";
            default:
                return "?";
        }
    }
}
