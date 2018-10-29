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
public class BishopTest {

    @Mock
    private Board board;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void shouldMoveInDiagonal() throws Exception {
        Bishop bishop = initBishop(new Position(2, 3));
        Position positionToBeMoved = new Position(5, 6);
        bishop.move(positionToBeMoved, board);
        assertEquals(bishop.getPosition(), positionToBeMoved);
    }


    @Test (expected = UnsupportedOperationException.class)
    public void shouldMoveOnlyInDiagonal() throws Exception {
        Bishop bishop = initBishop(new Position(2, 3));
        bishop.move(new Position(1, 5), board);
    }

    @Test (expected = UnsupportedOperationException.class)
    public void shouldNotMoveToAPositionWhereSamePieceColorExists() throws Exception {
        Bishop bishop = initBishop(new Position(2, 3));
        Position positionToBeMoved = new Position(3, 4);
        when(board.getPieceAt(positionToBeMoved)).thenReturn(new Pawn(Color.BLACK));
        bishop.move(positionToBeMoved, board);
    }

    @Test (expected = UnsupportedOperationException.class)
    public void shouldNotAllowJumpingOverPiecesInForwardRightDiagonal() throws Exception {
        Bishop bishop = initBishop(new Position(2, 3));
        when(board.isPositionOccupied(new Position(3, 4))).thenReturn(true);
        bishop.move(new Position(5, 6), board);

    }

    @Test (expected = UnsupportedOperationException.class)
    public void shouldNotAllowJumpingOverPiecesInForwardLeftDiagonal() throws Exception {
        Bishop bishop = initBishop(new Position(2, 4));
        when(board.isPositionOccupied(new Position(3, 3))).thenReturn(true);
        bishop.move(new Position(5, 1), board);

    }

    @Test (expected = UnsupportedOperationException.class)
    public void shouldNotAllowJumpingOverPiecesInBackwardRightDiagonal() throws Exception {
        Bishop bishop = initBishop(new Position(5, 3));
        when(board.isPositionOccupied(new Position(4, 4))).thenReturn(true);
        bishop.move(new Position(3, 5), board);

    }


    @Test (expected = UnsupportedOperationException.class)
    public void shouldNotAllowJumpingOverPiecesInBackwardLeftDiagonal() throws Exception {
        Bishop bishop = initBishop(new Position(5, 6));
        when(board.isPositionOccupied(new Position(4, 5))).thenReturn(true);
        bishop.move(new Position(3, 4), board);

    }


    private Bishop initBishop(Position position) {
        Bishop bishop = new Bishop(Color.BLACK);
        bishop.initPosition(position);
        return bishop;
    }

}
