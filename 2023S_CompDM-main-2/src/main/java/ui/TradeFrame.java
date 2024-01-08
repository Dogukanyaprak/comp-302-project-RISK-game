package src.main.java.ui;

import src.main.java.Controller.TradeController;
import src.main.java.domain.Game;
import src.main.java.domain.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TradeFrame extends JFrame {

    private static ArrayList<JLabel> panels = new ArrayList<JLabel>();
    private Player currentPlayer;
    private PauseButton pauseButton;


    public TradeFrame(GameFrame frame, Game game) {
        currentPlayer = game.getCurrentPlayer();


        setTitle("Trade Army Cards");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // TODO: Add the UI components for trading army cards

        // Create the three JPanels
        JPanel topPanel = new JPanel();
        JPanel middlePanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        JPanel inputPanel = new JPanel(new GridLayout(3, 1, 10, 10));


        JLabel infantryLabel = new JLabel("Infantry:");
        JTextField infantryField = new JTextField(10);

        // Create a label and text field for Cavalry input
        JLabel cavalryLabel = new JLabel("Cavalry:");
        JTextField cavalryField = new JTextField(10);

        // Create a label and text field for Artillery input
        JLabel artilleryLabel = new JLabel("Artillery:");
        JTextField artilleryField = new JTextField(10);

        inputPanel.add(infantryLabel);
        inputPanel.add(infantryField);
        inputPanel.add(cavalryLabel);
        inputPanel.add(cavalryField);
        inputPanel.add(artilleryLabel);
        inputPanel.add(artilleryField);


        // Add labels to the top panel
        JLabel labelInf = new JLabel("Infantry");
        JLabel NumberInf = new JLabel();
        NumberInf.setText("" + currentPlayer.getArmyDeck().getArmyCards().get("Infantry"));
        topPanel.add(labelInf, BorderLayout.NORTH);
        topPanel.add(NumberInf, BorderLayout.CENTER);

        // Add labels to the middle panel
        JLabel labelCav = new JLabel("Cavalry");
        JLabel NumberCav = new JLabel("Cavalry");
        NumberCav.setText("" + currentPlayer.getArmyDeck().getArmyCards().get("Cavalry"));
        middlePanel.add(labelCav, BorderLayout.NORTH);
        middlePanel.add(NumberCav, BorderLayout.CENTER);


        // Add labels to the bottom panel
        JLabel labelArt = new JLabel("Artillery");
        JLabel NumberArt = new JLabel();
        NumberArt.setText("" + currentPlayer.getArmyDeck().getArmyCards().get("Artillery"));
        bottomPanel.add(labelArt, BorderLayout.NORTH);
        bottomPanel.add(NumberArt, BorderLayout.CENTER);

        panels.add(NumberInf);
        panels.add(NumberCav);
        panels.add(NumberArt);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {

            String infantryText = infantryField.getText();
            String cavalryText = cavalryField.getText();
            String artilleryText = artilleryField.getText();


            try {
                int infantry = Integer.parseInt(infantryText);
                int cavalry = Integer.parseInt(cavalryText);
                int artillery = Integer.parseInt(artilleryText);

                //
                TradeController.tradeArmy(currentPlayer, infantry, cavalry, artillery);
                frame.updateTurnText(game.getCurrentPlayer());


            } catch (NumberFormatException ex) {
                // Handle invalid input
                JOptionPane.showMessageDialog(TradeFrame.this, "Please enter integer values!");
            }

        });


        // Create a container panel for the input panel and the submit button
        JPanel inputContainer = new JPanel(new BorderLayout());

        // Add the input panel to the center position of the container panel
        inputContainer.add(inputPanel, BorderLayout.CENTER);

        // Add the submit button to the south position of the container panel
        inputContainer.add(submitButton, BorderLayout.SOUTH);

        // Add the container panel to the frame's south position
        add(inputContainer, BorderLayout.SOUTH);


        // Add the three panels to the main frame
        add(topPanel, BorderLayout.WEST);
        add(middlePanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.EAST);
        pauseButton = new PauseButton();
        add(pauseButton, BorderLayout.NORTH);


    }


    public void showError(String error) {
        switch (error) {
            case "not int":
                // code to handle error type A
                JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);

                break;
            case "invalid combination":
                JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
                // code to handle error type B
                break;
            case "not enough card":
                JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
                // code to handle error type C
                break;
            default:
                // code to handle unrecognized error types
                JOptionPane.showMessageDialog(null, "not enough card", "Error", JOptionPane.ERROR_MESSAGE);
                break;

        }
    }


    public void updateFrame() {

        int c = 0;
        for (JLabel label : panels) {


            if (c == 0) {
                label.setText("" + currentPlayer.getArmyDeck().getArmyCards().get("Infantry"));
                c++;
            } else if (c == 1) {
                label.setText("" + currentPlayer.getArmyDeck().getArmyCards().get("Cavalry"));
                c++;
            } else if (c == 2) {
                label.setText("" + currentPlayer.getArmyDeck().getArmyCards().get("Artillery"));
                c++;
            }

        }
    }


}
