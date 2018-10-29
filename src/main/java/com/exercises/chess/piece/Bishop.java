package com.exercises.chess.piece;

import com.exercises.chess.Board;
import com.exercises.chess.Color;
import com.exercises.chess.Position;
import com.exercises.chess.piece.pathValidator.UnoccupiedDiagonalLineValidator;

/**
 * Created by amala on 27/10/18.
 */
public class Bishop extends Piece{
    public Bishop(Color color) {
        super(color);
    }

    protected boolean validateMove(Position positionToBeMoved, Board board) {
        return new UnoccupiedDiagonalLineValidator(board).isPathValid(position, positionToBeMoved);
    }
}
