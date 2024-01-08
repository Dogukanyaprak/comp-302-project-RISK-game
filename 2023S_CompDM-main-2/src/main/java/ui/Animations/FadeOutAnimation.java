package src.main.java.ui.Animations;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import src.main.java.Controller.AttackController;

public class FadeOutAnimation{
	private BufferedImage Image ;
	public void animate(FadeImage image, int x0, int y0, int val) {
		
		image.setVisible(true);
		image.move(x0, y0);
		image.setBounds(0, 0, 10000, 10000);
		
		Thread fade = new Thread() {
			public void run() {
				try {
					Image = ImageIO.read(new File("./src/main/java/ui/Animations/fade"+Integer.toString(val)+".png"));
					image.setImage(Image);
					image.resetAlpha();
					Thread.sleep(300);
					for(int i = 0; i<9; i++) {
						image.setAlpha(1.0f - i*(1.0f /10));
						Thread.sleep(100);
					}
					image.setVisible(false);
					Thread.sleep(200);
					AttackController.attackFinish();
				} catch(Exception e) {
					e.printStackTrace();
				}

			}
		};
		fade.start();
		
	}
}
