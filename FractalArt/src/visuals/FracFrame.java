package visuals;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fractals.DragonCurve;

import java.awt.Canvas;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

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
		
		JButton btnDragon = new JButton("Dragon Curve");
		buttonPanel.add(btnDragon);
		btnDragon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DragonCurve dragon = new DragonCurve();
				contentPane.add(dragon, BorderLayout.CENTER);
				
				//Revalidates container and repaints the panel
				contentPane.revalidate();
				contentPane.repaint();
			}
		});
		

		JButton btnSierpinski = new JButton("Sierpinski Triangle");
		buttonPanel.add(btnSierpinski);
		
		JButton btnB3 = new JButton("B3");
		buttonPanel.add(btnB3);
		
		JButton btnB4 = new JButton("B4");
		buttonPanel.add(btnB4);
		
		JButton btnB5 = new JButton("B5");
		buttonPanel.add(btnB5);
		
		JButton btnClear = new JButton("Clear Panel");
		buttonPanel.add(btnClear);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = contentPane.getComponentCount();
				System.out.println(a);
				if(a > 1) {
					//for(int i = 1; i < a; i++) 
					contentPane.remove(1);
					contentPane.revalidate();
					contentPane.repaint();
				}
			}
		});
		

	}

}
