package com.exercises.chess.piece;

import com.exercises.chess.Board;
import com.exercises.chess.Color;
import com.exercises.chess.Position;

import static java.lang.Math.abs;

/**
 * Created by amala on 27/10/18.
 */
public class Horse extends Piece {

    public Horse(Color color) {
        super(color);
    }

    protected boolean validateMove(Position positionToBeMoved, Board board) {
        if ((abs(positionToBeMoved.getRow() - position.getRow()) == 2 && abs(positionToBeMoved.getColumn() - position.getColumn()) == 1)
                || abs(positionToBeMoved.getColumn() - position.getColumn()) == 2 && abs(positionToBeMoved.getRow() - position.getRow()) == 1) {
            return true;
        } else {
            throw new UnsupportedOperationException("Cannot move to a position which is not L shape");
        }
    }

}
