package JavaChess;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * has a move to attribute to allow the action listener to have an extra parameter
 */
public class ChessButton extends JButton {
    private ActionListener curAL;

    public void setCurAL(ActionListener a) {
        curAL = a;
    }
    public ActionListener getCurAL() {
        return curAL;
    }
}
