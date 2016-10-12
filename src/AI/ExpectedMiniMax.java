package AI;

import Battle.Match;
import PokeMove.PokeMove;

public class ExpectedMiniMax {
	/*
	 * Created by Ben Bianchi on 10/12/2016
	 */

	private TreeNode<PokeMove> root;

	public Integer expectiminimax(TreeNode node, Integer depth) {
		Integer alpha = 0;
		if (depth ==0 || node.get().valid() == false)
			return node.get().getHeuristic();

		if (node.get().isPlayerAGoing == false) {
			alpha = 99999;
			// Return value of minimum-valued child node
			for (Integer i = 0; i < node.children.size(); i++) {
				alpha = Integer.min(alpha, expectiminimax(((TreeNode) node.children.get(i)), depth - 1));
			}
		}

		else if (root.get().isPlayerAGoing == false) {
			// Return value of maximum-valued child node
			alpha = -99999;
			for (Integer i = 0; i < root.children.size(); i++) {
				alpha = Integer.max(alpha, expectiminimax(((TreeNode) root.children.get(i)), depth - 1));
			}
			// else if random event at node
			// // Return weighted average of all child nodes' values
			// let α := 0
			// foreach child of node
			// α := α + (Probability[child] * expectiminimax(child, depth-1))
			// return α
		}

		return alpha;
	}
}
