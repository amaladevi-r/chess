package com.exercises.chess;

import com.exercises.chess.piece.Pawn;
import com.exercises.chess.piece.Piece;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

/**
 * Created by amala on 27/10/18.
 */
public class BoardTest {

    @Test
    public void shouldGivePieceInPosition() throws Exception {
        Board board = new Board();
        Piece piece = mock(Piece.class);
        Position position = new Position(2, 1);
        when(piece.getPosition()).thenReturn(new Position(2, 1));
        board.setPieceAt(piece, position);
        assertEquals(board.getPieceAt(position), piece);
    }

    @Test
    public void shouldSetExistingPieceDeadWhenSomePieceMovesToItsPosition() throws Exception {
        Board board = new Board();
        Piece existingPiece = mock(Piece.class);
        Piece pieceToMove = mock(Piece.class);
        when(pieceToMove.getPosition()).thenReturn(new Position(0, 1));
        when(existingPiece.getPosition()).thenReturn(new Position(1, 1));
        board.setPieceAt(existingPiece, new Position(1,1));
        board.setPieceAt(pieceToMove, new Position(1,1));
        verify(existingPiece).setDead();
    }

    @Test (expected = UnsupportedOperationException.class)
    public void shouldNotExecuteMoveWhenNoPieceFoundInFromPosition() throws Exception {
        Board board = new Board();
        Player whitePlayer = new Player(Color.WHITE);
        board.executeMove(whitePlayer, new Position(1, 1), new Position(2, 1));
    }

    @Test (expected = UnsupportedOperationException.class)
    public void shouldOnlyMovePieceThatBelongsToThePlayer() throws Exception {
        Board board = new Board();
        Piece piece = mock(Piece.class);
        when(piece.getPosition()).thenReturn(new Position(1, 1));
        board.setPieceAt(piece, new Position(1, 1));
        Player whitePlayer = new Player(Color.WHITE);
        board.executeMove(whitePlayer, new Position(1, 1), new Position(2, 1));
    }

    @Test
    public void shouldMoveThePieceWhenAskedByPlayer() throws Exception {
        Board board = new Board();
        Piece mockPiece = mock(Piece.class);
        Position positionOfMockPiece = new Position(1, 1);
        when(mockPiece.getPieceColor()).thenReturn(Color.BLACK);
        when(mockPiece.getPosition()).thenReturn(positionOfMockPiece);
        board.setPieceAt(mockPiece, positionOfMockPiece);
        Position toPosition = new Position(2, 1);
        board.executeMove(new Player(Color.BLACK), positionOfMockPiece, toPosition);
        verify(mockPiece).move(toPosition, board);
        assertEquals(board.getPieceAt(toPosition), mockPiece);
        assertNull(board.getPieceAt(positionOfMockPiece));
    }
}
