package Model;

import java.util.ArrayList;

public class Node {

	ArrayList<Branch> inBranches;
	ArrayList<Branch> outBranches;

	public Node() {
		inBranches = new ArrayList<Branch>();
		outBranches = new ArrayList<Branch>();
	}

	public ArrayList<Branch> getInBranches() {
		return inBranches;
	}

	public void setInBranches(ArrayList<Branch> inBranches) {
		this.inBranches = inBranches;
	}

	public ArrayList<Branch> getOutBranches() {
		return outBranches;
	}

	public void setOutBranches(ArrayList<Branch> outBranches) {
		this.outBranches = outBranches;
	}
}
