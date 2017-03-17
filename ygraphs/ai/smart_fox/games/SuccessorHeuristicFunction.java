package ygraphs.ai.smart_fox.games;

import java.util.ArrayList;

public class SuccessorHeuristicFunction {
	public int count = 0;

	public ArrayList<GameRules> getSuccessors(GameRules state) throws CloneNotSupportedException {
		ArrayList<GameRules> successors = new ArrayList<GameRules>();
		Queen[] queens = state.getFriend();

		int stateCount = 0;

		//        For each queen
		for(int i=0; i<queens.length; i++) {
			ArrayList<Queen> curQueenMoves = new ArrayList<>();
			// Make a deep copy of all queen moves at specific queen
			for(Queen q : state.getLegalMoves(queens[i])) {
				curQueenMoves.add(q);
				//System.out.println("Queen: "+i+" has move "+md.rowPos+"(row), "+md.colPos+"(col)");
			}

			//                For each possible queen move
			for(int j=0; j<curQueenMoves.size(); j ++) {

				GameRules placeHolderBoard = state.deepCopy();
				ArrayList<Arrow> numshots = new ArrayList<>();

				Queen[] placeholderBoardQueens = placeHolderBoard.getFriend();
				placeholderBoardQueens[i].moveQueen(curQueenMoves.get(j).row, curQueenMoves.get(j).col);
				//                    Update board
				placeHolderBoard.updateAfterMove();

				numshots = placeHolderBoard.getArrowMoves(placeHolderBoard.getFriend()[i].row,placeHolderBoard.getFriend()[i].col);

				//for each arrow shot of a queen
				for(int k=0; k<numshots.size(); k++) {

					GameRules newBoard = state.deepCopy();
					//                    Perform move
					Queen[] newBoardQueens = newBoard.getFriend();
					newBoardQueens[i].moveQueen(curQueenMoves.get(j).row, curQueenMoves.get(j).col);
					//                    Update board
					newBoard.updateAfterMove();

					//                    For each possible arrow shot
					ArrayList<Arrow> legalArrowShots = new ArrayList<>();
					legalArrowShots = newBoard.getArrowMoves(newBoardQueens[i].row, newBoardQueens[i].col);
					//                        Add shot to board
					//                    System.out.println("The legal arrow shot is: "+legalArrowShots.get(k).rowPos+"(row), "+legalArrowShots.get(k).colPos+"(col)");
					newBoard.addArrow(legalArrowShots.get(k).row, legalArrowShots.get(k).col);

					//                        PRINT THE STATE
					//                    System.out.println("\nQueen "+i+" at ("+queens[i].getRowPos()+", "+queens[i].getColumnPos()+") moving to ("+ curQueenMoves.get(j).rowPos + ", " + curQueenMoves.get(j).colPos+")");
					//                    System.out.println("State Generated: Arrow Throw #"+k+"\nQueen: " + i + " || Location: (" + curQueenMoves.get(j).rowPos + ", " + curQueenMoves.get(j).colPos + ") || Arrow: (" + legalArrowShots.get(k).rowPos + ", " +legalArrowShots.get(k).colPos+")");


					//                        Update board
					newBoard.updateAfterMove();

					//                        Add copied board with move made to successors
					stateCount ++;
					successors.add(newBoard);
					count++;
					//                    int size = newBoard.arrows.size();
					//                    newBoard.arrows.set(size-1,null);
				}
			}
		}
		System.out.println("Total States: " + stateCount);
		return successors;
	}

	public ArrayList<SearchTreeNode> getSuccessors(GameRules state, boolean us) throws CloneNotSupportedException {
        ArrayList<SearchTreeNode> successors = new ArrayList<SearchTreeNode>();
        Queen[] queens;
        //check to see whose move it is and make the appropriate successors
        if(us) {
            queens = state.getFriend();
        } else{
            queens = state.getEnemy();
        }

        int stateCount = 0;

        for(int i=0; i<queens.length; i++) {
        	ArrayList<Queen> currentQueenMoves = new ArrayList<>();
			// Make a deep copy of all queen moves at specific queen
			for(Queen q : state.getLegalMoves(queens[i])) {
				currentQueenMoves.add(q);
				//System.out.println("Queen: "+i+" has move "+md.rowPos+"(row), "+md.colPos+"(col)");
			}

            // For each queen move
            for(int j = 0; j< currentQueenMoves.size(); j++) {
                // Temp board with moved queen
                GameRules tempBoard = state.deepCopy();
                // System.out.println("Deep Copy is: \n"+tempBoard.toString());
                tempBoard.friend[i].row = currentQueenMoves.get(j).row;
                tempBoard.friend[i].col = currentQueenMoves.get(j).col;
                tempBoard.updateAfterMove();

                // For each arrow shot at that locations
                ArrayList<Arrow> legalArrowMoves = new ArrayList<>();
                
                // Add legal arrow shots from given position
                for(Arrow a : tempBoard.getArrowMoves(currentQueenMoves.get(j).row,currentQueenMoves.get(j).col)) {
                	legalArrowMoves.add(a.clone());
                }
				
				
                for(int k = 0; k < legalArrowMoves.size(); k++) {
                	GameRules newState = tempBoard.deepCopy();
                    newState.addArrow(legalArrowMoves.get(k));
                    newState.updateAfterMove();
                    stateCount++;

                    SearchTreeNode S = new SearchTreeNode(newState, currentQueenMoves.get(k), legalArrowMoves.get(k), 0);
                    successors.add(S);
                    count++;
                }
            }
        }
        return successors;
    }
}
