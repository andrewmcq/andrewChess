package JavaChess;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * has an current action listener attribute to make remove work easily
 */
public class ChessButton extends JButton {
    private ActionListener curAL;
    private boolean moveTo = false;

    public void setCurAL(ActionListener a) {
        curAL = a;
    }
    public ActionListener getCurAL() {
        return curAL;
    }

    public boolean getMoveTo() { return moveTo; }
    public void setMoveTo(boolean moveTo) { this.moveTo = moveTo; }
}
