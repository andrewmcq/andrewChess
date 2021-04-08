package JavaChess;
import JavaChess.ChessPieces.ChessPiece;
import JavaChess.ChessPieces.*;
import JavaChess.SetUp.StandardGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * class to handle the gui driving the game class
 */
public class ChessGUI {
    private JPanel chessBoard;
    private ChessButton[][] chessBoardSquares;
    private Game game;
    private Board board;

    public ChessGUI() {
        //initializing variables
        chessBoard = new JPanel(new GridLayout(8, 8));
        chessBoardSquares = new ChessButton[8][8];
        game = new Game();
        game.setUp(new StandardGame());
        board = game.getPlayers()[0].getBoard();

        //setting each square of the board
        for (int i=0; i<8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessButton b = new ChessButton();
                b.setMargin(new Insets(0, 0, 0, 0));
                b.setOpaque(true);
                b.setBorderPainted(false);
                b.setVisible(true);
                chessBoardSquares[i][j] = b;
            }
        }
        //adding the squares to panel so [0][0] is A1(bottom left)
        for (int i=7;i>=0;i--) {
            for (int j=0; j<8; j++) {
                chessBoard.add(chessBoardSquares[j][i]);
            }
        }
        setDefaultColors();
        game.setUp(new StandardGame());
    }

    /**
     * Get the possible moves from the game and send to unclicked buttons
     * if no moves, game ends in checkmate here
     */
    public void playTurn() {
        board = game.getPlayers()[0].getBoard();
        updateBoardIcons();
        setDefaultColors();
        resetActionListeners();
        ArrayList<String> moves = game.getMoves();
        unclickedButtons(moves);
        //checkmate
        if (moves.size()==0) {
            return;
        }
    }

    /**
     * takes the start coordinates and calls setMovablePieceAction on the correct squares and passes their moveTos with it
     */
    private void unclickedButtons(ArrayList<String> moves) {
        ArrayList<String> movablePieces = new ArrayList<String>();
        //making list of pieces that can move
        for (int i=0; i<moves.size(); i++) {
            String coord = Character.toString(moves.get(i).charAt(0)) + Character.toString(moves.get(i).charAt(1));
            if (!movablePieces.contains(coord)) {
                movablePieces.add(coord);
            }
        }
        ArrayList<String> end = Player.filterEndCoords(moves);
        //for movable pieces, if you select them, highlight its square and the squares it can move to, and make them interactive
        for (int i=0; i<movablePieces.size(); i++) {
            ArrayList<String> moveTo = new ArrayList<String>();
            //grabbing end squares for specific piece
            for (int j=0; j<end.size();j++) {
                if (movablePieces.get(i).equals(Character.toString(moves.get(j).charAt(0)) + Character.toString(moves.get(j).charAt(1)))) {
                    moveTo.add(end.get(j));
                }
            }
            int[] coord = board.convertCoord(movablePieces.get(i));
            ChessButton button = chessBoardSquares[coord[0]][coord[1]];
            setMovablePieceAction(button,moveTo,movablePieces.get(i));
        }
    }

    /**
     * sets chess board to a standard chess board coloring
     */
    private void setDefaultColors() {
        for (int i=0;i<8;i++) {
            for (int j=0;j<8;j++) {
                JButton button = chessBoardSquares[i][j];
                if (i%2 == 0) {
                    if(j%2 == 0) {
                        button.setBackground(Color.lightGray);
                    }
                    else {
                        button.setBackground(Color.darkGray);
                    }
                }
                else {
                    if(j%2 == 0) {
                        button.setBackground(Color.darkGray);
                    }
                    else {
                        button.setBackground(Color.lightGray);
                    }
                }
            }
        }
    }

    /**
     * sets the actions for squares containing pieces that can move
     */
    public void setMovablePieceAction(ChessButton b,ArrayList<String> moveTo,String pos) {

        ActionListener movable = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDefaultColors();
                //removing the moveTo functionality
                for (int i=0; i<8;i++) {
                    for (int j=0; j<8;j++) {
                        if(chessBoardSquares[i][j].getMoveTo()) {
                            chessBoardSquares[i][j].removeActionListener(chessBoardSquares[i][j].getCurAL());
                            chessBoardSquares[i][j].setMoveTo(false);
                            chessBoardSquares[i][j].setCurAL(null);
                        }
                    }
                }
                //sets up the squares this piece can move to
                for (int i=0; i<moveTo.size(); i++) {
                    int[] c = board.convertCoord(moveTo.get(i));
                    ChessButton button = chessBoardSquares[c[0]][c[1]];
                    button.setBackground(Color.green);
                    setMoveToPieceAction(button,pos,moveTo.get(i));

                }
            }
        };
        b.setCurAL(movable);
        b.addActionListener(movable);
    }

    /**
     * sets up the destination squares after a movable piece is clicked
     * should remove all other moveTo action listeners
     */
    public void setMoveToPieceAction(ChessButton button, String pos, String move) {
        ActionListener moveto = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //updates game with move
                game.playMove(pos+":"+move);
                //return back to movable pieces
                playTurn();
            }
        };
        button.setMoveTo(true);
        button.setCurAL(moveto);
        button.addActionListener(moveto);
    }

    /**
     * removes all action listeners from the chess buttons, and sets the moveto attribute to false
     */
    public synchronized void resetActionListeners() {
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++) {
                ChessButton b = chessBoardSquares[i][j];
                b.setMoveTo(false);
                if (b.getCurAL() != null) {
                    b.removeActionListener(b.getCurAL());
                }
            }
        }
    }

    /**
     * iterates of the Board class and sets the chess icons properly based on it
     */
    public void updateBoardIcons() {
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                ChessButton b =chessBoardSquares[i][j];
                ChessPiece p = board.getBoard()[i][j].getPiece();
                if (p != null) {
                    if (game.getPlayers()[0].getPieces().contains(p)) {
                        if (p instanceof Pawn) {
                            b.setIcon(new ImageIcon("./assets/whitePawn.png"));
                        }
                        else if (p instanceof Knight) {
                            b.setIcon(new ImageIcon("./assets/whiteKnight.png"));
                        }
                        else if (p instanceof Bishop) {
                            b.setIcon(new ImageIcon("./assets/whiteBishop.png"));
                        }
                        else if (p instanceof Rook) {
                            b.setIcon(new ImageIcon("./assets/whiteRook.png"));
                        }
                        else if (p instanceof Queen) {
                            b.setIcon(new ImageIcon("./assets/whiteQueen.png"));
                        }
                        else if (p instanceof King) {
                            b.setIcon(new ImageIcon("./assets/whiteKing.png"));
                        }
                    }
                    else if (game.getPlayers()[1].getPieces().contains(p)){
                        if (p instanceof Pawn) {
                            b.setIcon(new ImageIcon("./assets/blackPawn.png"));
                        }
                        else if (p instanceof Knight) {
                            b.setIcon(new ImageIcon("./assets/blackKnight.png"));
                        }
                        else if (p instanceof Bishop) {
                            b.setIcon(new ImageIcon("./assets/blackBishop.png"));
                        }
                        else if (p instanceof Rook) {
                            b.setIcon(new ImageIcon("./assets/blackRook.png"));
                        }
                        else if (p instanceof Queen) {
                            b.setIcon(new ImageIcon("./assets/blackQueen.png"));
                        }
                        else if (p instanceof King) {
                            b.setIcon(new ImageIcon("./assets/blackKing.png"));
                        }
                    }
                }
                else {
                    b.setIcon(null);
                }
            }
        }
    }

    //getters
    public JPanel getBoard() { return chessBoard; }
    public ChessButton[][] getChessBoardSquares() { return chessBoardSquares; }
    public Game getGame() { return game; }
}
