package Model;

import java.util.ArrayList;

public class Node {
	private int nodeNumber;
	private ArrayList<Branch> Bin,Bout;
	public Node(int number) {
		Bin = new ArrayList<Branch>();
		Bout = new ArrayList<Branch>();
		nodeNumber = number;
	}
	public int getNumber() {
		return nodeNumber;
	}
	public ArrayList<Branch> getInBranches() {
		return Bin;
	}
	public ArrayList<Branch> getOutBranches() {
		return Bout;
	}
	public void addInBranch(Branch inBranch) {
		Bin.add(inBranch);
	}
	public void addOutBranch(Branch outBranch) {
		Bout.add(outBranch);
	}
}
