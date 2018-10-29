package com.exercises.chess.piece;

import com.exercises.chess.Board;
import com.exercises.chess.Color;
import com.exercises.chess.Position;
import com.exercises.chess.piece.pathValidator.UnoccupiedStraightLineValidator;

/**
 * Created by amala on 27/10/18.
 */
public class Elephant extends Piece{
    public Elephant(Color color) {
        super(color);
    }

    protected boolean validateMove(Position positionToBeMoved, Board board) {
        return new UnoccupiedStraightLineValidator(board).isPathValid(position, positionToBeMoved);
    }


}
