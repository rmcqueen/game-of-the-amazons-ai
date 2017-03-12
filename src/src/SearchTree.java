package src;
import java.util.ArrayList;

/**
 * Created by r on 2/15/17.
 */
// ITERATIVE DEEPENING-SEARCH
public class SearchTree {
    private SearchTreeNode root;
    private int depth;
    private int numOfMoves;
    public static int evaluation;
    private ArrayList<SearchTreeNode> frontier = new ArrayList<SearchTreeNode>();

    public SearchTree(SearchTreeNode node) {
        this.root = node;
        this.depth = 0;
        this.evaluation = 0;
    }

    public void calculateDepth() {
        SearchTreeNode n = this.root;
        int tempDepth = 0;
        while(n != null) {
            if(!n.getChildren().isEmpty()) {
                n = n.getChildren().get(0);
            }
            else{
                break;
            }
            tempDepth++;
        }
        this.depth = tempDepth;
    }
    
    private int AlphaBeta(SearchTreeNode N, int D, int alpha, int beta, boolean maxPlayer){


        if(D == 0 || N.getChildren().size() == 0) {
            evaluation++;
            if(numOfMoves<7) {
                queenHeuristic.calculate(N.B);
                N.setValue(queenHeuristic.ownedByUs - queenHeuristic.ownedByThem);

            }else{
                kingHeuristic.calculate(N.B);
                N.setValue(kingHeuristic.ownedByUs - kingHeuristic.ownedByThem);

            }
            int val = N.getValue();
            return val;
        }

        if(maxPlayer){

            int V = Integer.MIN_VALUE;
            for(SearchTreeNode S: N.getChildren()){

                V = Math.max(V, AlphaBeta(S,D - 1, alpha, beta, false ));
                alpha = Math.max(alpha, V);

                if(beta <= alpha)
                    break;
            }
            N.setValue(V);
            return V;
        }else{

            int V = Integer.MAX_VALUE;
            for(SearchTreeNode S: N.getChildren()){

                V = Math.min(V, AlphaBeta(S,D - 1, alpha, beta, true ));
                beta = Math.min(beta, V);

                if(beta <= alpha)
                    break;
            }
            N.setValue(V);
            return V;
        }
    }
    
    public void expandFrontier(){
        ArrayList<SearchTreeNode> newFrontier = new ArrayList<SearchTreeNode>();
        if(depth != 0){
            if(depth % 2 ==0){
                for(SearchTreeNode S: frontier)
                    newFrontier.addAll(S.setSuccessors(true));
            }else{
                for(SearchTreeNode S: frontier)
                    newFrontier.addAll(S.setSuccessors(false));
            }
        }else{
            newFrontier.addAll(root.setSuccessors(true));
        }

        //clearing the old frontier and setting the new one
        frontier.clear();
        for(SearchTreeNode S: newFrontier){
            SearchTreeNode newNode =new SearchTreeNode(S.gameRules.deepCopy());
            frontier.add(newNode);
        }
        depth++;
    }
    
    public void performAlphaBeta(){
        evaluation=0;
        calculateDepth();
        AlphaBeta(root, depth, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
    }
    
    public void trimFrontier(){
        int avg = 0;
        for (SearchTreeNode S: frontier){
            if(numOfMoves>=14) {
                kingHeuristic.calculate(S.gameRules);
                S.setValue(kingHeuristic.ownedByUs);
            }else{
                queenHeuristic.calculate(S.gameRules);
                S.setValue(queenHeuristic.ownedByUs);
            }

                avg += S.getValue();
        }
        if(frontier.size() != 0)
            avg = avg/frontier.size();
        ArrayList<SearchTreeNode> toRemove = new ArrayList<SearchTreeNode>();
        for(SearchTreeNode S: frontier){
            if(S.getValue() < avg){
                toRemove.add(S);
            }
        }

        for(SearchTreeNode S: toRemove){
            frontier.remove(S);
        }
    }

}
