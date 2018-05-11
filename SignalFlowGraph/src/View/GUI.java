package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Control.Control;

/** @author EshraqIbrahim */
public class GUI {
	/** Frame used */
	private JFrame frame = new JFrame();
	/** Panel that contains the drawing area */
	private static JPanel left = new JPanel();
	/** Panel that contains the buttons */
	private JPanel bottom = new JPanel();
	/** Box contains all the buttons */
	private Box theButtonsBox = Box.createHorizontalBox();
	/** Button to add nodes */
	public static JButton nodeButton;
	/** Button to add edges */
	public static JButton edgeButton;
	/**
	 * Button to calculate loops, forward paths, Non touching loops , Mason Formula
	 */
	public static JButton calculateButton;

	public GUI() {
		initialize();
	}

	/* initialize our frame contents */
	public void initialize() {

		frame.setTitle("Signal Flow Graph");
		frame.setLayout(new BorderLayout());
		frame.setSize(1100, 600);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		nodeButton = new JButton("Nodes");
		edgeButton = new JButton("Add Edge");
		calculateButton = new JButton("Calculate");
		Font font = new Font(null, 20, 20);
		nodeButton.setFont(font);
		nodeButton.setSize(100, 100);
		calculateButton.setFont(font);
		calculateButton.setSize(100, 100);
		edgeButton.setFont(font);
		edgeButton.setSize(100, 100);
		theButtonsBox.add(nodeButton);
		theButtonsBox.add(edgeButton);
		theButtonsBox.add(calculateButton);
		bottom.add(theButtonsBox);
		Control control = new Control();
		control.doActions();
		control.setBackground(Color.WHITE);
		control.setBounds(50, 10, 1000, 600);
		left.setLayout(null);
		left.add(control);
		frame.add(left, BorderLayout.CENTER);
		frame.add(bottom, BorderLayout.SOUTH);
	}

	/* main function */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
