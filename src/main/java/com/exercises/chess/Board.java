package com.exercises.chess;

import com.exercises.chess.piece.*;
import com.exercises.chess.piece.pathValidator.PositionOccupationRetriever;

/**
 * Created by amala on 27/10/18.
 */
public class Board implements PositionOccupationRetriever {
    private Piece[][] pieces = new Piece[8][8];

    public void setPieceAt(Piece piece, Position position) {
        Piece existingPiece = pieces[position.getRow()][position.getColumn()];
        if(existingPiece!=null) {
            existingPiece.setDead();
        }
        Position currentPosition = piece.getPosition();
        piece.move(position, this);
        pieces[currentPosition.getRow()][currentPosition.getColumn()] = null;
        pieces[position.getRow()][position.getColumn()] = piece;
    }

    public Piece getPieceAt(Position position) {
        return pieces[position.getRow()][position.getColumn()];
    }

    public void init() {
        for (int i=0; i<8; i++) {
            setPieceAtAndInitPosition(new Pawn(Color.BLACK), new Position(1, i));
            setPieceAtAndInitPosition(new Pawn(Color.WHITE), new Position(6, i));
        }
        setPieceAtAndInitPosition(new Elephant(Color.BLACK), new Position(0, 0));
        setPieceAtAndInitPosition(new Elephant(Color.BLACK), new Position(0, 7));
        setPieceAtAndInitPosition(new Elephant(Color.WHITE), new Position(7, 0));
        setPieceAtAndInitPosition(new Elephant(Color.WHITE), new Position(7, 7));

        setPieceAtAndInitPosition(new Horse(Color.BLACK), new Position(0, 1));
        setPieceAtAndInitPosition(new Horse(Color.BLACK), new Position(0, 6));
        setPieceAtAndInitPosition(new Horse(Color.WHITE), new Position(7, 1));
        setPieceAtAndInitPosition(new Horse(Color.WHITE), new Position(7, 6));

        setPieceAtAndInitPosition(new Bishop(Color.BLACK), new Position(0, 2));
        setPieceAtAndInitPosition(new Bishop(Color.BLACK), new Position(0, 5));
        setPieceAtAndInitPosition(new Bishop(Color.WHITE), new Position(7, 2));
        setPieceAtAndInitPosition(new Bishop(Color.WHITE), new Position(7, 5));

        setPieceAtAndInitPosition(new Queen(Color.BLACK), new Position(0, 3));
        setPieceAtAndInitPosition(new Queen(Color.WHITE), new Position(7, 3));

        setPieceAtAndInitPosition(new King(Color.BLACK), new Position(0, 4));
        setPieceAtAndInitPosition(new King(Color.WHITE), new Position(7, 4));
    }

    private void setPieceAtAndInitPosition(Piece piece, Position position) {
        piece.initPosition(position);
        pieces[position.getRow()][position.getColumn()] = piece;
    }

    @Override
    public String toString() {
        String piecesString = "";
        for (int i =0; i< this.pieces.length; i++) {
            for (int j=0; j<this.pieces[i].length; j++) {
                piecesString = piecesString + this.pieces[i][j]+"["+i+","+j+"] ";
            }
            piecesString = piecesString + "\n";
        }
        return piecesString;
    }

    public void executeMove(Player player, Position fromPosition, Position toPosition) {
        Piece pieceToBeMoved = getPieceAt(fromPosition);
        if(pieceToBeMoved == null || !player.getColor().equals(pieceToBeMoved.getPieceColor())) {
            throw new UnsupportedOperationException("Cannot execute move");
        }
        setPieceAt(pieceToBeMoved, toPosition);
    }

    @Override
    public boolean isPositionOccupied(Position position) {
        return getPieceAt(position) != null;
    }
}
