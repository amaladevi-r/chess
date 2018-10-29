package com.exercises.chess.piece.pathValidator;

import com.exercises.chess.Board;
import com.exercises.chess.Position;

/**
 * Created by amala on 29/10/18.
 */
public class StraightLineValidator implements PathValidator {
    @Override
    public boolean isPathValid(Position fromPosition, Position toPosition) {
        if (toPosition.getRow() == fromPosition.getRow() || toPosition.getColumn() == fromPosition.getColumn()) {
            return true;
        }
        return false;
    }
}
