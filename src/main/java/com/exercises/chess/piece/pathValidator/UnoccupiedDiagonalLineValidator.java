package com.exercises.chess.piece.pathValidator;

import com.exercises.chess.Position;

/**
 * Created by amala on 29/10/18.
 */
public class UnoccupiedDiagonalLineValidator extends DiagonalLineValidator {
    private PositionOccupationRetriever positionOccupationRetriever;

    public UnoccupiedDiagonalLineValidator(PositionOccupationRetriever positionOccupationRetriever) {
        this.positionOccupationRetriever = positionOccupationRetriever;
    }

    @Override
    public boolean isPathValid(Position fromPosition, Position toPosition) {
        boolean pathValid = super.isPathValid(fromPosition, toPosition);
        if (pathValid) {
            return findIfDiagonalIsFree(fromPosition, toPosition);
        }
        return pathValid;
    }

    private boolean findIfDiagonalIsFree(Position positionA, Position positionB) {
        if (positionB.getRow() > positionA.getRow() && positionB.getColumn() > positionA.getColumn()) {
            int row = positionA.getRow() + 1;
            int col = positionA.getColumn() + 1;
            while(row < positionB.getRow() && col < positionB.getColumn()) {
                if (positionOccupationRetriever.isPositionOccupied(new Position(row, col))) {
                    return false;
                }
                row++;
                col++;
            }
        } else if (positionB.getRow() > positionA.getRow() && positionB.getColumn() < positionA.getColumn()) {
            int row = positionA.getRow() + 1;
            int col = positionA.getColumn() - 1;
            while (row< positionB.getRow() && col > positionB.getColumn()) {
                if (positionOccupationRetriever.isPositionOccupied(new Position(row, col))) {
                    return false;
                }
                row++;
                col--;
            }
        }  else if (positionB.getRow() < positionA.getRow() && positionB.getColumn() > positionA.getColumn()) {
            int row = positionA.getRow() - 1;
            int col = positionA.getColumn() + 1;
            while (row > positionB.getRow() && col < positionB.getColumn()) {
                if (positionOccupationRetriever.isPositionOccupied(new Position(row, col))) {
                    return false;
                }
                row--;
                col++;
            }
        }  else if (positionB.getRow() < positionA.getRow() && positionB.getColumn() < positionA.getColumn()) {
            int row = positionA.getRow() - 1;
            int col = positionA.getColumn() - 1;
            while (row > positionB.getRow() && col > positionB.getColumn()) {
                if (positionOccupationRetriever.isPositionOccupied(new Position(row, col))) {
                    return false;
                }
                row--;
                col--;
            }
        }
        return true;
    }

}
