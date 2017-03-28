package ygraphs.ai.smart_fox.games;
import java.util.ArrayList;

public class SearchTree {
    private SearchTreeNode root;
    private minDisHeur minDistH = new minDisHeur();
    private int depth;
    private int numOfMoves;
    public int evaluation;
    private ArrayList<SearchTreeNode> frontier = new ArrayList<>();

    /**
     * SearchTree constructor
     * @param node: a SearchTreeNode to be added to the SearchTree
     */
    public SearchTree(SearchTreeNode node) {
        this.root = node;
        this.depth = 0;
        this.evaluation = 0;
    }

    /**
     * calculateDepth: calculates the depth we are to get SearchTreeNode children from
     * Assigns our current depth in the SearchTree
     */
    public void calculateDepth() {
        SearchTreeNode n = this.root;
        int tempDepth = 0;
        while (n != null) {
            if (!n.getChildren().isEmpty()) {
                n = n.getChildren().get(0);
            } else {
                break;
            }
            tempDepth++;
        }
        this.depth = tempDepth;
    }

    /**
     * AlphaBeta: performs AlphaBeta search on a SearchTreeNode root
     * @param N: the root SearchTreeNode to be evaluated
     * @param D: the depth we are evaluating at
     * @param alpha: an Integer storing negative infinity, we attempt to maximize this in the function
     * @param beta: an Integer storing positive infinity, we attempt to minimize this in the function
     * @param maxPlayer: boolean indicating whether or not we're attempting to maximize alpha
     * @return: an int representing the weighting of the move
     */
    private int AlphaBeta(SearchTreeNode N, int D, int alpha, int beta, boolean maxPlayer) {
        if (D == 0 || N.getChildren().size() == 0) {
            evaluation++;
            minDistH.calculate(N.gameRules);
            N.setValue(minDistH.ownedByUs - minDistH.ownedByThem);
            int val = N.getValue();
            return val;
        }

        if (maxPlayer) {
            int V = Integer.MIN_VALUE;
            for (SearchTreeNode S : N.getChildren()) {
                V = Math.max(V, AlphaBeta(S, D - 1, alpha, beta, false));
                alpha = Math.max(alpha, V);
                if (beta <= alpha) {
                    break;
                }
            }
            N.setValue(V);
            return V;

        } else {
            int V = Integer.MAX_VALUE;
            for (SearchTreeNode S : N.getChildren()) {
                V = Math.min(V, AlphaBeta(S, D - 1, alpha, beta, true));
                beta = Math.min(beta, V);
                if (beta <= alpha)
                    break;
            }
            N.setValue(V);
            return V;
        }
    }

    /**
     * expandFrontier: Depending on if the depth is even or odd, add the successor SearchTreeNodes
     * as friendly/enemy SearchTreeNodes. If we have an even depth, then we know we are evaluating our nodes
     */
    public void expandFrontier() {
        ArrayList<SearchTreeNode> newFrontier = new ArrayList<>();
        // See if we're at the very first root node
        if (depth != 0) {
            if (depth % 2 == 0) {
                for (SearchTreeNode S : frontier)
                    newFrontier.addAll(S.setSuccessors(true));
            } else {
                for (SearchTreeNode S : frontier)
                    newFrontier.addAll(S.setSuccessors(false));
            }
        } else {
            newFrontier.addAll(root.setSuccessors(true));
        }

        // Clear the old frontier, and add in the new SearchTreeNodes to use
        frontier.clear();
        for (SearchTreeNode S : newFrontier) {
            // deepCopy in order to keep Object relationships
            SearchTreeNode newNode = new SearchTreeNode(S.gameRules.deepCopy());
            frontier.add(newNode);
        }
        depth++;
    }

