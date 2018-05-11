package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/** @author EshraqIbrahim */
public class NonTouchingLoops {
	/** ArrayList contains all loops */
	private ArrayList<ArrayList<Integer>> allLoops;
	/** ArrayList contains the loops gain */
	private ArrayList<Integer> allLoopsGain;
	/** Headers for non touching loops */
	private ArrayList<ArrayList<Integer>> nonTouchingLoops;
	/** ArrayList contains non touching loops indexes */
	private ArrayList<ArrayList<Integer>> nonTouchingLoopsIndex;
	/** ArrayList contains non touching loops gain */
	private ArrayList<Integer> nonTouchingLoopsGain;

	/**
	 * initialize arrayList and set loops and loops gains
	 * 
	 * @param loops
	 *            get list of loops
	 * @param loopGains
	 *            get list of loops gain
	 * 
	 */
	public NonTouchingLoops(ArrayList<ArrayList<Integer>> loops, ArrayList<Integer> loopGains) {
		allLoops = loops;
		allLoopsGain = loopGains;
		nonTouchingLoopsGain = new ArrayList<Integer>();
		nonTouchingLoopsIndex = new ArrayList<ArrayList<Integer>>();
		nonTouchingLoops = new ArrayList<ArrayList<Integer>>();
	}

	/**
	 * Get the non touching loops
	 * 
	 * @return ArrayList contains ArrayLists of indexes of non touching loops
	 * 
	 */
	public ArrayList<ArrayList<Integer>> getNonTouchingLoops() {
		int flag = 0;
		Queue<ArrayList<Integer>> myQueue = new LinkedList<ArrayList<Integer>>();
		Queue<ArrayList<Integer>> myQueueIndex = new LinkedList<ArrayList<Integer>>();
		for (int i = 0; i < allLoops.size(); i++) {
			ArrayList<Integer> loop = new ArrayList<Integer>();
			ArrayList<Integer> loopIndex = new ArrayList<Integer>();
			loop.addAll(allLoops.get(i));
			loopIndex.add(i);
			myQueue.add(loop);
			myQueueIndex.add(loopIndex);
		}
		while (myQueue.size() != 0) {
			ArrayList<Integer> element = myQueue.poll();
			ArrayList<Integer> elementIndex = myQueueIndex.poll();
			ArrayList<Integer> nodesNumbers = new ArrayList<Integer>();
			for (int k = 0; k < element.size(); k++) {
				nodesNumbers.add(element.get(k));
			}
			Collections.sort(nodesNumbers);
			Collections.sort(elementIndex);
			nonTouchingLoops.add(nodesNumbers);
			if (elementIndex.size() != 1) {
				nonTouchingLoopsIndex.add(elementIndex);
			}
			for (int i = 0; i < allLoops.size(); i++) {
				flag = 0;
				for (int j = 0; j < allLoops.get(i).size(); j++) {
					for (int k = 0; k < element.size(); k++) {
						if (allLoops.get(i).get(j) == element.get(k)) {
							flag = 1;
							break;
						}
					}
					if (flag == 1) {
						break;
					}
				}
				if (flag == 0) {
					ArrayList<Integer> newNonTouchingLoops = new ArrayList<Integer>();
					ArrayList<Integer> newNonTouchingLoopsIndex = new ArrayList<Integer>();
					for (int z = 0; z < element.size(); z++) {
						newNonTouchingLoops.add(element.get(z));
					}
					newNonTouchingLoopsIndex.addAll(elementIndex);
					for (int z = 0; z < allLoops.get(i).size(); z++) {
						newNonTouchingLoops.add(allLoops.get(i).get(z));
					}
					newNonTouchingLoopsIndex.add(i);
					myQueue.add(newNonTouchingLoops);
					myQueueIndex.add(newNonTouchingLoopsIndex);
				}
			}
		}
		Set<ArrayList<Integer>> removeDublicates = new LinkedHashSet<>();
		removeDublicates.addAll(nonTouchingLoopsIndex);
		nonTouchingLoopsIndex.clear();
		nonTouchingLoopsIndex.addAll(removeDublicates);
		calculateGain();
		return nonTouchingLoopsIndex;
	}

	/** Calculate non touching loops gain */
	private void calculateGain() {
		for (int i = 0; i < nonTouchingLoopsIndex.size(); i++) {
			int gain = 1;
			for (int j = 0; j < nonTouchingLoopsIndex.get(i).size(); j++) {
				gain *= allLoopsGain.get(nonTouchingLoopsIndex.get(i).get(j));
			}
			nonTouchingLoopsGain.add(gain);
		}
	}

	/**
	 * Getter for nonTouchingLoopsGain
	 * 
	 * @return nonTouchingLoopsGain
	 * 
	 */
	public ArrayList<Integer> getNontouchingGains() {
		return nonTouchingLoopsGain;
	}
}
