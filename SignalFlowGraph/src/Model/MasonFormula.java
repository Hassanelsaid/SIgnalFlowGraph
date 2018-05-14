package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import View.ResultsTable;

/** @author EshraqIbrahim */
public class MasonFormula {
	/** all non touching loops indexes */
	ArrayList<ArrayList<Integer>> allNonTouchingLoops;
	/** all forward paths node numbers */
	ArrayList<ArrayList<Integer>> allForwardPaths;
	/** all loops node numbers */
	ArrayList<ArrayList<Integer>> allLoops;
	/** all non touching loops gain */
	ArrayList<Integer> nonTouchingLoopsGain;
	/** forward paths gains */
	ArrayList<Integer> forwardGains;
	/** all loops gains */
	ArrayList<Integer> loopGains;
	/** delta for each forward path */
	ArrayList<Double> pathDelta;
	/** all nodes */
	ArrayList<Node> nodes;
	/** object of nonTouchingLoops */
	NonTouchingLoops nonTouchingLoops;
	/** object of Individual Loops */
	IndividualLoops loops;
	/** object of forward paths */
	ForwardPaths forwardPaths;
	/** delta of over all function */
	double delta;

	/**
	 * initialize and set every thing needed to calculate mason formula
	 * 
	 * @param graphNodes
	 *            all nodes
	 */
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

	/**
	 * calculate delta
	 * 
	 * @return delta
	 */
	public double calculateDelta(ArrayList<Integer> loopGains, ArrayList<ArrayList<Integer>> allNonTouchingLoops,
			ArrayList<Integer> nonTouchingLoopsGain) {
		double delta = 1;
		// sum of individual loops
		double sumOfLoopsGain = 0;
		for (int i = 0; i < loopGains.size(); i++) {
			sumOfLoopsGain += loopGains.get(i);
		}
		// sum of non touching loops
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

	/** calculate delta for each path */
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

	/**
	 * calculate Mason Formula
	 * 
	 * @return overAllTransfer value
	 */
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
