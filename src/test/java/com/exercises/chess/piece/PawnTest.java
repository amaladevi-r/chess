package com.exercises.chess.piece;

import com.exercises.chess.Board;
import com.exercises.chess.Color;
import com.exercises.chess.Position;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by amala on 27/10/18.
 */
public class PawnTest {

    @Mock
    private Board board;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void shouldMoveForward() throws Exception {
        Pawn pawn = initPawnInPosition(new Position(2, 1));
        Position positionToBeMoved = new Position(3, 1);
        pawn.move(positionToBeMoved, board);
        assertEquals(pawn.getPosition().getRow(), positionToBeMoved.getRow());
        assertEquals(pawn.getPosition().getColumn(), positionToBeMoved.getColumn());
    }

    @Test (expected = UnsupportedOperationException.class)
    public void shouldNotMoveBackward() throws Exception {
        Position initPosition = new Position(2, 1);
        Pawn pawn = initPawnInPosition(initPosition);
        pawn.move(new Position(initPosition.getRow() - 1, initPosition.getColumn()), board);
    }

    @Test (expected = UnsupportedOperationException.class)
    public void shouldMoveForwardByOnlyOne() throws Exception {
        Position initPosition = new Position(2, 1);
        Pawn pawn = initPawnInPosition(initPosition);
        pawn.move(new Position(initPosition.getRow()+2, initPosition.getColumn()), board);
        pawn.move(new Position(pawn.getPosition().getRow()+2, pawn.getPosition().getColumn()), board);
    }

    @Test
    public void shouldAllowMoveForwardByTwoForFirstMoveAlone() throws Exception {
        Position initPosition = new Position(2, 1);
        Pawn pawn = initPawnInPosition(initPosition);
        pawn.move(new Position(initPosition.getRow()+2, initPosition.getColumn()), board);

    }

    @Test (expected = UnsupportedOperationException.class)
    public void shouldNotAllowMoveSideways() throws Exception {
        Pawn pawn = initPawnInPosition(new Position(2, 1));
        pawn.move(new Position(pawn.getPosition().getRow(), pawn.getPosition().getColumn()+1), board);
    }

    @Test
    public void shouldAllowDiagonalMoveWhenKilling() throws Exception {
        Pawn pawn = initPawnInPosition(new Position(2, 1));
        Position positionToMove = new Position(3, 2);
        when(board.getPieceAt(positionToMove)).thenReturn(new Pawn(Color.WHITE));
        pawn.move(positionToMove, board);
    }

    @Test (expected = UnsupportedOperationException.class)
    public void shouldNotAllowDiagonalMoveForMoreThanOneStepEvenForKilling() throws Exception {
        Pawn pawn = initPawnInPosition(new Position(1, 1));
        Position positionToMove = new Position(3, 3);
        when(board.getPieceAt(positionToMove)).thenReturn(new Pawn(Color.WHITE));
        pawn.move(positionToMove, board);
    }

    @Test (expected = UnsupportedOperationException.class)
    public void shouldNotAllowMoveToAPositionWhereSameColorPieceExists() throws Exception {
        Pawn pawn = initPawnInPosition(new Position(1, 1));
        Position positionToMove = new Position(2, 1);
        when(board.getPieceAt(positionToMove)).thenReturn(new Pawn(Color.BLACK));
        pawn.move(positionToMove, board);

    }

    @Test (expected = UnsupportedOperationException.class)
    public void shouldNotAllowMovingInStraightLineWhenAPieceIsAlreadyPresent() throws Exception {
        Pawn pawn = initPawnInPosition(new Position(1, 1));
        Position positionToMove = new Position(2, 1);
        when(board.getPieceAt(positionToMove)).thenReturn(new Pawn(Color.WHITE));
        pawn.move(positionToMove, board);

    }

    @Test (expected = UnsupportedOperationException.class)
    public void shouldNotAllowJumpingOverPieces() throws Exception {
        Pawn pawn = initPawnInPosition(new Position(1, 1));
        when(board.getPieceAt(new Position(2, 1))).thenReturn(new Pawn(Color.BLACK));
        pawn.move(new Position(3, 1), board);
    }

    private Pawn initPawnInPosition(Position initPosition) {
        Pawn pawn = new Pawn(Color.BLACK);
        pawn.initPosition(initPosition);
        return pawn;
    }

}
