package src.main.java.ui.Animations;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import javax.swing.JLabel;

public class Arrow extends JLabel{
	double theta = 0;
    Path2D arrow = new ArrowShape();
    double x = 0;
    double y = 0;

	public Arrow() {
		super();

		repaint();

	}

	public void rotate(double angle) {
		theta += angle;
		repaint();
	}

	public void move(double toX, double toY) {
		x = toX;
		y = toY;
		repaint();
	}

	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
/*
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
*/
        g2d.setStroke(new BasicStroke(4));
/*
        double x = (getWidth() - arrow.getBounds().getWidth()) / 2d;
        double y = (getHeight() - arrow.getBounds().getHeight()) / 2d;
        
 */     
        AffineTransform at = new AffineTransform();

        at.translate(x, y);
        at.rotate(theta, arrow.getBounds().getWidth() / 2d, arrow.getBounds().getHeight() / 2d);
        g2d.setTransform(at);

        g2d.draw(arrow);
        g2d.dispose();
    }

	public class ArrowShape extends Path2D.Double{
		public ArrowShape() {
	        moveTo(0, 10);
	        lineTo(36, 10);
	        moveTo(36 - 16, 0);
	        lineTo(36, 10);
	        moveTo(36 - 16, 20);
	        lineTo(36, 10);
	    }


	}

}