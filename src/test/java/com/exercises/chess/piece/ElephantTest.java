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
public class ElephantTest {

    @Mock
    private Board board;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void shouldMoveInStraightLine() throws Exception {
        Elephant elephant = initElephant(new Position(1, 7));
        Position positionToBeMoved = new Position(4, 7);
        elephant.move(positionToBeMoved, board);
        assertEquals(elephant.getPosition(), positionToBeMoved);
    }

    @Test (expected = UnsupportedOperationException.class)
    public void shouldMoveOnlyInStraightLine() throws Exception {
        Elephant elephant = initElephant(new Position(1, 7));
        elephant.move(new Position(3,3), board);
    }

    @Test (expected = UnsupportedOperationException.class)
    public void shouldNotBeAbleToJumpOverPiecesWhileMovingRowWise() throws Exception {
        Elephant elephant = initElephant(new Position(1, 7));
        Position positionToBeMoved = new Position(3, 7);
        Position positionOfSomePawn = new Position(2, 7);
        when(board.isPositionOccupied(positionOfSomePawn)).thenReturn(true);
        elephant.move(positionToBeMoved, board);
    }

    @Test (expected = UnsupportedOperationException.class)
    public void shouldNotBeAbleToJumpOverPiecesWhileMovingColumnWise() throws Exception {
        Elephant elephant = initElephant(new Position(1, 7));
        Position positionToBeMoved = new Position(1, 4);
        Position positionOfSomePawn = new Position(1, 6);
        when(board.isPositionOccupied(positionOfSomePawn)).thenReturn(true);
        elephant.move(positionToBeMoved, board);
    }

    @Test (expected = UnsupportedOperationException.class)
    public void shouldNotMoveToAPositionWhereASameColorPieceExists() throws Exception {
        Elephant elephant = initElephant(new Position(1, 7));
        Position positionToBeMoved = new Position(4, 7);
        when(board.getPieceAt(positionToBeMoved)).thenReturn(new Pawn(Color.BLACK));
        elephant.move(positionToBeMoved, board);
    }

    private Elephant initElephant(Position position) {
        Elephant elephant = new Elephant(Color.BLACK);
        elephant.initPosition(position);
        return elephant;
    }


}
