package ygraphs.ai.smart_fox.games;

import java.util.ArrayList;

public class SuccessorHeuristicFunction {
	public int count = 0;

    /**
     * getSuccessors: assigns all possible successor nodes to a given state, and returns an ArrayList with all of these
     * SearchTreeNodes
     * @param state: a GameRules object containing the current state of the board
     * @param us: a boolean indicating whether or not it is our move
     * @return: an ArrayList storing SearchTreeNodes of all possible moves from a given state
     */
	public ArrayList<SearchTreeNode> getSuccessors(GameRules state, boolean us) {
        ArrayList<SearchTreeNode> successors = new ArrayList<SearchTreeNode>();
        Queen[] queens;
        // Check to see whose move it is and make the appropriate successors
        if(us) {
            queens = state.getFriend();
        } else{
            queens = state.getEnemy();
        }

        for(int i = 0; i < queens.length; i++) {
        	ArrayList<Queen> currentQueenMoves = new ArrayList<>();
			// Make a deep copy of all queen moves at specific queen
			for(Queen q : state.getLegalMoves(queens[i])) {
                q.previousRow = queens[i].row;
                q.previousCol = queens[i].col;
				currentQueenMoves.add(q);
			}

            // For each queen move, copy its location to the new GameRules board
            for(int j = 0; j < currentQueenMoves.size(); j++) {
                // Temp board with moved queen
                GameRules tempBoard = state.deepCopy();
                if(us) {
                    tempBoard.friend[i].row = currentQueenMoves.get(j).row;
                    tempBoard.friend[i].col = currentQueenMoves.get(j).col;
                    tempBoard.friend[i].previousRow = currentQueenMoves.get(j).previousRow;
                    tempBoard.friend[i].previousCol = currentQueenMoves.get(j).previousCol;
                }
                else {
                    tempBoard.enemy[i].row = currentQueenMoves.get(j).row;
                    tempBoard.enemy[i].col = currentQueenMoves.get(j).col;
                    tempBoard.enemy[i].previousRow = currentQueenMoves.get(j).previousRow;
                    tempBoard.enemy[i].previousCol = currentQueenMoves.get(j).previousCol;
                }

                // Update the board with the new Queen locations
                tempBoard.updateAfterMove();

                /* Find all possible arrow shots the current Queen could make from their current location
                    and add it to an ArrayList
                 */
                ArrayList<Arrow> legalArrowMoves = new ArrayList<>();
                for(Arrow a : tempBoard.getArrowMoves(currentQueenMoves.get(j).row, currentQueenMoves.get(j).col)) {
                    legalArrowMoves.add(a.clone());
                }

                /* Iterate over each legal arrow move that the current Queen has, and create a new SearchTreeNode
                    to use for later analysis
                 */
                for(int k = 0; k < legalArrowMoves.size(); k++) {
                    // Create a new state where this SearchTreeNode will exist
                	GameRules newState = tempBoard.deepCopy();
                	// Add this arrow shot to the new state
                    newState.addArrow(legalArrowMoves.get(k));
                    newState.updateAfterMove();
                    // Make new SearchTreeNode
					SearchTreeNode S = new SearchTreeNode(newState, currentQueenMoves.get(j), legalArrowMoves.get(k), 0);
					successors.add(S);
					count++;
                }
            }
        }
        return successors;
    } // end of getSuccessors
} // end of SuccessorHeuristicFunction
