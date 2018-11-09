package visuals;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import fractals.*;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.WindowConstants;


/*
 * Discrete Structures final project made by Michael Jurkoic and William Kostuch
 * 
 * The later iteration fractals for the Mandelbrot set, Julia set, and burning ship
 * will chug initially (especially if you're using a 4k screen).  
 * Please give them a little time to display before moving on to the next one, as 
 * once you've loaded them once they'll be significantly faster next time
 * because they'll be living in a hashtable.
 * 
 */




/**
 * @author Michael Jurkoic
 * @author William Kostuch
 */
@SuppressWarnings("serial")
public class FracFrame extends JFrame {

	private JPanel contentPane = new JPanel();
	
	//Set objects up for fractals
	private DragonCurve dragon = new DragonCurve();
	private SierpinskiTriangle sierpinski = new SierpinskiTriangle();
	private JuliaSet julia = new JuliaSet();
	private BurningShip ship = new BurningShip();
	private MandelbrotSet mandelbrot = new MandelbrotSet();
	

	
	private Fractal mode = Fractal.CLEAR;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					FracFrame frame = new FracFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FracFrame() {

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)screenSize.getWidth();
		int height = (int)screenSize.getHeight();
		int windowWidth = (8 * (width/10));
		int windowHeight = (8 * (height/10));
		setBounds((width/10), (height/10), windowWidth, windowHeight);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		
		JPanel buttonPanel = new JPanel();
		contentPane.add(buttonPanel, BorderLayout.NORTH);
		buttonPanel.setLayout(new GridLayout(2, 3, 0, 0));
		
		
		// Slider sets number of iterations to display for fractal
		JSlider iterSlider = new JSlider(0, 15, 0);
		contentPane.add(iterSlider, BorderLayout.SOUTH);
		iterSlider.setMajorTickSpacing(5);
		iterSlider.setMinorTickSpacing(1);
		iterSlider.setPaintTicks(true);
		iterSlider.setSnapToTicks(true);
		iterSlider.setPaintLabels(true);
		iterSlider.addChangeListener(new ChangeListener() {
			// Change in slider changes iteration values for all fractals
			public void stateChanged(ChangeEvent e) {
				int val = iterSlider.getValue();
				// Changes operation depending on which 
				// fractal is currently being displayed
				switch (mode) {
				case CLEAR:
					break;
				case DRAGON:
					dragon.setIter(val);
					break;
				case SIERPINSKI:
					sierpinski.setIter(val);
					break;
				case JULIA:
					julia.setIter(val);
					break;
				case SHIP:
					ship.setIter(val);
					break;
				case MANDELBROT:
					mandelbrot.setIter(val);
					break;
				}
				contentPane.repaint();
			}
		});


		/*
		 * Checklist for buttons:
		 * - Run clear()
		 * - Change mode
		 * - Run new fractal's setIter function using value from slider
		 * - Add button's fractal to CENTER of BorderLayout
		 * - Set new fractal's visibility to true
		 * - Revalidate and repaint
		 * 
		 * DO NOT
		 * - Instantiate new fractals
		 * - Clear contentPane
		 */
		
		// Dragon Curve button
		// Use as model for other buttons
		JButton btnDragon = new JButton("Dragon Curve");
		buttonPanel.add(btnDragon);
		btnDragon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clear();
				iterSlider.setValue(0);
				mode = Fractal.DRAGON;
				dragon.setIter(iterSlider.getValue());
				contentPane.add(dragon, BorderLayout.CENTER);
				dragon.setVisible(true);
				contentPane.revalidate();
				contentPane.repaint();
			}
		});
		
		JButton btnSierpinski = new JButton("Sierpinski Triangle");
		buttonPanel.add(btnSierpinski);
		btnSierpinski.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clear();
				iterSlider.setValue(0);
				mode = Fractal.SIERPINSKI;
				sierpinski.setIter(iterSlider.getValue());
				contentPane.add(sierpinski, BorderLayout.CENTER);
				sierpinski.setVisible(true);
			contentPane.revalidate();
				contentPane.repaint();
			}
		});
		
		JButton btnMandelbrot = new JButton("Mandelbrot Set");
		buttonPanel.add(btnMandelbrot);
		btnMandelbrot.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clear();
				iterSlider.setValue(0);
				mode = Fractal.MANDELBROT;
				mandelbrot.setIter(iterSlider.getValue());
				contentPane.add(mandelbrot, BorderLayout.CENTER);
				mandelbrot.setVisible(true);
				contentPane.revalidate();
				contentPane.repaint();
			}
		});
		
		JButton btnJulia = new JButton("Julia Set");
		buttonPanel.add(btnJulia);
		btnJulia.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clear();
				iterSlider.setValue(0);
				mode = Fractal.JULIA;
				julia.setIter(iterSlider.getValue());
				contentPane.add(julia, BorderLayout.CENTER);
				julia.setVisible(true);
				contentPane.revalidate();
				contentPane.repaint();
			}
		});
		
		JButton btnShip = new JButton("Burning Ship");
		buttonPanel.add(btnShip);
		btnShip.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clear();
				iterSlider.setValue(0);
				mode = Fractal.SHIP;
				ship.setIter(iterSlider.getValue());
				contentPane.add(ship, BorderLayout.CENTER);
				ship.setVisible(true);
				contentPane.revalidate();
				contentPane.repaint();
			}
		});
		
		// On action, this button sets all fractals' visibility to false,
		// then revalidates and repaints
		JButton btnClear = new JButton("Clear Panel");
		buttonPanel.add(btnClear);
		btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mode = Fractal.CLEAR;
				clear();
				iterSlider.setValue(0);
				contentPane.revalidate();
				contentPane.repaint();	
			}
		});
	}
	
	//Called when buttons are hit
	public void clear() {
		dragon.setVisible(false);
		sierpinski.setVisible(false);
		julia.setVisible(false);
		mandelbrot.setVisible(false);
		ship.setVisible(false);
	}
}
	
