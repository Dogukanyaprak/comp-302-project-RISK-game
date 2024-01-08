package src.main.java.ui.Animations;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ArrowAnimation {
	private boolean start = true;
	private int pos = 0;
	public void animate( Arrow arrow, int x0, int y0, int x1, int y1) {
		
		double xTravel = x1-x0;
		double yTravel = y1-y0;
		double rotationAngle = Math.atan2(yTravel,xTravel);

		arrow.setVisible(true);
		arrow.rotate(rotationAngle);
		arrow.setBounds(0, 0, 10000, 10000);
		
		Thread travel = new Thread() {
			public void run() {
				try {
					while (start) {
						pos = pos%10;
						arrow.move(x0 + (xTravel)* (pos/10.0), y0 + (yTravel)*(pos/10.0));
						pos +=1;
						Thread.sleep(100);
					}
					arrow.setVisible(false);

				} catch(Exception e) {

				}

			}
		};
		travel.start();

	}
	public void stop() {
		start = false;
		
	}


}