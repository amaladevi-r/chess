package com.exercises.chess.piece;

import com.exercises.chess.Board;
import com.exercises.chess.Color;
import com.exercises.chess.Position;

import static java.lang.Math.abs;

/**
 * Created by amala on 27/10/18.
 */
public abstract class Piece {
    protected boolean isInitialPosition;
    private Color pieceColor;
    protected Position position;
    private boolean isDead;

    public Piece(Color pieceColor) {
        this.pieceColor = pieceColor;
    }

    public Color getPieceColor() {
        return pieceColor;
    }

    public void initPosition(Position position) {
        this.position = position;
        this.isInitialPosition = true;
    }

    public void setDead() {
        this.isDead = true;
        this.position = null;
    }

    public Position getPosition() {
        return position;
    }

    protected abstract boolean validateMove(Position position, Board board);

    public void move(Position positionToBeMoved, Board board) {
        if (isDead) {
            throw new UnsupportedOperationException("Cannot move a dead piece");
        }
        if (positionToBeMoved.equals(position)) {
            throw new UnsupportedOperationException("Cannot move to same position");
        }
        if (board.getPieceAt(positionToBeMoved) != null && board.getPieceAt(positionToBeMoved).getPieceColor().equals(pieceColor)) {
            throw new UnsupportedOperationException("Cannot move to a position which has piece of same color");
        }
        if(validateMove(positionToBeMoved, board)) {
            this.position = positionToBeMoved;
            isInitialPosition = false;
        } else {
            throw new UnsupportedOperationException("Move is not valid");
        }
    }

    @Override
    public String toString() {
        return pieceColor + this.getClass().getSimpleName();
    }
}
