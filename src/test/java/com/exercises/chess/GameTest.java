package com.exercises.chess;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by amala on 27/10/18.
 */
public class GameTest {

    @Test
    public void shouldGiveEqualTurnsToPlayer() throws Exception {
        Player blackPlayer = new Player(Color.BLACK);
        Player whitePlayer = new Player(Color.WHITE);
        Game game = startGame(blackPlayer, whitePlayer);
        assertEquals(game.getPlayerToTurn(), blackPlayer);
        game.play(blackPlayer, InstructionType.MOVE, new Position(1, 1), new Position(2, 1));
        assertEquals(game.getPlayerToTurn(), whitePlayer);
    }

    @Test (expected = UnsupportedOperationException.class)
    public void shouldNotAllowWhitePlayerToPlayOutOfTurn() throws GameOverException {
        Player blackPlayer = new Player(Color.BLACK);
        Player whitePlayer = new Player(Color.WHITE);
        Game game = startGame(blackPlayer, whitePlayer);
        game.play(whitePlayer, InstructionType.MOVE, new Position(1, 1), new Position(2, 1));
    }

    @Test (expected = UnsupportedOperationException.class)
    public void shouldNotAllowBlackPlayerToPlayOutOfTurn() throws GameOverException {
        Player blackPlayer = new Player(Color.BLACK);
        Player whitePlayer = new Player(Color.WHITE);
        Game game = startGame(blackPlayer, whitePlayer);
        game.play(blackPlayer, InstructionType.MOVE, new Position(1, 1), new Position(2, 1));
        game.play(blackPlayer, InstructionType.MOVE, new Position(1, 1), new Position(2, 1));
    }

    private Game startGame(Player blackPlayer, Player whitePlayer) {
        Game game = new Game();
        game.init(blackPlayer, whitePlayer);
        return game;
    }

}
