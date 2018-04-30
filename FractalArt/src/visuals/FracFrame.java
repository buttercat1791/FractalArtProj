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
		JSlider iterSlider = new JSlider(0, 15, 1);
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
				
				dragon.setIter(val);
				dragon.revalidate();
				dragon.repaint();
				
				contentPane.revalidate();
				contentPane.repaint();
			}
		});
		
		// Dragon Curve button
		JButton btnDragon = new JButton("Dragon Curve");
		buttonPanel.add(btnDragon);
		btnDragon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
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
		
		JButton btnClear = new JButton("Clear Panel");
		buttonPanel.add(btnClear);
		btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//int a = contentPane.getComponentCount();
				//System.out.println(a);
				clearPane(contentPane);
				contentPane.revalidate();
				contentPane.repaint();
				
			}
		});
	}
	
	//Recursive function for removing drawn components from JPanel
	//Used in Clear button
	public void clearPane(JPanel jp)
	{
		int numComps = jp.getComponentCount();
		if(numComps == 2)
		{
			return;
		} else
		{
			jp.remove(2);
			clearPane(jp);
		}

	}
}
