package Model;

public class Branch {

	private Node startingNode;
	private Node endingNode;
	private int gain;
	private int startIndex;
	private int endIndex;

	public Branch(Node start, Node end, int gain, int startNode, int endNode) {
		startingNode = start;
		endingNode = end;
		this.gain = gain;
		startIndex = startNode;
		endIndex = endNode;
	}

	public Node getStartingNode() {
		return startingNode;
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
		this.startingNode = startingNode;
	}

	public Node getEndingNode() {
		return endingNode;
	}

	public void setEndingNode(Node endingNode) {
		this.endingNode = endingNode;
	}

	public int getGain() {
		return gain;
	}

	public void setGain(int gain) {
		this.gain = gain;
	}

}