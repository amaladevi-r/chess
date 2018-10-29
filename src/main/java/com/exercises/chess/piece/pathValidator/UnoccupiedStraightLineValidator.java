package com.exercises.chess.piece.pathValidator;

import com.exercises.chess.Position;

/**
 * Created by amala on 29/10/18.
 */
public class UnoccupiedStraightLineValidator extends StraightLineValidator{

    private PositionOccupationRetriever positionOccupationRetriever;

    public UnoccupiedStraightLineValidator(PositionOccupationRetriever positionOccupationRetriever) {
        this.positionOccupationRetriever = positionOccupationRetriever;
    }

    @Override
    public boolean isPathValid(Position fromPosition, Position toPosition) {
        boolean pathValid = super.isPathValid(fromPosition, toPosition);
        if (pathValid) {
            return findIfStraightLineIsFree(fromPosition, toPosition);
        }
        return pathValid;
    }

    private boolean findIfStraightLineIsFree(Position positionA, Position positionB) {
        if (positionA.getRow() != positionB.getRow()) {
            if (positionA.getRow() > positionB.getRow()) {
                return findIfPathIsClear(positionB, positionA, positionOccupationRetriever, true);
            } else if (positionB.getRow() > positionA.getRow()) {
                return findIfPathIsClear(positionA, positionB, positionOccupationRetriever, true);
            }
        } else if (positionA.getColumn() != positionB.getColumn()) {
            if(positionA.getColumn() > positionB.getColumn()) {
                return findIfPathIsClear(positionB, positionA, positionOccupationRetriever, false);
            } else if (positionB.getColumn() > positionA.getColumn()) {
                return findIfPathIsClear(positionB, positionA, positionOccupationRetriever, false);
            }
        }
        return false;
    }

    private boolean findIfPathIsClear(Position positionA, Position positionB, PositionOccupationRetriever board, boolean isRowMovement) {
        int row = positionA.getRow();
        int col = positionA.getColumn();
        if (isRowMovement) {
            row++;
        } else {
            col++;
        }
        while((isRowMovement && row < positionB.getRow()) || (!isRowMovement && col < positionB.getColumn())) {
            if (board.isPositionOccupied(new Position(row, col))) {
                return false;
            }
            if (isRowMovement) {
                row++;
            } else {
                col++;
            }
        }
        return true;
    }
}
