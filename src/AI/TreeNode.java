package AI;

import java.util.ArrayList;

import Battle.Match;
import PokeMove.PokeMove;
/*
 * Created by Ben Bianchi 10/12/2016
 * TreeNode is just a datastructure we will use to compare nodes in a MINIMAX tree
 * This class does not drive anything, it is just a data structure.
 */

public class TreeNode<T>{

	public TreeNode root;
	public Match data;
	public ArrayList<TreeNode> children;


	    public TreeNode(TreeNode root, Match data) {
	    	this.root = root;
	        this.data = data;
	        this.children = new ArrayList<TreeNode>();
	    }

	public Match getData()
	{
		return data;
	}
	
	public boolean isRoot()
	{
		if (root == null)
			return true;
		else
			return false;
	}
	
}
