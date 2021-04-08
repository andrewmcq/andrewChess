package JavaChess;

import javax.swing.*;

public class Main {
    public static void main (String[] args) {
        Runnable r = () -> {
            ChessGUI gui = new ChessGUI();
            JFrame window = new JFrame("andrewChess");
            gui.playTurn();
            window.add(gui.getBoard());
            window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            window.setLocationByPlatform(true);
            window.pack();
            window.setMinimumSize(window.getSize());
            window.setMaximumSize(window.getSize());
            window.setVisible(true);
        };
        SwingUtilities.invokeLater(r);
    }
}
