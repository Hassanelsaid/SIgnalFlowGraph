package Model;

public class Branch {
	private int gain,startIndex,endIndex;
	private Node startNode,endNode;
	public Branch(Node start, Node end, int branchGain, int startNodeIndex, int endNodeIndex) {
		startNode = start;
		endNode = end;
		gain = branchGain;
		startIndex = startNodeIndex;
		endIndex = endNodeIndex;
	}
	public Node getStartingNode() {
		return startNode;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}
	public void setStartingNode(Node startingNode) {
		this.startNode = startingNode;
	}
	public Node getEndingNode() {
		return endNode;
	}
	public void setEndingNode(Node endingNode) {
		this.endNode = endingNode;
	}
	public int getGain() {
		return gain;
	}
	public void setGain(int gain) {
		this.gain = gain;
	}
}