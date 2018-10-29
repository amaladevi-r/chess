package com.exercises.chess.piece;

import com.exercises.chess.Board;
import com.exercises.chess.Color;
import com.exercises.chess.Position;
import com.exercises.chess.piece.pathValidator.UnoccupiedDiagonalLineValidator;
import com.exercises.chess.piece.pathValidator.UnoccupiedStraightLineValidator;

/**
 * Created by amala on 27/10/18.
 */
public class Queen extends Piece{
    public Queen(Color color) {
        super(color);
    }

    protected boolean validateMove(Position positionToBeMoved, Board board) {
        UnoccupiedDiagonalLineValidator unoccupiedDiagonalLineValidator = new UnoccupiedDiagonalLineValidator(board);
        UnoccupiedStraightLineValidator unoccupiedStraightLineValidator = new UnoccupiedStraightLineValidator(board);
        return unoccupiedDiagonalLineValidator.isPathValid(position, positionToBeMoved) ||
                unoccupiedStraightLineValidator.isPathValid(position, positionToBeMoved);

    }
}
