package View;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import Control.Control;

public class GUI {
	/**
	 * 
	 *
	 */
	private JFrame frame = new JFrame();
	private static JPanel left = new JPanel();
	private JPanel bottom = new JPanel();
	private Box theBox = Box.createHorizontalBox();
	private JMenuBar menuBar;
	private JMenu menu;
	public static JMenuItem save;
	public static JMenuItem load;
	public static JMenuItem neww;
	public static JButton drawButton;

	public GUI() {
		initialize();
	}

	/* initialize the menu bar contents */
	public void initializeMenuBar() {
		menuBar = new JMenuBar();
		menu = new JMenu("File");
		save = new JMenuItem("Save");
		load = new JMenuItem("Load");
		neww = new JMenuItem("New");
		menu.add(save);
		menu.add(load);
		menu.add(neww);
		menuBar.add(menu);
	}

	/* initialize our frame contents */
	public void initialize() {

		frame.setTitle("Signal Flow Graph");
		initializeMenuBar();
		frame.setJMenuBar(menuBar);
		frame.setLayout(new BorderLayout());
		frame.setSize(1100, 600);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		drawButton = new JButton("Draw");
		Font font = new Font(null, 20, 20);
		drawButton.setFont(font);
		drawButton.setSize(100, 100);
		theBox.add(drawButton);
		bottom.add(theBox);
		Control t = new Control();
		t.doActions();
		t.setBackground(Color.WHITE);
		t.setBounds(50, 10, 1000, 600);
		left.setLayout(null);
		left.add(t);
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
