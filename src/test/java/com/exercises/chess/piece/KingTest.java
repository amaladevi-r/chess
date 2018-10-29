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
 * Created by amala on 28/10/18.
 */
public class KingTest {

    @Mock
    private Board board;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void shouldAllowMovingOneStepInAnyDirection() throws Exception {
        King king = initKing(new Position(1, 5));
        Position positionToBeMoved = new Position(2, 5);
        king.move(positionToBeMoved, board);
        assertEquals(king.getPosition(), positionToBeMoved);
    }

    @Test (expected = UnsupportedOperationException.class)
    public void shouldAllowMovingOnlyOneStep() throws Exception {
        King king = initKing(new Position(1, 5));
        king.move(new Position(3, 5), board);
    }

    @Test (expected = UnsupportedOperationException.class)
    public void shouldNotMoveToAPositionWherePieceOfSameColorExists() throws Exception {
        King king = initKing(new Position(1, 5));
        Position positionToBeMoved = new Position(2, 5);
        when(board.getPieceAt(positionToBeMoved)).thenReturn(new Pawn(Color.BLACK));
        king.move(positionToBeMoved, board);

    }

    private King initKing(Position position) {
        King king = new King(Color.BLACK);
        king.initPosition(position);
        return king;
    }

}
