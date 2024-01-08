package src.main.java.ui.Animations;

import java.awt.Dimension;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import src.main.java.Controller.AttackController;

public class DiceAnimation {
	Random random = new Random();
	public DiceAnimation() {
		
	}

	public void animate(JLabel dice1, int result1, JLabel text1, JLabel dice2, int result2, JLabel text2) {
		dice1.setVisible(true);
		text1.setVisible(true);
		
		dice2.setVisible(true);
		text2.setVisible(true);
		Thread roll = new Thread() {
			public void run() {
				try {
					Dimension sizeText1 = text1.getPreferredSize();
					int xText1= text1.getBounds().x;
					int yText1 = text1.getBounds().y;
			        text1.setBounds(xText1, yText1, sizeText1.width, sizeText1.height);
			        
			        Dimension sizeText2 = text2.getPreferredSize();
					int xText2= text2.getBounds().x;
					int yText2 = text2.getBounds().y;
			        text2.setBounds(xText2, yText2, sizeText2.width, sizeText2.height);
			        
					for(int i = 0; i<50; i++) {
						dice1.setIcon(new ImageIcon(getClass().getResource("dice" +Integer.toString(random.nextInt(6)+1)+".png")));
						Dimension size1 = dice1.getPreferredSize();
						int x1 = dice1.getBounds().x;
						int y1 = dice1.getBounds().y;
				        dice1.setBounds(x1, y1, size1.width, size1.height);
				        
				        dice2.setIcon(new ImageIcon(getClass().getResource("dice" +Integer.toString(random.nextInt(6)+1)+".png")));
						Dimension size2 = dice2.getPreferredSize();
						int x2 = dice2.getBounds().x;
						int y2 = dice2.getBounds().y;
				        dice2.setBounds(x2, y2, size2.width, size2.height);
				        
						Thread.sleep(20);
					}
					
					dice1.setIcon(new ImageIcon(getClass().getResource("dice" +Integer.toString(result1)+".png")));
					dice2.setIcon(new ImageIcon(getClass().getResource("dice" +Integer.toString(result2)+".png")));
					Thread.sleep(500);
					
					dice1.setVisible(false);
					text1.setVisible(false);
					
					dice2.setVisible(false);
					text2.setVisible(false);
					
					AttackController.attackUpdate();
				} catch(Exception e) {
					
				}
			
			}
		};
		roll.start();

	}
}
