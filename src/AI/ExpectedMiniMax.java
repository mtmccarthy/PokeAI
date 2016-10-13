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

	private TreeNode root;

	public ExpectedMiniMax(TreeNode root){
		this.root = root;
	}


	public Integer expectiminimax(TreeNode node, Integer depth) {
		Integer alpha = 0;
		if (depth == 0 || node.getData().valid() == false)
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
	public ExpectedMiniMax loadOptions(int depth) throws InvalidModifier, InvalidPokemonError{
		Match currentMatch = this.root.getData();

		//make root node
		TreeNode node = new TreeNode(null, currentMatch);
		
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
		return this;
	}
	
	//build 4 children for a node
	public void buildChildren(TreeNode node) throws InvalidModifier, InvalidPokemonError{
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
				Match childData = child.getData();
				PokeMove attackingMove = childData.getCurrentA().getMoves().get(i);
				Match resultingMatch = childData.evaluateMove(childData, true, attackingMove);
				TreeNode newNode = new TreeNode(node.root, resultingMatch);
				node.children.set(i, newNode);
				/*
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
				*/
			}
		}
		else{
			//if player b is going, run b's moves
			for(int i=0;i<4;i++){
				TreeNode child = (TreeNode) node.children.get(i);
				Match childData = child.getData();
				PokeMove attackingMove = childData.getCurrentA().getMoves().get(i);
				Match resultingMatch = childData.evaluateMove(childData, false, attackingMove);
				TreeNode newNode = new TreeNode(node.root, resultingMatch);
				node.children.set(i, newNode);

				/*
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
				*/
			}
		}
	}

	//get move
	//stubbed to choose randomly
	//when implementing, base off of expected minimax value
	public PokeMove getMove(Match match, boolean playerA) throws InvalidModifier, InvalidPokemonError{

		//Integer heuristic = expectiminimax(this.root, 3);

		//Determine result of each move

		Match result1;
		Match result2;
		Match result3;
		Match result4;
		PokeMove move1;
		PokeMove move2;
		PokeMove move3;
		PokeMove move4;

		Pokemon attacker;
		if(playerA) {
			attacker = match.getCurrentA();
			move1 = attacker.getMoves().get(0);
			move2 = attacker.getMoves().get(1);
			move3 = attacker.getMoves().get(2);
			move4 = attacker.getMoves().get(3);
			result1 = match.evaluateMove(match, playerA, move1);
			result2 = match.evaluateMove(match, playerA, move2);
			result3 = match.evaluateMove(match, playerA, move3);
			result4 = match.evaluateMove(match, playerA, move4);
		}
		else {
			attacker = match.getCurrentB();
			move1 = attacker.getMoves().get(0);
			move2 = attacker.getMoves().get(1);
			move3 = attacker.getMoves().get(2);
			move4 = attacker.getMoves().get(3);
			result1 = match.evaluateMove(match, !playerA, move1);
			result2 = match.evaluateMove(match, !playerA, move2);
			result3 = match.evaluateMove(match, !playerA, move3);
			result4 = match.evaluateMove(match, !playerA, move4);
		}
			int heuristic1 = result1.getHeuristic();
			System.out.println(attacker.getName() + " using " + move1.getName() + " would give heuristic " + heuristic1 + " with a hp " + result1.getCurrentA().getStats().getHitPoints().getBase() + " and b hp " + result1.getCurrentB().getStats().getHitPoints().getBase());
			int heuristic2 = result2.getHeuristic();
			System.out.println(attacker.getName() + " using " + move2.getName() + " would give heuristic " + heuristic2 + " with a hp " + result2.getCurrentA().getStats().getHitPoints().getBase() + " and b hp " + result2.getCurrentB().getStats().getHitPoints().getBase());
			int heuristic3 = result3.getHeuristic();
			System.out.println(attacker.getName() + " using " + move3.getName() + " would give heuristic " + heuristic3 + " with a hp " + result3.getCurrentA().getStats().getHitPoints().getBase() + " and b hp " + result3.getCurrentB().getStats().getHitPoints().getBase());
			int heuristic4 = result4.getHeuristic();
			System.out.println(attacker.getName() + " using " + move4.getName() + " would give heuristic " + heuristic4 + " with a hp " + result4.getCurrentA().getStats().getHitPoints().getBase() + " and b hp " + result4.getCurrentB().getStats().getHitPoints().getBase());

			if(heuristic1 >= heuristic2 && heuristic1 >= heuristic3 && heuristic1 >= heuristic4) {
				return move1;
			}
			else if(heuristic2 >= heuristic1 && heuristic2 >= heuristic3 && heuristic2 >= heuristic4) {
				return move2;
			}
			else if(heuristic3 >= heuristic1 && heuristic3 >= heuristic2 && heuristic3 >= heuristic4) {
				return move3;
			}
			else {
				return move4;
			}
		}
}

