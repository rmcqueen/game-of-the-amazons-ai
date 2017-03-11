package src;
import java.util.ArrayList;

/**
 * Created by r on 2/15/17.
 */
public class SearchTreeNode {
    //private move queenMove;		// TO DO : figure out what a Move object is
    //private Arrow arrowShot;		// TO DO : figure out what an arrowShot object is
	private boolean terminal;
	private int heuristicValue;
    private ArrayList<SearchTreeNode> children = new ArrayList<SearchTreeNode>();
    private SuccessorHeuristicFunction successorHeuristic; // TO DO: instantiate Successor Function
    protected GameRules gameRules; // TO DO: create GameRules class later
    
    public boolean isTerminal(){
    	return terminal;
    }
    
    public SearchTreeNode(GameRules board,move M, Arrow A, int heuristicValue){
    	gameRules = board;
        queenMove = M; 		// TO DO : figure out what a Move object is
        arrowShot = A;		// TO DO : figure out what an arrowShot object is
        this.heuristicValue = heuristicValue;
    }

    public ArrayList<SearchTreeNode> getChildren() {
        return children;
    }
    
    // TO DO: figure out what the boolean parameter is
    public ArrayList<SearchTreeNode> setSuccessors(boolean Move){

        if(Move) {
            ArrayList<SearchTreeNode> expanded = successorHeuristic.getSuccessors(gameRules, true); // TO DO: figure out what the boolean represents
            // when implementing SuccessorHeuristicFunction.getSuccessors
            for (SearchTreeNode S: expanded){
                children.add(S);
            }
            return children;
        }else{
            ArrayList<SearchTreeNode> expanded = successorHeuristic.getSuccessors(gameRules, false);
            for (SearchTreeNode S: expanded) {
                children.add(S);
            }
            return children;
        }

    }
    
    public int getValue(){
        return heuristicValue;
    }
    
    public void setValue(int V){
    	heuristicValue = V;
    }



}
