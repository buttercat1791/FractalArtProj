package fractals;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import javafx.scene.paint.Color;

public class DragonCurve extends JPanel {
	
	public DragonCurve() {
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D G = (Graphics2D)g;
		int width = getWidth();
		int height = getHeight();
		G.setColor(Color.WHITESMOKE);
		G.drawLine(50, height / 2, width - 50, height / 2);
	}
}
