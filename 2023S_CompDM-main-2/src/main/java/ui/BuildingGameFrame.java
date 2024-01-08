package src.main.java.ui;

import src.main.java.Controller.BuildingFrameController;
import src.main.java.Controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BuildingGameFrame extends JFrame {

    private final JTextField numPlayersField;
    private final JPanel panel;

    public BuildingGameFrame() {

        setTitle("Building Game");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add a panel to hold the components
        panel = new JPanel();
        panel.setLayout(new GridLayout(10, 1));
        add(panel);

        // Add a label to prompt for the number of players
        JLabel numberOfPlayersLabel = new JLabel("Enter the number of players:");
        panel.add(numberOfPlayersLabel);

        // Add a text field to enter the number of players
        numPlayersField = new JTextField();
        panel.add(numPlayersField);

        // Add a button to submit the number of players
        JButton submitNumbers = new JButton("Submit Number of Players");
        submitNumbers.addActionListener(new SubmitNumbersButtonListener());
        panel.add(submitNumbers);

        PauseButton pauseButton = new PauseButton();
        add(pauseButton, BorderLayout.NORTH);
        
        JButton loadGameButton = new JButton("Load existing game");
        loadGameButton.addActionListener(e -> {
            GameController.loadPrevGame("TextFile");
            dispose();

        });
        
        add(loadGameButton,BorderLayout.NORTH);

        setVisible(true);
    }

    public void updatePlayersField(int numberOfPlayers) {

        JButton startGame = new JButton("Start Game");

        panel.removeAll();

        for (int i = 0; i < numberOfPlayers; i++) {

            JLabel label = new JLabel("Enter name of the player ");
            panel.add(label);

            JTextField nameField = new JTextField();
            panel.add(nameField);
        }

        startGame.addActionListener(new StartGameButtonListener());
        panel.add(startGame);

        panel.revalidate();
        panel.repaint();

    }

    private class SubmitNumbersButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            int numPlayers = Integer.parseInt(numPlayersField.getText());
            if (numPlayers < 2 || numPlayers > 6) {
                throw new IllegalArgumentException();
            }
            BuildingFrameController.updateNumberOfPlayers(numPlayers);

            System.out.println("Starting new game with " + numPlayers + " players!");
        }
    }

    private class StartGameButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Get the names of the players from the text fields
            ArrayList<String> playerNames = new ArrayList<String>();
            for (Component comp : panel.getComponents()) {

                if (comp instanceof JTextField) {
                    JTextField textField = (JTextField) comp;
                    String playerName = textField.getText().trim();
                    if (!playerName.isEmpty()) {
                        playerNames.add(playerName);
                    }
                }
            }

            // Update the game with the player names
            BuildingFrameController.updatePlayerNames(playerNames);

            // Do something with the player names, such as starting the game
            System.out.println("Starting game with players: " + playerNames);

            ((JFrame) ((JButton) e.getSource()).getRootPane().getParent()).dispose();

            GameController.startGame();


        }
    }
    
    
    
    


}
