package Model;

import java.util.ArrayList;

public class IndividualLoops {
	private ArrayList<Node> nodes;
	private ArrayList<ArrayList<Integer>> loops = new ArrayList<>();
	private int loopGain = 1;
	private ArrayList<Integer> loopGains = new ArrayList<>(),loop = new ArrayList<>();
	private ArrayList<Integer> visited = new ArrayList<>();
	public IndividualLoops(ArrayList<Node> nodes) {
		this.nodes = nodes;
	}
	public ArrayList<Integer> getAllLoopGains() {
		return loopGains;
	}
	public ArrayList<ArrayList<Integer>> getLoops() {
		for (int i = 0; i < nodes.size(); i++) {
			Node node = nodes.get(i);
			if (node.getInBranches().size() != 0) {
				ArrayList<Branch> outBranch = node.getOutBranches();
				loop.add(node.getNumber());
				for (int j = 0; j < outBranch.size(); j++) {
					Node out = outBranch.get(j).getEndingNode();
					if (out.getNumber() == node.getNumber()) {
						loop.add(node.getNumber());
						loopGain *= outBranch.get(j).getGain();
						loopGains.add(loopGain);
						loops.add(loop);
						loop.remove(loop.size() - 1);
					} else if (!visited.contains(out.getNumber())) {
						loopGain *= outBranch.get(j).getGain();
						getLoopPath(node, out);
					}
					loopGain = 1;
				}
			}
			visited.add(node.getNumber());
			loopGain = 1;
			loop = new ArrayList<>();
		}
		return loops;
	}
	public void getLoopPath(Node node, Node out) {
		loop.add(out.getNumber());
		ArrayList<Branch> branches = out.getOutBranches();
		for (int i = 0; i < branches.size(); i++) {
			out = branches.get(i).getEndingNode();
			int flag = 0;
			for (int j = 1; j < loop.size(); j++) {
				if (out.getNumber() == loop.get(j)) {
					flag = 1;
				}
			}
			if (flag == 0 && out.getNumber() != node.getNumber() && !visited.contains(out.getNumber())) {
				loopGain *= branches.get(i).getGain();
				getLoopPath(node, out);
				loopGain /= branches.get(i).getGain();
			}
			if (out.getNumber() == node.getNumber()) {
				loopGain *= branches.get(i).getGain();
				loop.add(node.getNumber());
				ArrayList<Integer> path = new ArrayList<>();
				for (int j = 0; j < loop.size(); j++) {
					path.add(loop.get(j));
				}
				loops.add(path);
				loopGains.add(loopGain);
				loopGain /= branches.get(i).getGain();
				loop.remove(loop.size() - 1);
			}
		}
		loop.remove(loop.size() - 1);
	}
}
