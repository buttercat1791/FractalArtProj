package fractals;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * @author Michael Jurkoic
 * Generates an image of the Sierpinski Triangle
 */
@SuppressWarnings("serial")
public class SierpinskiTriangle extends JPanel {
	
	int iter;
	int width;
	int height;
	
	BufferedImage img;
	
	public SierpinskiTriangle() {}
	
	public void setIter(int newIter) {
		iter = newIter;
	}
	
	public void paintComponent(Graphics g) {
		img = buildSierpinski(BufferedImage.TYPE_INT_ARGB, iter);
		g.drawImage(img, 0, 0, this);
	}
	
	private BufferedImage buildSierpinski(int type, int iterations) {
		width = getWidth();
		height = getHeight();
		
		BufferedImage image = new BufferedImage(width, height, type);
		
		Graphics2D g2 = image.createGraphics();
		g2.setColor(Color.BLACK);
		
		// Recursive definition for Sierpinski Triangle drawing
		// Base case draws a triangle with points at bottom-left corner, bottom-right corner,
		// and top center.
		// Recursion gets image from previous iteration, scales it to half size, and draws three
		// of them, one in top center, one in bottom left, one in bottom right.
		if (iterations == 0) {
			g2.fillPolygon(new int[] {0, width / 2, width}, new int[] {height, 0, height}, 3);
		} else if (iterations > 0) {
			BufferedImage smallimg = buildSierpinski(type, iterations - 1);
			smallimg = halfScale(smallimg, width / 2, height / 2);
			g2.drawImage(smallimg, 0, height / 2, this);
			g2.drawImage(smallimg, width / 2, height / 2, this);
			g2.drawImage(smallimg, width / 4, 0, this);
		}
		
		return image;
	}
	
	// Rescales image
	// Uses code from https://stackoverflow.com/questions/9417356/bufferedimage-resize
	private BufferedImage halfScale(BufferedImage bi, int newWidth, int newHeight) {
		BufferedImage dimg = new BufferedImage(newWidth, newHeight, bi.getType());  
	    Graphics2D g = dimg.createGraphics();  
	    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
	    RenderingHints.VALUE_INTERPOLATION_BILINEAR);  
	    g.drawImage(bi, 0, 0, newWidth, newHeight, 0, 0, getWidth(), getHeight(), null);  
	    g.dispose();  
	    return dimg; 
	}
}
