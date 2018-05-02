package fractals;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import javafx.scene.paint.Color;

import java.util.ArrayList;

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
		img = buildSierpinski(BufferedImage.TYPE_INT_ARGB);
		g.drawImage(img, 0, 0, this);
	}
	
	private BufferedImage buildSierpinski(int type) {
		width = getWidth();
		height = getHeight();
		
		BufferedImage image = new BufferedImage(width, height, type);
		
		Graphics2D g2 = image.createGraphics();
		
		if (iter == 0) {
			g2.drawPolygon(new int[] {0, width / 2, width}, new int[] {height, 0, height}, 3);
		} else {
			g2.drawPolygon(new int[] {0, width / 2, width}, new int[] {height, 0, height}, 3);
		}
		
		return image;
	}
}
