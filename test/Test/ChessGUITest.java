package Test;

import JavaChess.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * testing attributes of gui are set properly at certain times
 */
public class ChessGUITest {
    private ChessGUI gui;
    private JPanel chessBoard;

    @BeforeEach
    public void setUp() {
        gui = new ChessGUI();
        chessBoard = gui.getBoard();
    }

    //testing constructor sets the squares properly
    @Test
    public void testGUIConstructor() {
        ChessButton[][] b = gui.getChessBoardSquares();
        for (int i=0; i<8;i++) {
            for (int j=0; j<8;j++) {
                assertTrue(b[i][j].isVisible());
                assertTrue(b[i][j].isOpaque());
                assertFalse(b[i][j].isBorderPainted());
            }
        }
    }

    /**
     * testing colors are only grays and alternate
     */
    @Test
    public void testSetDefaultColors() {
        ChessButton[][] b = gui.getChessBoardSquares();
        assertEquals(Color.lightGray,b[0][0].getBackground());
        assertEquals(Color.darkGray,b[0][1].getBackground());
        assertEquals(Color.darkGray,b[1][0].getBackground());
        for (int i=0; i<8;i++) {
            for (int j=0; j<6;j++) {
                assertEquals(b[i][j].getBackground(),b[i][j+2].getBackground());
                assertNotEquals(b[i][j].getBackground(),b[i][j+1].getBackground());
            }
        }
        for (int i=0; i<6;i++) {
            for (int j=0; j<8;j++) {
                assertEquals(b[i][j].getBackground(),b[i+2][j].getBackground());
                assertNotEquals(b[i][j].getBackground(),b[i+1][j].getBackground());
            }
        }
    }

    /**
     * testing that piece in pos gets correct action listener
     */
    @Test
    public void testSetMovablePieceAction() {
        ChessButton[][] b = gui.getChessBoardSquares();
        ArrayList<String> moveTo = new ArrayList<>();
        //A1 should get a new action listener that changes A2 and A3
        moveTo.add("A2");
        moveTo.add("A3");
        assertNull(b[0][0].getCurAL());
        gui.setMovablePieceAction(b[0][0],moveTo,"A1");
        //A1 should now have an action listener, but a2 and a3 should still be unchanged for now
        assertNotNull(b[0][0].getCurAL());
        assertNull(b[0][1].getCurAL());
        assertNull(b[0][2].getCurAL());
        //simulating click
        b[0][0].doClick();
        //moveTo buttons should now have a new AL and have moveTo == true
        assertNotNull(b[0][1].getCurAL());
        assertTrue(b[0][1].getMoveTo());
        assertNotNull(b[0][2].getCurAL());
        assertTrue(b[0][2].getMoveTo());

        //now testing that the AL gets removed from previous buttons when new button presed
        moveTo.clear();
        moveTo.add("B2");
        moveTo.add("B3");
        gui.setMovablePieceAction(b[1][0],moveTo,"B1");
        b[1][0].doClick();

        //new moveTo buttons changed
        assertNotNull(b[1][1].getCurAL());
        assertTrue(b[1][1].getMoveTo());
        assertNotNull(b[1][2].getCurAL());
        assertTrue(b[1][2].getMoveTo());

        //old moveTo buttons invalidated
        assertNotNull(b[0][0].getCurAL());
        assertNull(b[0][1].getCurAL());
        assertNull(b[0][2].getCurAL());

        //
    }

    //makes sure moveTo piece sets button properly, and button functions properly
    @Test
    public void testSetMoveToPieceAction() {
        ChessButton[][] b = gui.getChessBoardSquares();
        int startTurn = gui.getGame().getTurnFlag();
        gui.setMoveToPieceAction(b[0][3],"A2","A4");
        assertTrue(b[0][3].getMoveTo());
        assertNotNull(b[0][3].getCurAL());

        //clicking the button should cause the game to play the move
        b[0][3].doClick();
        //by seeing play swapped we know turn is played
        assertNotEquals(startTurn,gui.getGame().getTurnFlag());
    }

    @Test
    public void testFoolsMate() {
        gui.playTurn();
        ChessButton[][] b = gui.getChessBoardSquares();
        // G2 -> G4
        assertDoesNotThrow(() -> b[6][1].doClick());
        assertDoesNotThrow(() -> b[6][3].doClick());
        // E7 -> E6
        assertDoesNotThrow(() -> b[4][6].doClick());
        assertDoesNotThrow(() -> b[4][7].doClick());
        // F2 -> F3
        assertDoesNotThrow(() -> b[5][1].doClick());
        assertDoesNotThrow(() -> b[5][2].doClick());
        //D8 -> H3
        assertDoesNotThrow(() -> b[3][7].doClick());
        assertDoesNotThrow(() -> b[7][2].doClick());
    }

}
