package fractals;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JPanel;

/**
 * @author Michael Jurkoic
 * @author William Kostuch
 * Generates an image of the Dragon Curve
 * Class borrows code from http://rosettacode.org/wiki/Dragon_curve#Java
 */
@SuppressWarnings("serial")
public class DragonCurve extends JPanel {
	
	private List<Integer> turns;
    private double startingAngle, side;
    private int iter;
 
    public DragonCurve() {
    	turns = getSequence(iter);
        startingAngle = -iter * (Math.PI / 4);
    }
 
    public List<Integer> getSequence(int iterations) {
        List<Integer> turnSequence = new ArrayList<Integer>();
        for (int i = 0; i < iterations; i++) {
            List<Integer> copy = new ArrayList<Integer>(turnSequence);
            Collections.reverse(copy);
            turnSequence.add(1);
            for (Integer turn : copy) {
                turnSequence.add(-turn);
            }
        }
        return turnSequence;
    }
    
    // Reruns constructor function code to refresh the image
    public void setIter(int newIter) {
    	iter = newIter;
    	turns = getSequence(iter);
        startingAngle = -iter * (Math.PI / 4);
    }
 
    @Override
    public void paint(Graphics g) {
    	int width = this.getWidth();
    	int height = this.getHeight();
    	if (width > height) {
    		side = (height/2) / (Math.pow(1.3411, iter*1.));
    	} else if (width < height) {
    		side =  (width/2) / (Math.pow(1.3411, iter*1.));
    	}
        g.setColor(Color.GREEN);
        double angle = startingAngle;
        int x1 = width/3, y1 = (int)(height/1.5);
        int x2 = x1 + (int) (Math.cos(angle) * side);
        int y2 = y1 + (int) (Math.sin(angle) * side);
        g.drawLine(x1, y1, x2, y2);
        x1 = x2;
        y1 = y2;
        for (Integer turn : turns) {
        	
        	//Random color
        	int R = (int) (Math.random( )*256);
        	int G = (int)(Math.random( )*256);
        	int B= (int)(Math.random( )*256);
        	Color randomColor = new Color(R, G, B);
        	g.setColor(randomColor);
        	
            angle += turn * (Math.PI / 2);
            x2 = x1 + (int) (Math.cos(angle) * side);
            y2 = y1 + (int) (Math.sin(angle) * side);
            g.drawLine(x1, y1, x2, y2);
            x1 = x2;
            y1 = y2;
        }
    }
}
