package src;

import java.util.ArrayList;

public class SearchTreeNode {
    //private move queenMove;		// TO DO : figure out what a Move object is
    //private Arrow arrowShot;		// TO DO : figure out what an arrowShot object is
	private boolean terminal;
	private int heuristicValue;
	private Arrow arrowShot;
	private Queen queen;
    private ArrayList<SearchTreeNode> children = new ArrayList<SearchTreeNode>();
    private SearchTreeNode parent;
    private SuccessorHeuristicFunction successorHeuristic = new SuccessorHeuristicFunction();
    protected GameRules gameRules;
    
    public boolean isTerminal(){
    	return terminal;
    }
    
    public SearchTreeNode(GameRules board){
        gameRules = board;
    }
    
    public Queen getQueen(){
    	return queen;
    }
    
    public SearchTreeNode getParent(){
    	return parent;
    }
    
    public void setParent(SearchTreeNode n){
    	parent = n;
    }
    public SearchTreeNode(GameRules board, Queen q, Arrow A, int heuristicValue){
    	gameRules = board;	
    	queen = q;
    	arrowShot = A;
        this.heuristicValue = heuristicValue;
    }
    
    public SearchTreeNode(GameRules board, Queen q, Arrow A){
    	gameRules = board;	
    	queen = q;
    	arrowShot = A;
    }

    public ArrayList<SearchTreeNode> getChildren() {
        return children;
    }
    
    public Arrow getArrowShot(){
        return arrowShot;
    }
    
    public ArrayList<SearchTreeNode> setSuccessors(boolean ourMove) throws CloneNotSupportedException{
        ArrayList<SearchTreeNode> expanded = successorHeuristic.getSuccessors(gameRules, ourMove);
        for (SearchTreeNode S: expanded){
            children.add(S);
            S.setParent(this);
        }
        return children;
    }
    
    public int getValue(){
        return heuristicValue;
    }
    
    public void setValue(int V){
    	heuristicValue = V;
    }
    
    public void setTerminal(boolean t){
    	terminal = t;
    }
}
