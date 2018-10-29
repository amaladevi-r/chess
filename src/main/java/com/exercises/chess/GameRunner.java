package com.exercises.chess;

/**
 * Created by amala on 28/10/18.
 */
public class GameRunner {
    public static void main(String[] args) {
        Game game = new Game();
        Player playerBlack = new Player(Color.BLACK);
        Player playerWhite = new Player(Color.WHITE);
        game.init(playerBlack, playerWhite);
        game.start();
    }
}
