package com.exercises.chess.piece;

import com.exercises.chess.Board;
import com.exercises.chess.Color;
import com.exercises.chess.Position;
import com.exercises.chess.piece.Horse;
import com.exercises.chess.piece.Piece;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by amala on 27/10/18.
 */
public class HorseTest {

    @Mock
    private Board board;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void shouldMoveInLShape() throws Exception {
        Horse horse = initHorse(new Position(1, 7));
        Position positionToBeMoved = new Position(3, 6);
        horse.move(positionToBeMoved, board);
        assertEquals(horse.getPosition(), positionToBeMoved);
    }

    private Horse initHorse(Position position) {
        Horse horse = new Horse(Color.BLACK);
        horse.initPosition(position);
        return horse;
    }

    @Test (expected = UnsupportedOperationException.class)
    public void shouldMoveOnlyInLShape() throws Exception {
        Horse horse = initHorse(new Position(1, 7));
        horse.move(new Position(1,8), board);
    }

    @Test (expected = UnsupportedOperationException.class)
    public void shouldNotMoveToAPositionWhereThePieceIsSameColor() throws Exception {
        Horse horse = initHorse(new Position(1, 7));
        Position positionToBeMoved = new Position(3, 6);
        when(board.getPieceAt(positionToBeMoved)).thenReturn(new Pawn(Color.BLACK));
        horse.move(positionToBeMoved, board);
    }
}
