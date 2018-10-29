package com.exercises.chess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by amala on 27/10/18.
 */
public class Game {
    private Player blackPlayer;
    private Player whitePlayer;
    private boolean isBlackPlayerTurn;
    private Board board;

    public void start() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            Player playerToTurn = null;
            try {
                playerToTurn = getPlayerToTurn();
                System.out.println(board);
                System.out.println("Player to play " + playerToTurn.getColor());
                System.out.println("Enter from position: x");
                String fromPositionX = bufferedReader.readLine();
                System.out.println("Enter from position: y");
                String fromPositionY = bufferedReader.readLine();
                System.out.println("Enter to position: x");
                String toPositionX = bufferedReader.readLine();
                System.out.println("Enter to position: y");
                String toPositionY = bufferedReader.readLine();
                System.out.println("Enter instruction type: MOVE, CHECK, CHECKMATE");
                String instructionType = bufferedReader.readLine();
                play(playerToTurn, InstructionType.valueOf(instructionType),
                        new Position(Integer.parseInt(fromPositionX), Integer.parseInt(fromPositionY)),
                        new Position(Integer.parseInt(toPositionX), Integer.parseInt(toPositionY)));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (GameOverException e) {
                System.out.println("Game over player " + playerToTurn.getColor() + " won");
                break;
            } catch (UnsupportedOperationException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void init(Player blackPlayer, Player whitePlayer) {
        this.blackPlayer = blackPlayer;
        this.whitePlayer = whitePlayer;
        board = new Board();
        board.init();
        isBlackPlayerTurn = true;
    }

    public Player getPlayerToTurn() {
        if (isBlackPlayerTurn)
        return blackPlayer;
        return whitePlayer;
    }

    public void play(Player player, InstructionType instructionType, Position fromPosition, Position toPosition) throws GameOverException {
        if (isBlackPlayerTurn && !player.getColor().equals(Color.BLACK)) {
            throw new UnsupportedOperationException();
        }
        else if (!isBlackPlayerTurn && !player.getColor().equals(Color.WHITE)) {
            throw new UnsupportedOperationException();
        }
        if (instructionType.equals(InstructionType.CHECKMATE)) {
            throw new GameOverException();
        }
        board.executeMove(player, fromPosition, toPosition);
        isBlackPlayerTurn = !isBlackPlayerTurn;
    }
}
