package com.exercises.chess.piece.pathValidator;

import com.exercises.chess.Position;

import static java.lang.Math.abs;

/**
 * Created by amala on 29/10/18.
 */
public class DiagonalLineValidator implements PathValidator {
    @Override
    public boolean isPathValid(Position fromPosition, Position toPosition) {
        if (abs(fromPosition.getRow() - toPosition.getRow()) == abs(fromPosition.getColumn() - toPosition.getColumn())) {
            return true;
        }
        return false;
    }
}
