package ygraphs.ai.smart_fox.games;

import java.util.ArrayList;

// ITERATIVE DEEPENING-SEARCH
public class SearchTree {
    private SearchTreeNode root;
    private minDisHeur minDistH = new minDisHeur();
    private int depth;
    private int numOfMoves;
    public  int evaluation;
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
            minDistH.calculate(N.gameRules);
            N.setValue(minDistH.ownedByUs - minDistH.ownedByThem);
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
    
    public void expandFrontier() throws CloneNotSupportedException{
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
            SearchTreeNode newNode = new SearchTreeNode(S.gameRules.deepCopy());
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
        	minDistH.calculate(S.gameRules);
            S.setValue(minDistH.ownedByUs);
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
    
    public void makeMoveOnRoot(Queen qCurrentPos, Arrow a){
        numOfMoves++;
        root.gameRules.addArrow(a); // adds arrow to be shot
        //makes a move for the queen ours or theirs
        if(qCurrentPos.isOpponent){
            for(Queen Q:root.gameRules.enemy){
            	if(Q.getPreviousRowPosition() == qCurrentPos.getRowPosition() && Q.getPreviousColPosition() == qCurrentPos.getColPosition()) {
                    Q.moveQueen(qCurrentPos.getRowPosition(), qCurrentPos.getColPosition());
            	}
                	
            }
        } else{
            for(Queen Q:root.gameRules.friend){
            	if(Q.getPreviousRowPosition() == qCurrentPos.getRowPosition() && Q.getPreviousColPosition() == qCurrentPos.getColPosition()) {
                    Q.moveQueen(qCurrentPos.getRowPosition(), qCurrentPos.getColPosition());
            	}            		
            }
        }
        root.gameRules.updateAfterMove();
        this.clearTree();
    }
    
    public SearchTreeNode makeMove() throws CloneNotSupportedException{
    	this.expandFrontier();
   	 	this.performAlphaBeta();
        SearchTreeNode bestMove = this.getMoveAfterAlphaBeta();
        this.makeMoveOnRoot(bestMove.getQueen(), bestMove.getArrowShot());
        return bestMove;
    }
    
    private SearchTreeNode getMoveAfterAlphaBeta(){
        int max = Integer.MIN_VALUE;
        SearchTreeNode currentBest = root.getChildren().get(0); // just to initialize currentBest
        
        for(SearchTreeNode S:root.getChildren()){
            if(max <= S.getValue()) {
                max = S.getValue();
                currentBest = S;
            }
        }
        return currentBest;
    }
    
    private void clearTree(){
        depth = 0;
        frontier.clear();
        root.getChildren().clear();
    }


}
