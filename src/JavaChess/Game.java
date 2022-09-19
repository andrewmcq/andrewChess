package JavaChess;

import JavaChess.ChessPieces.ChessPiece;
import JavaChess.SetUp.SetUp;
import java.util.Collections;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Drives players to make alternating moves
 */
public class Game {
    //index 0 = white, 1 = black
    private Player[] players = new Player[2];
    private int turnFlag;
    private HashMap<String,Integer> repetitions = new HashMap<String, Integer>();
    private int fiftyMovesCounter = 0;

    /**
     * Initializes nothing, all defaults/null
     */
    public Game() {

    }

    /**
     * gets actions for current player, meant to only be called once before each players makes a move.
     */
    public ArrayList<String> getMoves() {
        //if threefold repetition or if fiftymoves without a take happen
        if (updateRepetitions() ==3 || fiftyMovesCounter==100) {
            return new ArrayList<String>();
        }
        else {
            return players[turnFlag].getAvailableMoves();
        }
    }

    /**
     * plays move for current player and flips turns
     */
    public void playMove(String move) {
        int startSize = players[1-turnFlag].getPieces().size();
        String start = Character.toString(move.charAt(0)) + Character.toString(move.charAt(1));
        String piece = players[turnFlag].getBoard().get(start).getPiece().getName();
        players[turnFlag].playMove(move);
        //50 moves reset if the move made the opponent have one less piece, or if a pawn was moved
        if (startSize != players[1-turnFlag].getPieces().size() || piece.charAt(0) =='p')
            fiftyMovesCounter = 0;
        else
            fiftyMovesCounter += 1;
        swapTurns();
    }

    /**
     * Combines piece position with piece type to a string, with player turn, then avaliable moves, hashed to determine
     * a single instance of a board state
     * Starts with a 1 or 0 indicating player, then 64 character noting as empty or not, followed by avaliable moves for player.
     * returns the number of repetitions for a given resultant state
     */
    private int updateRepetitions() {
        Square[][] board = players[turnFlag].getBoard().getBoard();
        String hashable = Integer.toString(turnFlag);
        //iterates over the board and places a 2 letter code for every spot
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                ChessPiece piece = board[i][j].getPiece();
                if (piece==null){
                    hashable += "ee"; //if a square is empty, ee is the placeholder
                }
                else{
                    String name = piece.getName();
                    //if a piece is white it is prepended by a 0, or 1 if black
                    if (players[turnFlag].getPieces().contains(piece)){
                        hashable+=Integer.toString(turnFlag);
                    }
                    else {
                        hashable+=Integer.toString(1-turnFlag);
                    }
                    switch (name) {
                        case "king":
                            hashable+="k";
                            break;
                        case "knight":
                            hashable+="n";
                            break;
                        default:
                            hashable+=Character.toString(name.charAt(0));
                            break;
                    }
                }
            }
        }
        //Now determine if the avalaible moves are the same given the circumstances. This is to account for loss of ability
        //to castle or loss of ability to enpassant
        ArrayList<String> sortedMoves = players[turnFlag].getAvailableMoves();
        Collections.sort(sortedMoves);
        for (int i=0; i< sortedMoves.size(); i++){
            hashable+= sortedMoves.get(i);
        }
        Integer val = repetitions.get(hashable);
        if (val == null){
            repetitions.put(hashable,1);
            val = 1;
        }
        else {
            val += 1;
            repetitions.put(hashable,val);
        }
        return val;
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
