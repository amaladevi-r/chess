package com.exercises.chess.piece;

import com.exercises.chess.Board;
import com.exercises.chess.Color;
import com.exercises.chess.Position;

import static java.lang.Math.abs;

/**
 * Created by amala on 27/10/18.
 */
public class Pawn extends Piece{

    public Pawn(Color color) {
        super(color);
    }

    protected boolean validateMove(Position positionToMove, Board board) {
        if (moveIsBackwards(positionToMove)) {
            throw new UnsupportedOperationException("Cannot move backwards");
        }
        if (moveIsDiagonalMoreThanOne(positionToMove)) {
            throw new UnsupportedOperationException("Cannot move diagonal more than one");

        }
        if((!isInitialPosition && moveForwardMoreThanOneStep(positionToMove))) {
            throw new UnsupportedOperationException("Cannot move more than 1 step when not initial position");
        }
        if ((isInitialPosition && moveForwardMoreThan2Steps(positionToMove))) {
            throw new UnsupportedOperationException("Cannot move more than 2 steps when not in initial position");
        }
        if (board.getPieceAt(positionToMove) == null && moveIsSideways(positionToMove)) {
            throw new UnsupportedOperationException("Cannot move sideways unless there is a card there");
        }
        if (!isForwardSingleStepDiagonal(positionToMove) && board.getPieceAt(positionToMove) != null) {
            throw new UnsupportedOperationException("Cannot move to a position where a piece already exists");
        }

        if(isInitialPosition && moveForwardMoreThanOneStep(positionToMove)) {
            if (board.getPieceAt(new Position(positionToMove.getRow()-1, positionToMove.getColumn())) != null) {
                throw new UnsupportedOperationException("Cannot jump over pieces for initial move");
            }
        }
        return true;

    }

    private boolean isForwardSingleStepDiagonal(Position positionToMove) {
        return (positionToMove.getRow() - this.position.getRow()) == 1 && abs(positionToMove.getColumn() - this.position.getColumn()) == 1;
    }

    private boolean moveIsSideways(Position positionToMove) {
        return positionToMove.getColumn() != position.getColumn();
    }

    private boolean moveForwardMoreThanOneStep(Position positionToMove) {
        return positionToMove.getRow() - position.getRow() > 1;
    }

    private boolean moveForwardMoreThan2Steps(Position positionToMove) {
        return positionToMove.getRow() - position.getRow() > 2;
    }

    private boolean moveIsDiagonalMoreThanOne(Position positionToMove) {
        return (positionToMove.getColumn() - position.getColumn() > 1);
    }

    private boolean moveIsBackwards(Position positionToMove) {
        return positionToMove.getRow() < position.getRow();
    }
}
