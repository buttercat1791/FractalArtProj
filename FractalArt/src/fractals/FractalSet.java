package fractals;

import java.awt.Graphics;

import javax.swing.JPanel;

/** FractalSets class superclasses all fractal set classes
 * 
 * Contains:
 * - Variable and setter for number of iterations
 * - Override for paintComponent
 * - Default constructor
 * 
 * IN PROGRESS
 *
 */
public class FractalSet extends JPanel {
	
	private int iter;	// Number of iterations

	public FractalSets() {
		
	}
	
	public void setIter(int newIter) {
		this.iter = newIter;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
	}
}
