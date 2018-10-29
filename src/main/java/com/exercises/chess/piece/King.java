package com.exercises.chess.piece;

import com.exercises.chess.Board;
import com.exercises.chess.Color;
import com.exercises.chess.Position;

import static java.lang.Math.abs;

/**
 * Created by amala on 28/10/18.
 */
public class King extends Piece{
    public King(Color color) {
        super(color);
    }

    protected boolean validateMove(Position positionToBeMoved, Board board) {
        if (abs(positionToBeMoved.getColumn() - this.position.getColumn()) > 1 || abs(positionToBeMoved.getRow() - this.position.getRow()) > 1) {
            throw new UnsupportedOperationException("Cannot move more than one step");
        }
        return true;
    }
}
