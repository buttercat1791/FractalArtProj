package visuals;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fractals.*;

import java.awt.Canvas;
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
	//	contentPane.add(new DragonCurve(), BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		contentPane.add(buttonPanel, BorderLayout.NORTH);
		buttonPanel.setLayout(new GridLayout(2, 3, 0, 0));
		
		JSlider slider = new JSlider(1, 15, 1);
		contentPane.add(slider, BorderLayout.SOUTH);
		
		JButton btnDragon = new JButton("Dragon Curve");
		buttonPanel.add(btnDragon);
		btnDragon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearPane(contentPane);
				DragonCurve dragon = new DragonCurve();
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
			@Override
			public void actionPerformed(ActionEvent e) {
				clearPane(contentPane);
				MandelbrotSet mandel = new MandelbrotSet();
				contentPane.add(mandel, BorderLayout.CENTER);
				contentPane.revalidate();
				contentPane.repaint();
			}
		});
		
		JButton btnJulia = new JButton("Julia Set");
		buttonPanel.add(btnJulia);
		btnJulia.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearPane(contentPane);
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
