package JavaChess;

import javax.swing.*;

public class Main {
    public static void main (String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                ChessGUI gui = new ChessGUI();
                JFrame f = new JFrame("andrewChess");
                gui.playTurn();
                f.add(gui.getBoard());
                // Ensures JVM closes after frame(s) closed and
                // all non-daemon threads are finished
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setLocationByPlatform(true);

                // ensures the frame is the minimum size it needs to be
                // in order display the components within it
                f.pack();
                // ensures the minimum size is enforced.
                f.setMinimumSize(f.getSize());
                f.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(r);
    }
}
