package JavaChess;

import JavaChess.ChessPieces.ActionsBehaviors.ActionsBehavior;
import JavaChess.ChessPieces.ActionsBehaviors.KingBehaviorStandard;
import JavaChess.ChessPieces.ActionsBehaviors.StartBehavior;
import JavaChess.ChessPieces.ChessPiece;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * class wherein players will be able to manipulate the board and make valid moves
 */
public class Player {
    private Board board;
    private Player opponent;
    //List of pieces a player has control of
    private ArrayList<ChessPiece> pieces = new ArrayList<>();
    public Player() {

    }

    /**
     * plays the move given as parameter and updates all values accordingly including piece behaviors and opponents information
     * does not do any checking for the validity of moves
     */
    public void playMove(String move) {
        String start = Character.toString(move.charAt(0)) + Character.toString(move.charAt(1));
        String end = Character.toString(move.charAt(3)) + Character.toString(move.charAt(4));
        ChessPiece movedPiece = board.get(start).getPiece();
        //if a piece has actions tied to not having been moves, removes the starting behavior after the move
        if (movedPiece.getActions() instanceof StartBehavior) {
            ((StartBehavior) movedPiece.getActions()).updateActions(movedPiece);
        }
        //moves the rook first
        if (isCastle(move)) {
            //left/long castle
            if (start.charAt(0) > end.charAt(0)) {
                this.playMove("A"+Character.toString(start.charAt(1))+":D"+Character.toString(start.charAt(1)));
            }
            else { //short/right castle
                this.playMove("H"+Character.toString(start.charAt(1))+":F"+Character.toString(start.charAt(1)));
            }
        }
        //moves the king
        board.makeMove(move);
        //if a move is a castle, add in the moving of the rook to the move
        opponent.update();
    }

    /**
     * makes a list of possible moves concatenated by combining all possible moves from the methods inside of each piece,
     */
    public ArrayList<String> getAvailableMoves() {
        //getting all actions not checking being put in check
        ArrayList<String> availableMoves = getAllActions();
        ArrayList<String> bestMoves = new ArrayList<String>();
        String move;
        for (int i=0; i< availableMoves.size(); i++) {
            move = availableMoves.get(i);
            if (this.selfCheck(move)) {
                bestMoves.add(move);
            }
        }
        return bestMoves;
    }
    /**
     * gets all actions a player can make not checking moves that put the player into check
     */
    public ArrayList<String> getAllActions() {
        ArrayList<String> actions = new ArrayList<String>();
        ChessPiece currPiece;
        String startPos;
        String nextPos;
        for (int i=0; i<pieces.size();i++) {
            currPiece = pieces.get(i);
            startPos = board.get(currPiece).getPosition();
            ArrayList<String> moves =  currPiece.candidateMoves(board);
            for (int j=0; j< moves.size();j++){
                nextPos = moves.get(j);
                String move = startPos+":"+nextPos;
                if (!pieces.contains(board.get(nextPos).getPiece())) {
                    actions.add(startPos+":"+nextPos);
                }
            }
        }
        return actions;
    }

    /**
     * Checks to make sure the move doesnt put the player in check, and checks to make sure the move only attacks
     * opposing pieces
     */
    public boolean selfCheck(String move) {
        String start = Character.toString(move.charAt(0)) + Character.toString(move.charAt(1));
        String end = Character.toString(move.charAt(3)) + Character.toString(move.charAt(4));

        //If an attempted castle make sure the king doesnt pass through any attacked squares
        if (isCastle(move)){
            ArrayList<String> endCoords = filterEndCoords(opponent.getAllActions());
            //can't castle if you're currently in check
            if (endCoords.contains(start)){
                return false;
            }
            //can't castle if any if the squares between your destination are under attack
            String currPos = start;
            while(!currPos.equals(end)) {
                //castle left
                if (start.charAt(0)>end.charAt(0)) {
                    currPos = Character.toString(currPos.charAt(0)-1) + Character.toString(currPos.charAt(1));
                }
                else { //castle right
                    currPos = Character.toString(currPos.charAt(0)+1) + Character.toString(currPos.charAt(1));
                }
                if (endCoords.contains(currPos)) return false;
            }
            return true;

        }
        Player testPlayer = opponent.copy();
        Board testBoard = testPlayer.getBoard();
        //make a move on the test board
        testBoard.makeMove(move);
        //check where the king is
        Square king = testBoard.get(pieces.get(0));
        //making sure test player registers the move if there is a take
        testPlayer.update();
        //checking if making the move would put your own king under attack
        ArrayList<String> testMoves = filterEndCoords(testPlayer.getAllActions());
        if (testMoves.contains(king.getPosition())){
            return false;
        }
        return true;
    }

    /**
     * helper function to check if a move is an attempt to castle
     */
    public boolean isCastle(String move) {
        String start = Character.toString(move.charAt(0)) + Character.toString(move.charAt(1));
        String end = Character.toString(move.charAt(3)) + Character.toString(move.charAt(4));
        ActionsBehavior kTest = new KingBehaviorStandard();
        if (board.get(start).getPiece() == pieces.get(0) && !kTest.actions(pieces.get(0),board).contains(end)){
            return true;
        }
        return false;
    }

    /**
     * makes a list of ending coordniates from a list of startCoord:endCoord
     */
    public static ArrayList<String> filterEndCoords(ArrayList<String> moves) {
        ArrayList<String> endCoords = new ArrayList<String>();
        for (int i=0; i<moves.size();i++) {
            String move = moves.get(i);
            endCoords.add(Character.toString(move.charAt(3)) + Character.toString(move.charAt(4)));
        }
        return endCoords;
    }

    /**
     * Iterate over the board checking to find each piece in the arraylist, if one is missing, it is removed
     */
    public void update() {
        ArrayList<ChessPiece> toRemove = new ArrayList<ChessPiece>();
        for (int i =0; i<pieces.size(); i++){
            if (board.get(pieces.get(i)) == null) {
                toRemove.add(pieces.get(i));
            }
        }
        for (int i=0; i < toRemove.size(); i++) {
            pieces.remove(toRemove.get(i));
        }
    }

    /**
     * Create a copy of player without using any of the same memory for anything but chess Pieces
     */
    public Player copy() {
        Player copy = new Player();
        copy.setBoard(board.copy());
        copy.addPieces(pieces);
        copy.opponent = opponent;
        return copy;
    }

    /**
     * takes in a chess piece and adds it to the pieces list
     */
    public void addPieces(ChessPiece[] pieces) {
        this.pieces.addAll(Arrays.asList(pieces));
    }
    public void addPieces(ArrayList<ChessPiece> pieces) {
        this.pieces.addAll(pieces);
    }

    //getters for private vars
    public ArrayList<ChessPiece> getPieces() { return pieces; }
    public Board getBoard() { return board; }

    //setters for private vars
    public void setBoard(Board board) { this.board = board; }
    public void setOpponent(Player opponent) { this.opponent = opponent; }
}
