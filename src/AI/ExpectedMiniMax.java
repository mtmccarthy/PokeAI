package AI;

import java.util.Random;

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

	//builds tree, given depth and match state
	public void loadOptions(Match m, int depth){
		//make root node
		TreeNode node = new TreeNode(null, m);
		
		//currently can build up to depth 4
		//not set up efficiently but should build out fully
		switch (depth){
		case 0:
			break;
		case 1:
			buildChildren(node);
			break;
		case 2:
			buildChildren(node);
			for(int i = 0; i<4;i++){
				buildChildren((TreeNode) node.children.get(i));
			}
			break;
		case 3:
			buildChildren(node);
			for(int i = 0; i<4;i++){
				buildChildren((TreeNode) node.children.get(i));
				for(int j = 0;j<4;j++){
					buildChildren((TreeNode)((TreeNode)node.children.get(i)).children.get(j));
				}
			}
			break;
		case 4:
			buildChildren(node);
			for(int i = 0; i<4;i++){
				buildChildren((TreeNode) node.children.get(i));
				for(int j = 0;j<4;j++){
					buildChildren((TreeNode)((TreeNode)node.children.get(i)).children.get(j));
					for(int k =0;k<4;k++){
						buildChildren((TreeNode)((TreeNode)((TreeNode)node.children.get(i)).children.get(j)).children.get(k));
					}
				}
			}
			break;
		}
		
		this.root = node;
	}
	
	//build 4 children for a node
	public void buildChildren(TreeNode node){
		//build node for each possible move
		//creates child as copy of current node, then runs move 0-3 on opposing pokemon
		// in that node
		
		// CHILD 1 
		TreeNode c1 = new TreeNode(node, node.getData());
		node.children.add(c1);
		
		// CHILD 2
		TreeNode c2 = new TreeNode(node, node.getData());
		node.children.add(c2);

		// CHILD 3
		TreeNode c3 = new TreeNode(node, node.getData());
		node.children.add(c3);
		
		// CHILD 4
		TreeNode c4 = new TreeNode(node, node.getData());
		node.children.add(c4);
			
		//ADD CHECKS FOR GAMEOVER AFTER MOVE
		
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

	//get move
	//stubbed to choose randomly
	//when implementing, base off of expected minimax value
	public int getMove(){
		
		return (int) Math.random() * 4;
	}

}

