package JavaChess;

import JavaChess.SetUp.SetUp;

import java.util.ArrayList;

/**
 * Drives players to make alternating moves
 */
public class Game {
    //index 0 = white, 1 = black
    private Player[] players = new Player[2];
    private int turnFlag;

    /**
     * Initializes nothing, all defaults/null
     */
    public Game() {

    }

    /**
     * gets actions for current player
     */
    public ArrayList<String> getMoves() {
        return players[turnFlag].getAvailableMoves();
    }

    /**
     * plays move for current player and flips turns
     */
    public void playMove(String move) {
        players[turnFlag].playMove(move);
        swapTurns();
    }
    /**
     * initializes the game with a setup behavior to set the board and all pieces and thier behaviors
     */
    public void setUp(SetUp setup) {
        setup.setUpGame(this);
    }

    /**
     * flips turn flag
     */
    public void swapTurns() {
        turnFlag = 1 - turnFlag;
    }

    //getters for private vars
    public Player[] getPlayers() { return players; }
    public int getTurnFlag() { return turnFlag; }
}
