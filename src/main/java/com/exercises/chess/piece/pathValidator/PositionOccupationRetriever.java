package com.exercises.chess.piece.pathValidator;

import com.exercises.chess.Position;

/**
 * Created by amala on 29/10/18.
 */
public interface PositionOccupationRetriever {
    public boolean isPositionOccupied(Position position);
}
