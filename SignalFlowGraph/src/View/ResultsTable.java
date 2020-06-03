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

public class ResultsTable extends JFrame {

	private static final long serialVersionUID = 1L;
	ArrayList<ArrayList<Integer>> allNonTouchingLoops;
	ArrayList<ArrayList<Integer>> allForwardPaths;
	ArrayList<ArrayList<Integer>> allLoops;
	ArrayList<Integer> nonTouchingLoopsGain;
	ArrayList<Integer> forwardGains;
	ArrayList<Integer> loopGains;
	JTable forwardPathTable = new JTable();
	DefaultTableModel forwardPathModel = new DefaultTableModel();
	JScrollPane forwardPathScroll;
	String forwardPathHeaders[] = { "Number", "Forward Path", "Gain" , "Delta" };
	JTable loopsTable = new JTable();
	DefaultTableModel loopsModel = new DefaultTableModel();
	JScrollPane loopsScroll;
	String loopsHeaders[] = { "Number", "Loop", "Gain" };
	JTable nonTouchingLoopsTable = new JTable();
	DefaultTableModel nonTouchingLoopsModel = new DefaultTableModel();
	JScrollPane nonTouchingLoopsScroll;
	String nonTouchingLoopsHeaders[] = { "Number", "NonTounching Loop", "Gain" };
	private Box theTablesBox = Box.createHorizontalBox();
	private Box masonFormulaBox = Box.createVerticalBox();
	private Box theOverallGainBox = Box.createVerticalBox();
	ArrayList<Double> forwardPathDelta;

	public ResultsTable(ArrayList<Double> pathDelta, double deltaValue ,ArrayList<ArrayList<Integer>> nonTouching, ArrayList<ArrayList<Integer>> forward,
			ArrayList<ArrayList<Integer>> loops, ArrayList<Integer> nonGains, ArrayList<Integer> forwardGain,
			ArrayList<Integer> loopGain, String MasonResult) {
		allNonTouchingLoops = nonTouching;
		allForwardPaths = forward;
		allLoops = loops;
		nonTouchingLoopsGain = nonGains;
		forwardGains = forwardGain;
		loopGains = loopGain;
		forwardPathDelta = pathDelta;
		JLabel label1 = new JLabel(MasonResult);
		JLabel label2 = new JLabel("Overall gain : " );
		JLabel label3 = new JLabel("Forward paths : " );
		JLabel label4 = new JLabel("Loops : " );
		JLabel label5 = new JLabel("Nontouching loops : " );
		label1.setFont(new Font("Serif", Font.PLAIN, 30));
		label2.setFont(new Font("Serif", Font.PLAIN, 30));
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
		theTablesBox.add(label3);
		theTablesBox.add(forwardPathScroll);
		masonFormulaBox.add(label4);
		masonFormulaBox.add(loopsScroll);
		masonFormulaBox.add(label5);
		masonFormulaBox.add(nonTouchingLoopsScroll);
		theTablesBox.add(masonFormulaBox);
		theOverallGainBox.add(label2);
		theOverallGainBox.add(label1);
		theTablesBox.add(theOverallGainBox);
		add(theTablesBox, BorderLayout.NORTH);
		this.setTitle("Transfer Function");
		setSize(1300, 570);
		setVisible(true);
	}

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
					String.valueOf(forwardGains.get(i)) , String.valueOf(forwardPathDelta.get(i)) });

		}

	}

}
