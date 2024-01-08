package src.main.java.ui;

import javax.swing.*;

public class HelpButton extends JButton {
    public HelpButton(String text) {
        super(text);
        addActionListener(e -> {
            help();
        });
    }

    private void help() {
        JFrame helpFrame = new JFrame();
        helpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        helpFrame.setSize(300, 400);
        helpFrame.setTitle("Help Screen");

        // Set the size and close operation of the frame
        JLabel label = new JLabel("<html>This is the Help Screen<br/><h3>Instructions for the game</h3><br/>Under construction</html>");
        label.setVerticalAlignment(JLabel.TOP);
        helpFrame.add(label);
        helpFrame.setLocation(300, 100);
        helpFrame.setVisible(true);
    }


}


