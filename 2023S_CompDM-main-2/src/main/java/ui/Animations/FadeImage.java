package src.main.java.ui.Animations;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class FadeImage extends JLabel{
	 private BufferedImage Image;
	 private float alpha = 1.0f;
	 int x = 0;
	 int y = 0;
	    
	 public void setAlpha(float alpha) {
         this.alpha = alpha;
         repaint();
     }
     
     public float getAlpha() {
         return this.alpha;
     }
     
     @Override
     protected void paintComponent(Graphics g) {
         Graphics2D g2 = (Graphics2D) g.create();
         
         Point p = new Point();
         p.x = x;
         p.y = y;
         
         SwingUtilities.convertPointFromScreen(p, this);
         
         g2.setComposite(AlphaComposite.SrcOver.derive(alpha));
         g2.drawImage(Image, p.x, p.y, null);
         g2.dispose();
     }
     
     public void move(int toX, int toY) {
 		x = toX;
 		y = toY;
 		repaint();
 	}

	public void setImage(BufferedImage image) {
		this.Image = image;
	}
	public void resetAlpha() {
		setAlpha(1.0f);
	}
     
}
