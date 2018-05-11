package Model;

import java.util.ArrayList;

/** @author EshraqIbrahim */
public class Node {
	/** ArrayList contains the in branches */
	private ArrayList<Branch> inBranches;
	/** ArrayList contains the out branches */
	private ArrayList<Branch> outBranches;
	/** node number */
	private int nodeNumber;

	public Node(int number) {
		inBranches = new ArrayList<Branch>();
		outBranches = new ArrayList<Branch>();
		nodeNumber = number;
	}

	/**
	 * get the node number
	 * 
	 * @return nodeNumber
	 */
	public int getNumber() {
		return nodeNumber;
	}

	/**
	 * get the in branches
	 * 
	 * @return inBranches
	 */
	public ArrayList<Branch> getInBranches() {
		return inBranches;
	}

	/**
	 * get the out branches
	 * 
	 * @return outBranches
	 */
	public ArrayList<Branch> getOutBranches() {
		return outBranches;
	}

	/**
	 * add branch to in branches
	 * 
	 * @param inBranch
	 *            branch to be added
	 * 
	 */
	public void addInBranch(Branch inBranch) {
		inBranches.add(inBranch);
	}

	/**
	 * add branch to out branches
	 * 
	 * @param outBranch
	 *            branch to be added
	 * 
	 */
	public void addOutBranch(Branch outBranch) {
		outBranches.add(outBranch);
	}
}
