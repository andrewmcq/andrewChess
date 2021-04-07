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
}
