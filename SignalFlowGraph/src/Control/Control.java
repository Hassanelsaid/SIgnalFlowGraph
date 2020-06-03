package Control;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.QuadCurve2D;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import Model.Branch;
import Model.MasonFormula;
import Model.Node;
import View.FlowGraph;
public class Control extends JPanel {
	private static final long serialVersionUID = 1L;
	ArrayList<Node> nodes = new ArrayList<Node>();
	int size = 0;
	boolean pr = true,checkInput = true;
	Graphics g;
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.g = g;
		int nodeEnd = 0,startNode = 0;
		Graphics2D graphSettings = (Graphics2D) g;
		graphSettings.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphSettings.setStroke(new BasicStroke(2));
		graphSettings.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		if (size != 0) {
			int xOffset = 1000 / size;
			for (int i = 0; i < size; i++) {
				String nodeNum = "";
				nodeNum += i;
				g.setColor(Color.decode("#c0392b"));
				g.fillOval(25 + (i * xOffset), 245, 10, 10);
				g.drawString(nodeNum, 27 + (i * xOffset), 270);
				g.setColor(Color.decode("#ff7675"));
			}
			for (int i = 0; i < size; i++) {
				ArrayList<Branch> out = nodes.get(i).getOutBranches();
				for (int j = 0; j < out.size(); j++) {
					Branch inbran = out.get(j);
					nodeEnd = inbran.getEndIndex();
					startNode = inbran.getStartIndex();
					int xStart;
					int xEnd;
					if (nodeEnd == startNode) {
						xStart = 30 + (startNode * xOffset);
						xEnd = 25 + (nodeEnd * xOffset);
						String gain = "";
						gain += inbran.getGain();
						g.drawOval(xStart, 225, 30, 30);
						g.setColor(Color.black);
						g.drawString(gain, xEnd + 15, 217);
						g.setColor(Color.decode("#ff7675"));
					} else {
						if (nodeEnd > startNode) {
							xStart = 30 + (startNode * xOffset);
							xEnd = 25 + (nodeEnd * xOffset);
						} else {
							xStart = 30 + (startNode * xOffset);
							xEnd = 35 + (nodeEnd * xOffset);
						}
						if (nodeEnd - startNode == 1) {
							drawArrowLine(g, xStart, 250, xEnd, 250, 5, 5);
							int mid = (xEnd - xStart) / 2;
							int midPosition = xStart + mid;
							String gain = "";
							gain += inbran.getGain();
							g.setColor(Color.black);
							g.drawString(gain, midPosition, 270);
							g.setColor(Color.decode("#ff7675"));
						} else {
							QuadCurve2D n = new QuadCurve2D.Double();
							double xDStart;
							double xDEnd;
							if (nodeEnd > startNode) {
								xDStart = 30 + (startNode * xOffset);
								xDEnd = 25 + (nodeEnd * xOffset);
							} else {
								xDStart = 30 + (startNode * xOffset);
								xDEnd = 35 + (nodeEnd * xOffset);
							}
							double diff = xDEnd - xDStart;
							double diff2 = diff / 2;
							double mid = xDStart + diff2;
							n.setCurve(xDStart, 250, mid, 250 - diff2, xEnd, 250);
							String gain = "";
							gain += inbran.getGain();
							g.setColor(Color.black);
							g.drawString(gain, (int) mid, (int) (270 - (diff2 / 2)));
							g.setColor(Color.decode("#ff7675"));
							if (nodeEnd > startNode) {
								drawArrowLine(g, ((int) xDEnd) - 5, 245, (int) xDEnd, 250, 5, 5);
							} else {
								drawArrowLine(g, ((int) xDEnd) + 5, 255, (int) xDEnd, 250, 5, 5);
							}
							((Graphics2D) g).draw(n);
						}
					}
				}
				if (checkInput) {
					pr = false;
				}
			}
		}
		if (pr == true) {
			repaint();
		}
	}
	public void doActions() {
		FlowGraph.nodeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				size = 0;
				nodes.clear();
				pr = true;
				paintComponent(g);
				try {
					String numOfNodes = JOptionPane.showInputDialog(null, "Nodes number :");
					int numNodes = Integer.parseInt(numOfNodes);
					for (int i = 0; i < numNodes; i++) {
						Node newNode = new Node(i);
						nodes.add(newNode);
					}
					size = numNodes;
				} catch (java.lang.NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Number is invalid");
				}
			}
		});
		FlowGraph.edgeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				pr = true;
				checkInput = false;
				paintComponent(g);
				String start = JOptionPane.showInputDialog(null, "first node:");
				int startNode = -1;
				int endNode = -1;
				try {
					startNode = Integer.parseInt(start);
					if (startNode < 0 || startNode > size - 1) {
						JOptionPane.showMessageDialog(null, "node number is invalid");
					}
					String end = JOptionPane.showInputDialog(null, "second node:");
					endNode = Integer.parseInt(end);
					if (endNode < 0 || endNode > size - 1) {
						JOptionPane.showMessageDialog(null, "node number is invalid");
					}
				} catch (java.lang.NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Enter a valid number");
				}
				if (endNode >= 0 && endNode < size && startNode >= 0 && startNode < size) {
					String gain = JOptionPane.showInputDialog(null, "the Gain :");
					int gainValue = Integer.parseInt(gain);
					Branch newBranch = new Branch(nodes.get(startNode), nodes.get(endNode), gainValue,
							startNode, endNode);
					nodes.get(startNode).addOutBranch(newBranch);
					nodes.get(endNode).addInBranch(newBranch);
					checkInput = true;
				}
			}
		});
		FlowGraph.TransferFunctionButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new MasonFormula(nodes);
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
