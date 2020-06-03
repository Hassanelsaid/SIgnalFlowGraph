package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.*;
import Control.Control;

public class FlowGraph {

	private JFrame frame = new JFrame();
	private static JPanel left = new JPanel();
	private JPanel upbuttons = new JPanel();
	private JPanel up2buttons = new JPanel();
	private JPanel downbuttons = new JPanel();
	private JPanel drawing = new JPanel();
	private JLabel info=new JLabel();
	private JLabel info2=new JLabel();
	private JLabel info3=new JLabel();
	private Box theButtonsBox = Box.createHorizontalBox();
	private Box thesecondButtonsBox = Box.createHorizontalBox();
	private Box theButtons3Box = Box.createHorizontalBox();
	public static JButton nodeButton;
	public static JButton edgeButton;
	public static JButton TransferFunctionButton;

	public FlowGraph() {
		initialize();
	}

	public void initialize() {

		frame.setTitle("SFG");
		frame.setLayout(new BorderLayout());
		frame.setSize(1100, 600);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		nodeButton = new JButton("New graph");
		info.setText("set number of nodes of new graph: ");
		info2.setText("calculate overall T.F: ");
		info3.setText("SFG drawing: ");
		edgeButton = new JButton("Add Edge");
		TransferFunctionButton = new JButton("T.F");
		Font font = new Font(null, 30, 20);
		nodeButton.setFont(font);
		nodeButton.setSize(100, 150);
		TransferFunctionButton.setFont(font);
		TransferFunctionButton.setSize(200, 200);
		edgeButton.setFont(font);
		edgeButton.setSize(100, 150);
		edgeButton.setBackground(Color.ORANGE);
		nodeButton.setBackground(Color.CYAN);
		TransferFunctionButton.setBackground(Color.GREEN);
		TransferFunctionButton.setBorderPainted(true);
		theButtonsBox.add(nodeButton);
		theButtons3Box.add(edgeButton);
		thesecondButtonsBox.add(TransferFunctionButton);
		upbuttons.add(info);
		upbuttons.add(theButtonsBox);
		up2buttons.add(theButtons3Box);
		downbuttons.add(info2);
		downbuttons.add(thesecondButtonsBox);
		drawing.add(info3);
		Control control = new Control();
		control.doActions();
		control.setBackground(Color.gray);
		control.setBounds(0, 0, 1000, 600);
		left.setLayout(null);
		left.add(control);
		frame.add(left, BorderLayout.CENTER);
		frame.add(upbuttons, BorderLayout.NORTH);
		frame.add(up2buttons, BorderLayout.EAST);
		frame.add(downbuttons, BorderLayout.SOUTH);
		frame.add(drawing,BorderLayout.WEST);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					FlowGraph sfg = new FlowGraph();
					sfg.frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
