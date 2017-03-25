package ygraphs.ai.smart_fox.games;

import java.util.ArrayList;

public class SuccessorHeuristicFunction {
	public int count = 0;

	public ArrayList<SearchTreeNode> getSuccessors(GameRules state, boolean us) {
        ArrayList<SearchTreeNode> successors = new ArrayList<SearchTreeNode>();
        Queen[] queens;
        //check to see whose move it is and make the appropriate successors
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

            // For each queen move
            for(int j = 0; j < currentQueenMoves.size(); j++) {
                // Temp board with moved queen
                GameRules tempBoard = state.deepCopy();
                tempBoard.friend[i].row = currentQueenMoves.get(j).row;
                tempBoard.friend[i].col = currentQueenMoves.get(j).col;
				tempBoard.friend[i].previousRow = currentQueenMoves.get(j).previousRow;
				tempBoard.friend[i].previousCol = currentQueenMoves.get(j).previousCol;
                tempBoard.updateAfterMove();

                // For each arrow shot at that locations
                ArrayList<Arrow> legalArrowMoves = new ArrayList<>();
                
                // Add legal arrow shots from given position
                for(Arrow a : tempBoard.getArrowMoves(currentQueenMoves.get(j).row, currentQueenMoves.get(j).col)) {
                	legalArrowMoves.add(a.clone());
                }

                for(int k = 0; k < legalArrowMoves.size(); k++) {
                	GameRules newState = tempBoard.deepCopy();
                    newState.addArrow(legalArrowMoves.get(k));
                    newState.updateAfterMove();
                    // Make new SearchTreeNodes
					SearchTreeNode S = new SearchTreeNode(newState, currentQueenMoves.get(j), legalArrowMoves.get(k), 0);
					successors.add(S);
					count++;
                }
            }
        }
        return successors;
    }
}
