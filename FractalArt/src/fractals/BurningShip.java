package fractals;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class BurningShip extends JPanel {
	
	private int width, height;
	private int iter;
	
	BufferedImage img;

	public BurningShip() {}
	
	public void setIter(int newIter) {
		iter = newIter;
	}
	
	// zn+1 = (|Re(zn) | + i|Im(zn)|)^2 + c, z0 = 0
	private BufferedImage burnShip(int type) {
		width = getWidth();
		height = getHeight();
		
		BufferedImage image = new BufferedImage(width, height, type);
		
		ComplexNumbers z = new ComplexNumbers(0, 0);
		
		int i = 0;	// Number of iterations
		int maxIter = iter * 1000;
		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				while (z.norm() <= 2 && i < maxIter) {
					double cx = -2.5 + x * (3.5 / width);
					double cy = -1 + y * (2 / height);
					z = new ComplexNumbers(z.x * z.x - z.y * z.y - cx,
										   2 * Math.abs(z.x * z.y) - cy);
					i++;
				}
				if (z.norm() > 2)
					image.setRGB(x, y, Color.BLACK.getRGB());
			}
		}
		
		return image;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		img = burnShip(BufferedImage.TYPE_INT_ARGB);
		g.drawImage(img, 0, 0, this);
	}
}
