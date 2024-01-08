package src.main.java.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import src.main.java.Controller.TradeController;
import src.main.java.domain.Game;
import src.main.java.domain.Player;
import src.main.java.domain.Board.Continent;
import src.main.java.domain.Board.Map;

public class TradeTerritoryCardFrame extends JFrame{
	
	private Player currentPlayer;
	
	public  TradeTerritoryCardFrame(GameFrame frame, Game game) {
		currentPlayer = game.getCurrentPlayer();

        setTitle("Trade Territory Cards");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(10, 1));
        
        JLabel topLabel = new JLabel();
        topLabel.setText(currentPlayer.getTerritoryDeck().toString());
        
        //JLabel continentLabel = new JLabel("Continent:");
        JTextField continentField = new JTextField(20);
        
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
        	
        	//Bu ismi kullanarak çağırabilirsin fonksiyonlarını
        	String continentName = continentField.getText();
        	for(Continent c: Map.getMap().getAllContinents()) {
        		if(continentName.equals(c.getName())) {
        			TradeController.tradeTerritory(currentPlayer, c);
        		}
        	}
        	
        	
        });
        
        add(topLabel,BorderLayout.NORTH);
        //add(continentLabel,BorderLayout.CENTER);
        add(continentField,BorderLayout.CENTER);
        add(submitButton,BorderLayout.SOUTH);
		
		
	}
	
}
