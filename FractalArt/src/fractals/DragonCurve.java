package fractals;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import javafx.scene.paint.Color;

public class DragonCurve extends JPanel {
	
	public DragonCurve() {
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		int width = getWidth();
		int height = getHeight();
		g.setColor(Color.WHITESMOKE);
		g.drawLine(50, height / 2, width - 50, height / 2);
	}
}
