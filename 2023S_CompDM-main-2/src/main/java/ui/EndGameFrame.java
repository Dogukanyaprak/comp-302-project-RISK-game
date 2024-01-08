package src.main.java.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class EndGameFrame extends JFrame {

    public EndGameFrame(String winnerName) {
        setTitle("End Game Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JLabel winnerLabel = new JLabel("Winner: " + winnerName);
        winnerLabel.setHorizontalAlignment(JLabel.CENTER);
        add(winnerLabel);
    }

}