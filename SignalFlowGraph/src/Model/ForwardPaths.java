package Model;

import java.util.ArrayList;

/** @author RowanAdel */
public class ForwardPaths {
	/** all forward paths */
	private ArrayList<ArrayList<Integer>> forwardPaths = new ArrayList<>();
	/** all forward paths gains */
	private ArrayList<Integer> forwardGains = new ArrayList<>();
	/** to calculate forward path */
	private ArrayList<Integer> forwardPath = new ArrayList<>();
	/** all nodes */
	private ArrayList<Node> nodes;
	/** value of path gain */
	private int pathGain = 1;

	/**
	 * initialize every thing needed and set nodes
	 * 
	 * @param nodes
	 *            all nodes
	 */
	public ForwardPaths(ArrayList<Node> nodes) {
		Node lastNode = nodes.get(nodes.size() - 1);
		if (lastNode.getOutBranches().size() != 0) {
			Node newNode = new Node(nodes.size());
			Branch b = new Branch(lastNode, newNode, 1, nodes.size() - 1, nodes.size());
			newNode.addInBranch(b);
			lastNode.addOutBranch(b);
			nodes.add(newNode);
		}
		this.nodes = nodes;
	}

	/**
	 * getter for forwardGains
	 * 
	 * @return forwardGains
	 * 
	 */
	public ArrayList<Integer> getForwardGains() {
		return forwardGains;
	}

	/**
	 * getter for forward paths
	 * 
	 * @return forwardPaths
	 */
	public ArrayList<ArrayList<Integer>> getForwardPaths() {
		getPath(nodes.get(0));
		return forwardPaths;
	}

	/**
	 * get the forward path
	 * 
	 * @param node
	 *            starting node
	 */
	public void getPath(Node node) {
		ArrayList<Branch> nodeOutBranches = node.getOutBranches();
		forwardPath.add(node.getNumber());
		for (int i = 0; i < nodeOutBranches.size(); i++) {
			node = nodeOutBranches.get(i).getEndingNode();
			int flag = 0;
			for (int j = 0; j < forwardPath.size(); j++) {
				if (node.getNumber() == forwardPath.get(j)) {
					flag = 1;
				}
			}
			if (flag == 0) {
				pathGain *= nodeOutBranches.get(i).getGain();
				getPath(node);
				pathGain /= nodeOutBranches.get(i).getGain();
			}
		}
		if (forwardPath.get(forwardPath.size() - 1) == nodes.size() - 1) {
			ArrayList<Integer> path = new ArrayList<>();
			for (int j = 0; j < forwardPath.size(); j++) {
				path.add(forwardPath.get(j));
			}
			forwardPaths.add(path);
			forwardGains.add(pathGain);
		}
		forwardPath.remove(forwardPath.size() - 1);
	}
}
