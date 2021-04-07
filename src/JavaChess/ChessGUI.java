package JavaChess;
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
        playTurn();
    }

    /**
     * where you make and get moves from game and change the visual board behavior accordingly
     */
    private void playTurn() {
        setDefaultColors();
        ArrayList<String> moves = game.getMoves();
        unclickedButtons(moves);
        //checkmate
        if (moves.size()==0) {
            return;
        }
    }

    /**
     * takes the start coordinates and makes the buttons interactive
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
//            button.addActionListener(new ActionListener() {
//                //button should set the moveTo squares interactive and green
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    for (int k=0; k<moveTo.size(); k++) {
//                        JButton b = new JButton();
//                        b.setMargin(new Insets(50, 50, 50, 50));
//                        b.setOpaque(true);
//                        b.setBorderPainted(false);
//                        b.setBackground(Color.green);
//                        //selecting the button of a square to move to should play the move
//                        int finalK = k;
////                        b.addActionListener(new ActionListener() {
////                            @Override
////                            public void actionPerformed(ActionEvent e){
////                                game.playMove(movablePieces.get(finalI) +":"+ moveTo.get(finalK));
////                                update();
////                            }
////                        });
//                        int[] coord = board.convertCoord(moveTo.get(k));
//                        chessBoardSquares[coord[0]][coord[1]] = b;
//                    }
//                }
//            });
        }
    }
//
//    /**
//     * resets the board to default colors, and sets pieces as they appear in board
//     */
//    private void update() {
//        chessBoard = new JPanel(new GridLayout(8, 8));
//        chessBoardSquares = new JButton[8][8];
//        for (int i=0; i<8; i++) {
//            for (int j = 0; j < 8; j++) {
//                JButton b = new JButton();
//                b.setMargin(new Insets(0, 0, 0, 0));
//                b.setOpaque(true);
//                b.setBorderPainted(true);
//                chessBoardSquares[i][j] = b;
//                chessBoard.add(b);
//            }
//        }
//        setDefaultColors();
//    }
//
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

    public void setMovablePieceAction(ChessButton b,ArrayList<String> moveTo,String pos) {

        ActionListener movable = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDefaultColors();
                //removing the moveTo functionality
                for (int i=0; i<8;i++) {
                    for (int j=0; j<8;j++) {
                        if(chessBoardSquares[i][j].getBackground() == Color.GREEN) {
                            chessBoardSquares[i][j].removeActionListener(chessBoardSquares[i][j].getCurAL());
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
        button.setCurAL(moveto);
        button.addActionListener(moveto);
    }

    public JPanel getBoard(){
        return chessBoard;
    }
}
