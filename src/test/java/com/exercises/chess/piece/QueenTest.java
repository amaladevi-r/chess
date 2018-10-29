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
public class QueenTest {

    @Mock
    private Board board;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void shouldMoveInStraightLines() throws Exception {
        Queen queen = initQueen(new Position(2, 4));
        Position positionToBeMoved = new Position(5, 4);
        queen.move(positionToBeMoved, board);
        assertEquals(queen.getPosition(), positionToBeMoved);
    }


    @Test
    public void shouldMoveInDiagonal() throws Exception {
        Queen queen = initQueen(new Position(2, 4));
        Position positionToBeMoved = new Position(4, 6);
        queen.move(positionToBeMoved, board);
        assertEquals(queen.getPosition(), positionToBeMoved);
    }

    @Test (expected = UnsupportedOperationException.class)
    public void shouldMoveOnlyInStraightLines() throws Exception {
        Queen queen = initQueen(new Position(2, 4));
        queen.move(new Position(3, 6), board);

    }

    @Test (expected = UnsupportedOperationException.class)
    public void shouldNotMoveToAPositionWhereSameColorPieceExists() throws Exception {
        Queen queen = initQueen(new Position(2, 4));
        Position positionToBeMoved = new Position(5, 4);
        when(board.getPieceAt(positionToBeMoved)).thenReturn(new Pawn(Color.BLACK));
        queen.move(positionToBeMoved, board);

    }

    @Test (expected = UnsupportedOperationException.class)
    public void shouldNotJumpOverPiecesInStraightLine() throws Exception {
        Queen queen = initQueen(new Position(2, 4));
        Position positionToBeMoved = new Position(5, 4);
        when(board.isPositionOccupied(new Position(3, 4))).thenReturn(true);
        queen.move(positionToBeMoved, board);
    }

    @Test (expected = UnsupportedOperationException.class)
    public void shouldNotJumpOverPiecesInDiagonal() throws Exception {
        Queen queen = initQueen(new Position(2, 4));
        Position positionToBeMoved = new Position(4, 6);
        when(board.isPositionOccupied(new Position(3, 5))).thenReturn(true);
        queen.move(positionToBeMoved, board);
    }

    private Queen initQueen(Position position) {
        Queen queen = new Queen(Color.BLACK);
        queen.initPosition(position);
        return queen;
    }

}
