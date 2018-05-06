package fractals;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Hashtable;

import javax.swing.JPanel;

public class BurningShip extends JPanel {
	
	private int width, height;
	private int iter;
	
	BufferedImage img;
	
	Hashtable<Integer, BufferedImage> imgTable = new Hashtable<Integer, BufferedImage>();

	public BurningShip() {}
	
	public void buildImgTable() {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i <= 15; i++)
					imgTable.put(i, burnShip(BufferedImage.TYPE_INT_ARGB));
			}
		};
	}
	
	public void setIter(int newIter) {
		iter = newIter + 3;
	}
	
	// zn+1 = (|Re(zn) | + i|Im(zn)|)^2 + c, z0 = 0
	private BufferedImage burnShip(int type) {
		width = getWidth();
		height = getHeight();
		
		BufferedImage image = new BufferedImage(width, height, type);
		
		int maxIter = iter * 2;
		int black = 0;
		int[] colors = new int[maxIter];
		for (int i = 0; i < maxIter; i++) {
			colors[i] = Color.HSBtoRGB(i/256f, 1, i/(1 + 8f));
			
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					double cRe = (x - 2 * (width / 3.25)) * (5.0 / width);
					double cIm = (y - width / 2.5) * (5.0 / width);
					
					ComplexNumbers z = new ComplexNumbers(0, 0);
					
					int iteration = 0;
					
					// Implement iteration:
					// zn+1 = (|Re(zn)| + i|Im(zn)|)^2 + c
					while (z.norm() < 4 && iteration < maxIter) {
						z = new ComplexNumbers(z.x * z.x - z.y * z.y,
											   Math.abs(z.x * z.y) + Math.abs(z.x * z.y));
						z = new ComplexNumbers(z.x + cRe, z.y + cIm);
						iteration++;
					}
					
					if (iteration < maxIter)
						image.setRGB(x, y, colors[iteration]);
					else
						image.setRGB(x, y, black);
				}
			}
		}
		
		return image;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		//img = imgTable.get(iter);
		img = burnShip(BufferedImage.TYPE_INT_ARGB);
		g.drawImage(img, 0, 0, this);
	}
}
