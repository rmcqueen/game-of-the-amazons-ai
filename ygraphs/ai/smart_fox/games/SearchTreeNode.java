package ygraphs.ai.smart_fox.games;

import java.util.ArrayList;

public class SearchTreeNode {
	private boolean terminal;
	private int heuristicValue;
	private Arrow arrowShot;
	private Queen queen;
    private ArrayList<SearchTreeNode> children = new ArrayList<SearchTreeNode>();
    private SuccessorHeuristicFunction successorHeuristic = new SuccessorHeuristicFunction();
    protected GameRules gameRules;


    public SearchTreeNode(GameRules board, Queen q, Arrow A){
        gameRules = board;
        queen = q;
        arrowShot = A;
    }

    public SearchTreeNode(GameRules board, Queen q, Arrow A, int val){
        gameRules = board;
        queen = q;
        arrowShot = A;
        this.heuristicValue = val;
    }


    public SearchTreeNode(GameRules board){
        gameRules = board;
    }



    public boolean isTerminal(){
    	return terminal;
    }

    
    public Queen getQueen(){
    	return queen;
    }


    public ArrayList<SearchTreeNode> getChildren() {
        return children;
    }
    
    public Arrow getArrowShot(){
        return arrowShot;
    }
    
    public ArrayList<SearchTreeNode> setSuccessors(boolean ourMove)  {
        ArrayList<SearchTreeNode> expanded = successorHeuristic.getSuccessors(gameRules, ourMove);
        for (SearchTreeNode S: expanded){
            children.add(S);
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
