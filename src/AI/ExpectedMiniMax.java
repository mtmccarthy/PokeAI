package AI;

import java.util.ArrayList;
import java.util.Collections;
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

	private TreeNode root;

	public ExpectedMiniMax(TreeNode root){
		this.root = root;
	}


	public Integer expectiminimax(TreeNode node, Integer depth, boolean maxFirst) {
		Integer alpha = 0;
		
		if (depth == 0 || node.getData().valid() == false)
		{			
			return node.getData().getHeuristic(node.getData().isPlayerAGoing);
			
		}

		if (maxFirst == false) {
			maxFirst = true;
			alpha = 99999;
			// Return value of minimum-valued child node
			for (Integer i = 0; i < node.children.size(); i++) {
				
				alpha = Integer.min(alpha, expectiminimax(((TreeNode) node.children.get(i)), depth - 1,maxFirst));
				
			}
		}

		else if (maxFirst == true) {
			maxFirst = false;
			// Return value of maximum-valued child node
			alpha = -99999;
			for (Integer i = 0; i < root.children.size(); i++) {
				alpha = Integer.max(alpha, expectiminimax(((TreeNode) root.children.get(i)), depth - 1,maxFirst));
				
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
	public void loadOptions(int depth, Pokemon activP) throws InvalidModifier, InvalidPokemonError{
		System.out.println("Constructing Tree");
		buildChildren(root,depth,activP);
		System.out.println("Finished Tree");
		return;
	}
	
	//build 4 children for a node
	@SuppressWarnings("rawtypes")
	public void buildChildren(TreeNode node, Integer d, Pokemon activP) throws InvalidModifier, InvalidPokemonError{
		//build node for each possible move
		//creates child as copy of current node, then runs move 0-3 on opposing pokemon
		// in that node
		// CHILD 1 
		
		d = d -1;
		for (int i = 0; i < 4; i++)
		{
			
			Match m = new Match(node.getData().playerA,node.getData().playerB);
			
			m = m.evaluateMove(m,m.isPlayerAGoing,activP.getMoves().get(i));
			
			TreeNode c = new TreeNode(node,m);
			
			node.children.add(c);
			
			if (d > 0)
			{
				if (activP.equals(m.getCurrentA()))
					activP = m.getCurrentB();
				else
					activP = m.getCurrentA();
				buildChildren(c, d,activP);
			}
			
		}
	}

	//get move
	//stubbed to choose randomly
	//when implementing, base off of expected minimax value
	public PokeMove getMove(Integer d) throws InvalidModifier, InvalidPokemonError{
		
//		System.out.println("Choosing Best Option");
		Integer temp = 99999999;
		PokeMove bestMove = null;
		for ( int i = 0; i < root.children.size(); i++)
		{
			this.expectiminimax((TreeNode)root.children.get(i), d, true);
//			System.out.println(((TreeNode)root.children.get(i)).getData().getLastMove().getName());
			int cur = ((TreeNode)root.children.get(i)).getData().getInheritedHeuristic();
			
			if ( cur < temp)
			{
				temp = cur;
				bestMove= ((TreeNode)root.children.get(i)).getData().getLastMove();
			}
		}
//		System.out.println("Best Move Seems to be "+bestMove.getName());
		return bestMove;
	}
}