    /**
     * performAlphaBeta: Calculates how far down into the SearchTree we will go,
     * and whether or not we're trying to maximize our move (we always are, so set as true)
     */
    public void performAlphaBeta() {
        evaluation = 0;
        calculateDepth();
        AlphaBeta(root, depth, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
    }

    /**
     * trimFrontier: performs AB pruning to reduce the number of SearchTreeNodes evaluated
     */
    public void trimFrontier() {
        int avg = 0;
        // Get the average value from all SearchTreeNodes in the frontier
        for (SearchTreeNode S : frontier) {
            minDistH.calculate(S.gameRules);
            S.setValue(minDistH.ownedByUs);
            avg += S.getValue();
        }
        if (frontier.size() != 0)
            avg = avg / frontier.size();

        // If the nodes are below the average value, "cut" them
        ArrayList<SearchTreeNode> toRemove = new ArrayList<>();
        for (SearchTreeNode S : frontier) {
            if (S.getValue() < avg) {
                toRemove.add(S);
            }
        }

        // "cuts" the SearchTreeNodes that were below the average value
        for (SearchTreeNode S : toRemove) {
            frontier.remove(S);
        }
    }

    /**
     * makeMoveOnRoot: Moves the queen from its initial position (root), to its new position
     * @param qCurrentPos: a Queen object containing the new best Queen move
     * @param a: an Arrow object that is associated with the best Queen move
     */
    public void makeMoveOnRoot(Queen qCurrentPos, Arrow a) {
        numOfMoves++;
        // Adds an arrow to the board
        root.gameRules.addArrow(a);

        // Check whether or not the queen is the opponent's, or ours
        if (qCurrentPos.isOpponent) {
            for (Queen Q : root.gameRules.enemy) {
                if (Q.row == qCurrentPos.previousRow && Q.col == qCurrentPos.previousCol) {
                    Q.moveQueen(qCurrentPos.row, qCurrentPos.col);
                }

            }
        } else {
            for (Queen Q : root.gameRules.friend) {
                if (Q.row == qCurrentPos.previousRow && Q.col == qCurrentPos.previousCol) {
                    Q.moveQueen(qCurrentPos.row, qCurrentPos.col);
                }
            }
        }
        root.gameRules.updateAfterMove();
        this.clearTree();
    }

    /**
     * makeMove: performs expansion/trimming, alpha beta, and performs our move
     * @return: SearchTreeNode containing our best possible Queen move
     */
    public SearchTreeNode makeMove() {
        /* "Thresholding" based off the number of moves we have
            in order to increase our likelyhood of winning
         */
        if(numOfMoves <= 20) {
            this.expandFrontier();
            this.trimFrontier();
        }

        else if(numOfMoves > 20 && numOfMoves <= 30) {
            this.expandFrontier();
            this.trimFrontier();
            this.expandFrontier();

        }
        else if(numOfMoves > 30 && numOfMoves <= 45) {
            this.expandFrontier();
            this.trimFrontier();
            this.expandFrontier();
            this.trimFrontier();

        }
        else if(numOfMoves > 45 && numOfMoves <= 60) {
            this.expandFrontier();
            this.trimFrontier();
            this.expandFrontier();
            this.trimFrontier();
            this.expandFrontier();
            this.trimFrontier();
        }
        else if(numOfMoves > 60) {
            this.expandFrontier();
            this.expandFrontier();
            this.trimFrontier();
            this.expandFrontier();
            this.trimFrontier();
            this.expandFrontier();
            this.trimFrontier();

        }

        this.performAlphaBeta();
        SearchTreeNode bestMove = this.getMoveAfterAlphaBeta();
        this.makeMoveOnRoot(bestMove.getQueen(), bestMove.getArrowShot());
        return bestMove;
    } // end of makeMove

    private SearchTreeNode getMoveAfterAlphaBeta() {
        int max = Integer.MIN_VALUE;
        SearchTreeNode best = null;
        ArrayList<SearchTreeNode> currentBest = new ArrayList<>(); // just to initialize currentBest
        for (SearchTreeNode S : root.getChildren()) {
            if (max < S.getValue()) {
                max = S.getValue();
                currentBest.add(S);
            }
        }
        if (currentBest.size() > 1) {
            best = currentBest.get((currentBest.size() - 1));
        } else {
            try {
                best = currentBest.get(0);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Goal state reached!");
            }
        }

        return best;
    } // end of getMoveAfterAlphaBeta

    private void clearTree() {
        depth = 0;
        frontier.clear();
        root.getChildren().clear();
    } // end of clearTree

} // end of SearchTree
