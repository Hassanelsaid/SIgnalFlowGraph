package Model;

/** @author EshraqIbrahim */
public class Branch {
	/** start node */
	private Node startNode;
	/** end node */
	private Node endNode;
	/** gain value */
	private int gain;
	/** start node index */
	private int startIndex;
	/** end node index */
	private int endIndex;

	public Branch(Node start, Node end, int branchGain, int startNodeIndex, int endNodeIndex) {
		startNode = start;
		endNode = end;
		gain = branchGain;
		startIndex = startNodeIndex;
		endIndex = endNodeIndex;
	}

	/**
	 * Getter for startNode
	 * 
	 * @return startNode
	 */
	public Node getStartingNode() {
		return startNode;
	}

	/**
	 * Getter for startIndex
	 * 
	 * @return startIndex
	 */
	public int getStartIndex() {
		return startIndex;
	}

	/**
	 * set end Index
	 * 
	 * @param startIndex
	 *            the start index
	 */
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	/**
	 * Getter for endIndex
	 * 
	 * @return endIndex
	 */
	public int getEndIndex() {
		return endIndex;
	}

	/**
	 * set end index
	 * 
	 * @param endIndex
	 *            the end index
	 */
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	/**
	 * set start node index
	 * 
	 * @param startingNode
	 *            start Node
	 */
	public void setStartingNode(Node startingNode) {
		this.startNode = startingNode;
	}

	/**
	 * Getter for end Node
	 * 
	 * @return end Node
	 */
	public Node getEndingNode() {
		return endNode;
	}

	/**
	 * set end node
	 * 
	 * @param ending
	 *            Node the end node
	 */
	public void setEndingNode(Node endingNode) {
		this.endNode = endingNode;
	}

	/**
	 * Getter for gain value
	 * 
	 * @return gain value
	 */
	public int getGain() {
		return gain;
	}

	/**
	 * set gain
	 * 
	 * @param gain
	 *            gain value
	 */
	public void setGain(int gain) {
		this.gain = gain;
	}

}