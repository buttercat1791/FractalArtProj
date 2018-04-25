package visuals;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fractals.*;

import java.awt.Canvas;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JSlider;

public class FracFrame extends JFrame {

	private JPanel contentPane = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
	//	contentPane.add(new DragonCurve(), BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		contentPane.add(buttonPanel, BorderLayout.NORTH);
		buttonPanel.setLayout(new GridLayout(2, 3, 0, 0));
		
		JSlider slider = new JSlider();
		contentPane.add(slider, BorderLayout.SOUTH);
		
		JButton btnDragon = new JButton("Dragon Curve");
		buttonPanel.add(btnDragon);
		btnDragon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DragonCurve dragon = new DragonCurve(1);
				contentPane.add(dragon, BorderLayout.CENTER);
				contentPane.revalidate();
				contentPane.repaint();
			}
		});
		
		JButton btnSierpinski = new JButton("Sierpinski Triangle");
		buttonPanel.add(btnSierpinski);
		
		JButton btnMandelbrot = new JButton("Mandelbrot Set");
		buttonPanel.add(btnMandelbrot);
		btnMandelbrot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MandelbrotSet mandel = new MandelbrotSet();
				contentPane.add(mandel, BorderLayout.CENTER);
				contentPane.revalidate();
				contentPane.repaint();
			}
		});
		
		JButton btnJulia = new JButton("Julia Set");
		buttonPanel.add(btnJulia);
		btnJulia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JuliaSet julia = new JuliaSet();
				contentPane.add(julia, BorderLayout.CENTER);
				contentPane.revalidate();
				contentPane.repaint();
			}
		});
		
		JButton btnB5 = new JButton("B5");
		buttonPanel.add(btnB5);
		
		JButton btnClear = new JButton("Clear Panel");
		buttonPanel.add(btnClear);
		btnClear.addActionListener(new ActionListener() {
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
		if(numComps == 1)
		{
			return;
		} else
		{
			jp.remove(1);
			clearPane(jp);
		}

	}
}
