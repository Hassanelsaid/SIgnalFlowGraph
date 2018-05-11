package View;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/** @author EshraqIbrahim */
public class ResultsTable extends JFrame {

	/** serial version UID */
	private static final long serialVersionUID = 1L;
	/** ArrayList contains the non touching loops indexes */
	ArrayList<ArrayList<Integer>> allNonTouchingLoops;
	/** ArrayList contains the forward paths nodes */
	ArrayList<ArrayList<Integer>> allForwardPaths;
	/** ArrayList contains the loops nodes */
	ArrayList<ArrayList<Integer>> allLoops;
	/** ArrayList contains the non touching loops gains */
	ArrayList<Integer> nonTouchingLoopsGain;
	/** ArrayList contains the forward paths gains */
	ArrayList<Integer> forwardGains;
	/** ArrayList contains the loops gains */
	ArrayList<Integer> loopGains;
	/** Table for forward paths */
	JTable forwardPathTable = new JTable();
	/** Table model for forward paths */
	DefaultTableModel forwardPathModel = new DefaultTableModel();
	/** Scroll for forward paths table */
	JScrollPane forwardPathScroll;
	/** Headers for forward paths table */
	String forwardPathHeaders[] = { "No", "Forward Paths", "Gain" };
	/** Table for loops */
	JTable loopsTable = new JTable();
	/** Table model for loops */
	DefaultTableModel loopsModel = new DefaultTableModel();
	/** Scroll for loops table */
	JScrollPane loopsScroll;
	/** Headers for loops table */
	String loopsHeaders[] = { "No", "Loops", "Gain" };
	/** Table for non touching loops indexes */
	JTable nonTouchingLoopsTable = new JTable();
	/** Table model for non touching loops paths */
	DefaultTableModel nonTouchingLoopsModel = new DefaultTableModel();
	/** Scroll for non touching loops table */
	JScrollPane nonTouchingLoopsScroll;
	/** Headers for non touching loops table */
	String nonTouchingLoopsHeaders[] = { "No", "NonTounching Loops", "Gain" };
	/** Box containing all tables */
	private Box theTablesBox = Box.createHorizontalBox();
	/** Box to add mason formula result to the middle table */
	private Box masonFormulaBox = Box.createVerticalBox();

	/** sets the table features and data */
	public ResultsTable(ArrayList<ArrayList<Integer>> nonTouching, ArrayList<ArrayList<Integer>> forward,
			ArrayList<ArrayList<Integer>> loops, ArrayList<Integer> nonGains, ArrayList<Integer> forwardGain,
			ArrayList<Integer> loopGain, String MasonResult) {
		allNonTouchingLoops = nonTouching;
		allForwardPaths = forward;
		allLoops = loops;
		nonTouchingLoopsGain = nonGains;
		forwardGains = forwardGain;
		loopGains = loopGain;
		JLabel label1 = new JLabel("Mason formula gain : " + MasonResult);
		label1.setFont(new Font("Serif", Font.PLAIN, 30));
		forwardPathModel.setColumnIdentifiers(forwardPathHeaders);
		forwardPathTable.setModel(forwardPathModel);
		loopsModel.setColumnIdentifiers(loopsHeaders);
		loopsTable.setModel(loopsModel);
		nonTouchingLoopsModel.setColumnIdentifiers(nonTouchingLoopsHeaders);
		nonTouchingLoopsTable.setModel(nonTouchingLoopsModel);
		forwardPathScroll = new JScrollPane(forwardPathTable);
		loopsScroll = new JScrollPane(loopsTable);
		nonTouchingLoopsScroll = new JScrollPane(nonTouchingLoopsTable);
		insert();
		theTablesBox.add(forwardPathScroll);
		masonFormulaBox.add(loopsScroll);
		masonFormulaBox.add(label1);
		theTablesBox.add(masonFormulaBox);
		theTablesBox.add(nonTouchingLoopsScroll);
		add(theTablesBox, BorderLayout.NORTH);
		this.setTitle("Results");
		setSize(1300, 520);
		setVisible(true);
	}

	/**
	 * insert data in rows in forward paths table , loops table , non touching loops
	 * table
	 */
	public void insert() {
		ArrayList<Integer> ar = new ArrayList<Integer>();
		ar.add(1);
		ar.add(0);

		for (int i = 0; i < allNonTouchingLoops.size(); i++) {
			nonTouchingLoopsModel.addRow(new Object[] { String.valueOf(i), String.valueOf(allNonTouchingLoops.get(i)),
					String.valueOf(nonTouchingLoopsGain.get(i)) });
		}
		for (int i = 0; i < allLoops.size(); i++) {

			loopsModel.addRow(new Object[] { String.valueOf(i), String.valueOf(allLoops.get(i)),
					String.valueOf(loopGains.get(i)) });
		}
		for (int i = 0; i < allForwardPaths.size(); i++) {
			forwardPathModel.addRow(new Object[] { String.valueOf(i), String.valueOf(allForwardPaths.get(i)),
					String.valueOf(forwardGains.get(i)) });

		}

	}

}
