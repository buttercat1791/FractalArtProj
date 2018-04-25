package fractals;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class DragonCurve extends JPanel {
	
	public DragonCurve(int iterations) {
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		
		int width = this.getWidth();
		int height = this.getHeight();
		
		g2.setPaint(Color.BLACK);
		g2.drawLine(50, height / 2, width - 50, height / 2);
	}
}
