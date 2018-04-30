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

public class FracFrame extends JFrame {

	private JPanel contentPane = new JPanel();
	private DragonCurve dragon = new DragonCurve();
	private Fractal mode;

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
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)screenSize.getWidth();
		int height = (int)screenSize.getHeight();
		setBounds((width/10), (height/10), (8 * (width/10)), (8 * (height/10)));
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
				case DRAGON:
					dragon.setIter(val);
					dragon.revalidate();
					dragon.repaint();
				
				}
				
				contentPane.revalidate();
				contentPane.repaint();
			}
		});
		
		/*
		 * Checklist for buttons:
		 * - Change mode
		 * - Run new fractal's setIter function
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
		
		JButton btnMandelbrot = new JButton("Mandelbrot Set");
		buttonPanel.add(btnMandelbrot);
		
		JButton btnJulia = new JButton("Julia Set");
		buttonPanel.add(btnJulia);
		
		JButton btnB5 = new JButton("B5");
		buttonPanel.add(btnB5);
		
		// On action, this button sets all fractals' visibility to false,
		// then revalidates and repaints
		JButton btnClear = new JButton("Clear Panel");
		buttonPanel.add(btnClear);
		btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dragon.setVisible(false);
				contentPane.revalidate();
				contentPane.repaint();
				
			}
		});
	}
}
