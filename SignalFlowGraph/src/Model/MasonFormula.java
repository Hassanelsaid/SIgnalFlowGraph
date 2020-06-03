package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import View.ResultsTable;

public class MasonFormula {
	double delta;
	NonTouchingLoops nonTouchingLoops;
	ArrayList<ArrayList<Integer>> allNonTouchingLoops,allForwardPaths,allLoops;
	ArrayList<Integer> nonTouchingLoopsGain,forwardGains,loopGains;
	ArrayList<Double> pathDelta;
	ArrayList<Node> nodes;
	IndividualLoops loops;
	ForwardPaths forwardPaths;
	public MasonFormula(ArrayList<Node> graphNodes) {
		nodes = graphNodes;
		forwardPaths = new ForwardPaths(nodes);
		allForwardPaths = forwardPaths.getForwardPaths();
		forwardGains = forwardPaths.getForwardGains();
		loops = new IndividualLoops(nodes);
		allLoops = loops.getLoops();
		loopGains = loops.getAllLoopGains();
		nonTouchingLoops = new NonTouchingLoops(allLoops, loopGains);
		allNonTouchingLoops = nonTouchingLoops.getNonTouchingLoops();
		nonTouchingLoopsGain = nonTouchingLoops.getNontouchingGains();
		pathDelta = new ArrayList<Double>();
		calculatePathDelta();
		String result = calculateMasonFormula();
		new ResultsTable(pathDelta,delta,allNonTouchingLoops, allForwardPaths, allLoops, nonTouchingLoopsGain, forwardGains, loopGains,
				result);
	}
	public double calculateDelta(ArrayList<Integer> loopGains, ArrayList<ArrayList<Integer>> allNonTouchingLoops,
			ArrayList<Integer> nonTouchingLoopsGain) {
		double delta = 1;
		double sumOfLoopsGain = 0;
		for (int i = 0; i < loopGains.size(); i++) {
			sumOfLoopsGain += loopGains.get(i);
		}
		double sumofNonTouching = 0;
		for (int i = 0; i < nonTouchingLoopsGain.size(); i++) {
			if ((allNonTouchingLoops.get(i).size() % 2) == 0) {
				sumofNonTouching += nonTouchingLoopsGain.get(i);
			} else {
				sumofNonTouching -= nonTouchingLoopsGain.get(i);
			}
		}
		return delta - sumOfLoopsGain + sumofNonTouching;
	}
	public void calculatePathDelta() {
		boolean flag = false;
		for (int i = 0; i < allForwardPaths.size(); i++) {
			ArrayList<ArrayList<Integer>> notTouchingPathLoops = new ArrayList<ArrayList<Integer>>();
			notTouchingPathLoops.addAll(allLoops);
			ArrayList<Integer> notTouchingPathgain = new ArrayList<Integer>();
			notTouchingPathgain.addAll(loopGains);
			double delta = 1;
			for (int j = 0; j < allLoops.size(); j++) {
				flag = false;
				List<Integer> common = new ArrayList<Integer>(allForwardPaths.get(i));
				common.retainAll(allLoops.get(j));
				if (common.size() != 0) {
					flag = true;
				}
				if (flag == true) {
					notTouchingPathLoops.set(j, null);
					notTouchingPathgain.set(j, null);
				}
			}
			notTouchingPathLoops.removeAll(Collections.singleton(null));
			notTouchingPathgain.removeAll(Collections.singleton(null));
			NonTouchingLoops nonTouchingPath = new NonTouchingLoops(notTouchingPathLoops, notTouchingPathgain);
			ArrayList<ArrayList<Integer>> newLoops = nonTouchingPath.getNonTouchingLoops();
			ArrayList<Integer> nonTouchingPathGain = nonTouchingPath.getNontouchingGains();
			delta = calculateDelta(notTouchingPathgain, newLoops, nonTouchingPathGain);
			pathDelta.add(delta);
		}
	}
	public String calculateMasonFormula() {
		double overAllTransfer = 0;
		for (int i = 0; i < forwardGains.size(); i++) {
			overAllTransfer += (forwardGains.get(i) * pathDelta.get(i));
		}
		delta = calculateDelta(loopGains, allNonTouchingLoops, nonTouchingLoopsGain);
		overAllTransfer = overAllTransfer / delta;
		return Double.toString(overAllTransfer);
	}
}
