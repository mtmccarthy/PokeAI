package AI;

import Battle.Match;
import Error.InvalidModifier;
import Error.InvalidPokemonError;
import PokeMove.PokeMove;
import Pokemon.Pokemon;

public class ExpectedMiniMax {
	/*
	 * Created by Ben Bianchi on 10/12/2016
	 * Edited by Jacob Link
	 */

	private TreeNode<PokeMove> root;

	public Integer expectiminimax(TreeNode node, Integer depth) {
		Integer alpha = 0;
		if (depth ==0 || node.getData().valid() == false)
			return node.getData().getHeuristic();

		if (node.getData().isPlayerAGoing == false) {
			alpha = 99999;
			// Return value of minimum-valued child node
			for (Integer i = 0; i < node.children.size(); i++) {
				alpha = Integer.min(alpha, expectiminimax(((TreeNode) node.children.get(i)), depth - 1));
			}
		}

		else if (node.getData().isPlayerAGoing == true) {
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

	//adds all 4 children to a node
	public void loadOptions(TreeNode node){
		//build node for each possible move
		//creates child as copy of current node, then runs move 0-3 on opposing pokemon
		// in that node
		
		// CHILD 1 
		TreeNode c1 = new TreeNode(node, null);
		c1.data = root.data;
		node.children.add(c1);
		
		// CHILD 2
		TreeNode c2 = new TreeNode(node, null);
		c2.data = root.data;
		node.children.add(c2);

		// CHILD 3
		TreeNode c3 = new TreeNode(node, null);
		c3.data = root.data;
		node.children.add(c3);
		
		// CHILD 4
		TreeNode c4 = new TreeNode(node, null);
		c4.data = root.data;
		node.children.add(c4);
			
		//if player a, run a's moves
		if(node.getData().isPlayerAGoing == true){
			//run move in child
			for(int i=0;i<4;i++){
				TreeNode child = (TreeNode) node.children.get(i);
				Pokemon a = child.data.getCurrentA();
				Pokemon b = child.data.getCurrentB();
				try {
					a.attack(b, a.getMoves().get(i));
				} catch (InvalidPokemonError e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidModifier e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				child.data.setCurrentA(a);
				child.data.setCurrentB(b);
			}
		}
		else{
			//if player b is going, run b's moves
			for(int i=0;i<4;i++){
				TreeNode child = (TreeNode) node.children.get(i);
				Pokemon a = child.data.getCurrentA();
				Pokemon b = child.data.getCurrentB();
				try {
					b.attack(a, b.getMoves().get(i));
				} catch (InvalidPokemonError e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidModifier e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				child.data.setCurrentA(a);
				child.data.setCurrentB(b);
			}
		}
		
	}
}
