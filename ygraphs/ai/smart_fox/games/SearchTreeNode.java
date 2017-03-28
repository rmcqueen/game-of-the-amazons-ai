package ygraphs.ai.smart_fox.games;
import java.util.ArrayList;

public class SearchTreeNode {
    private int heuristicValue;
    private Arrow arrowShot;
    private Queen queen;
    private ArrayList<SearchTreeNode> children = new ArrayList<SearchTreeNode>();
    private SuccessorHeuristicFunction successorHeuristic = new SuccessorHeuristicFunction();
    protected GameRules gameRules;

    /**
     * SearchTreeNode constructor
     * @param board: a GameRules object containing the current board state
     * @param q: a Queen object contained in the SearchTreeNode
     * @param A: an Arrow object contained in the SearchTreeNode
     */
    public SearchTreeNode(GameRules board, Queen q, Arrow A) {
        gameRules = board;
        queen = q;
        arrowShot = A;
    }

    /**
     * SearchTreeNode constructor
     * @param board: a GameRules object containing the current board state
     * @param q: a Queen object contained in the SearchTreeNode
     * @param A: an Arrow object contained in the SearchTreeNode
     * @param val: a heuristic evaluation value indicating how good of a move this SearchTreeNode is
     */
    public SearchTreeNode(GameRules board, Queen q, Arrow A, int val) {
        gameRules = board;
        queen = q;
        arrowShot = A;
        heuristicValue = val;
    }

    /**
     * SearchTreeNode constructor
     * @param board: a GameRules object storing the current board's state
     */
    public SearchTreeNode(GameRules board) {
        gameRules = board;
    }

    /**
     *
     * @return:  a Queen object stored in the SearchTreeNode
     */
    public Queen getQueen() {
        return queen;
    }

    /**
     *
     * @return: an ArrayList storing all of this SearchTreeNode's children
     */
    public ArrayList<SearchTreeNode> getChildren() {
        return children;
    }

    /**
     *
     * @return: an Arrow object stored in the SearchTreeNode
     */
    public Arrow getArrowShot() {
        return arrowShot;
    }

    /**
     * setSuccessors: assigns children to a SearchTreeNode based off of the evaluation from the Heuristic function
     * @param ourMove: a boolean indicating whether or not the move is ours
     * @return: an ArrayList storing all of the good moves from the root SearchTreeNode
     */
    public ArrayList<SearchTreeNode> setSuccessors(boolean ourMove) {
        ArrayList<SearchTreeNode> expanded = successorHeuristic.getSuccessors(gameRules, ourMove);
        for (SearchTreeNode S : expanded) {
            children.add(S);
        }
        return children;
    }

    /**
     *
     * @return: the heuristicValue assigned to the SearchTreeNode
     */
    public int getValue() {
        return heuristicValue;
    }

    /**
     *
     * @param V: sets the heuristicValue of a SearchTreeNode
     */
    public void setValue(int V) {
        heuristicValue = V;
    }

} // end of SearchTreeNode
