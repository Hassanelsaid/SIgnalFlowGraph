package Control;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Path2D;
import java.awt.geom.QuadCurve2D;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Model.Branch;
import Model.Node;
import View.GUI;

public class Control extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Node[] myNodes;
	int size = 0;

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graphSettings = (Graphics2D) g;
		graphSettings.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphSettings.setStroke(new BasicStroke(2));
		graphSettings.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		//g.drawString("Hello", 50, 50);
		if (size != 0) {
			int xOffset = 1000 / size;
			for (int i = 0; i < size; i++) {
				// ArrayList<Branch> out = myNodes[i].getOutBranches();
				// for(int j=0;j<out.size();j++) {
				// Branch inbran = out.get(i);
				// int endNode = inbran.getEndIndex();
				// int startNode = inbran.getStartIndex();
				String nodeNum = "";
				nodeNum += i;
				g.drawOval(25 + (i * xOffset), 245, 10, 10);
				g.drawString(nodeNum, 27 + (i * xOffset), 270);
			}
			for (int i = 0; i < size; i++) {
				ArrayList<Branch> out = myNodes[i].getOutBranches();
				ArrayList<Branch> in = myNodes[i].getInBranches();

				for (int j = 0; j < out.size(); j++) {
					Branch inbran = out.get(j);
					int endNode = inbran.getEndIndex();
					int startNode = inbran.getStartIndex();
					int xStart;
					int xEnd;
					if (endNode > startNode) {
						xStart = 30 + (startNode * xOffset);
						xEnd = 25 + (endNode * xOffset);
					} else {
						xStart = 30 + (startNode * xOffset);
						xEnd = 35 + (endNode * xOffset);
					}
					if (Math.abs(endNode - startNode) == 1) {
						drawArrowLine(g, xStart, 250, xEnd, 250, 5, 5);
						int mid = (xEnd - xStart)/2;
						int midPosition = xStart + mid;
						String gain = "";
						gain += inbran.getGain();
						g.drawString(gain,midPosition, 270);
					} else {
						QuadCurve2D n = new QuadCurve2D.Double();
						double xDStart;
						double xDEnd;
						if (endNode > startNode) {
							xDStart = (double) (30 + (startNode * xOffset));
							xDEnd = (double) (25 + (endNode * xOffset));
						} else {
							xDStart = (double) (30 + (startNode * xOffset));
							xDEnd = (double) (35 + (endNode * xOffset));
						}
						double diff = xDEnd - xDStart;
						double diff2 = diff / 2;
						double mid = xDStart + diff2;
						String gain = "";
						gain += inbran.getGain();
						n.setCurve(xDStart, 250, mid, 250 - diff2, xEnd, 250);
						g.drawString(gain,(int) mid, (int) (270 - (diff2/2)));
						if (endNode > startNode) {
							drawArrowLine(g, ((int) xDEnd) - 5, 245, (int) xDEnd, 250, 5, 5);
						} else {
							drawArrowLine(g, ((int) xDEnd) + 5, 255, (int) xDEnd, 250, 5, 5);
						}

						((Graphics2D) g).draw(n);
					}
				}

			}

			// QuadCurve2D n = new QuadCurve2D.Double();
			// n.setCurve(250, 250, 350, 150, 450, 250);
			// drawArrowLine(g, 445, 245, 450, 250, 5, 5);
			// g.drawOval(245, 245, 10, 10);
			// g.drawOval(450, 250, 10, 10);
			// ((Graphics2D) g).draw(n);
		}

		repaint();

	}

	public void doActions() {
		GUI.drawButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String numOfNodes = JOptionPane.showInputDialog(null, "Enter the number of nodes :");
				int numNodes = Integer.parseInt(numOfNodes);

				myNodes = new Node[numNodes];
				for (int i = 0; i < numNodes; i++) {
					Node newNode = new Node();
					myNodes[i] = newNode;
				}
				String numOfbranches = JOptionPane.showInputDialog(null, "Enter the number of branches :");
				int numbranches = Integer.parseInt(numOfbranches);
				for (int i = 0; i < numbranches; i++) {
					String start = JOptionPane.showInputDialog(null, "Enter the starting node :");
					int startNode = Integer.parseInt(start);
					String end = JOptionPane.showInputDialog(null, "Enter the ending node :");
					int endNode = Integer.parseInt(end);
					String gain = JOptionPane.showInputDialog(null, "Enter the Gain value :");
					int gainValue = Integer.parseInt(gain);
					Branch newBranch = new Branch(myNodes[startNode], myNodes[endNode], gainValue, startNode, endNode);
					ArrayList<Branch> in = myNodes[endNode].getInBranches();
					ArrayList<Branch> out = myNodes[startNode].getOutBranches();
					in.add(newBranch);
					out.add(newBranch);
					myNodes[startNode].setOutBranches(out);
					myNodes[endNode].setInBranches(in);
				}
				size = numNodes;
			}
		});

	}

	private void drawArrowLine(Graphics g, int x1, int y1, int x2, int y2, int d, int h) {
		int dx = x2 - x1, dy = y2 - y1;
		double D = Math.sqrt(dx * dx + dy * dy);
		double xm = D - d, xn = xm, ym = h, yn = -h, x;
		double sin = dy / D, cos = dx / D;

		x = xm * cos - ym * sin + x1;
		ym = xm * sin + ym * cos + y1;
		xm = x;

		x = xn * cos - yn * sin + x1;
		yn = xn * sin + yn * cos + y1;
		xn = x;

		int[] xpoints = { x2, (int) xm, (int) xn };
		int[] ypoints = { y2, (int) ym, (int) yn };

		g.drawLine(x1, y1, x2, y2);
		g.fillPolygon(xpoints, ypoints, 3);
	}

}
