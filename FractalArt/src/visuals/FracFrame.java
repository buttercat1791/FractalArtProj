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

	private JPanel contentPane;

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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel buttonPanel = new JPanel();
		contentPane.add(buttonPanel, BorderLayout.NORTH);
		buttonPanel.setLayout(new GridLayout(1, 6, 0, 0));
		
		JButton btnDragon = new JButton("Dragon Curve");
		buttonPanel.add(btnDragon);
		btnDragon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DragonCurve dragon = new DragonCurve();
				contentPane.add(dragon, BorderLayout.CENTER);
			}
		});
		
		JButton btnSierpinski = new JButton("Sierpinski Triangle");
		buttonPanel.add(btnSierpinski);
	}

}
