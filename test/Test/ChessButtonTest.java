package Test;

import JavaChess.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ChessButtonTest {
    private ChessButton button;

    @BeforeEach
    private void setUp() {
        button = new ChessButton();
    }

    @Test
    public void testCurrAL() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button.setBackground(Color.green);
            }
        };
        assertNull(button.getCurAL());
        button.setCurAL(al);
        assertEquals(al,button.getCurAL());

    }
}
