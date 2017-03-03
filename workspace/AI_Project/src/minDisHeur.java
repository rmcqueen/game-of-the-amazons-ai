import java.util.LinkedList;
import java.util.Queue;

public class minDisHeur {
	/*
	 * Steps: 
	 * Step 1: Mark all squares which can be reached directly by the original amazons of each side
			by Y which denotes the number of moves necessary to reach this square, Y is initialised
			to 1. A square that is marked for both sides will be denoted neutral.
		Step 2: For each unmarked square, search if there are marked squares to be reached in one
			move. If so mark this square by the marker found and add one to it (Y+1). Again if
			markers for both sides are found the square under consideration will be denoted
			neutral.
		Step 3: Use all newly-marked squares found in step 2 except the neutral ones and mark all the
			squares they can reach in the same way as described in step 1. Keep repeating step 2
			and 3 until either of them cannot put any more markers on the board. 
	 */
	static Tile[][] board = null;
	public static void main(String[] args) {
		Tile[][] board = null;
		int[] bQueens = null;
		int[] wQueens = null;
		int[] arrows = null;
		
		for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                // If tile is empty, check who 'owns it'
                if(board[i][j]==null){
                    findNearestQueen(i, j);
                }
            }
        }
	}
	
	//return value of tile, -nums indicate black value, +nums are white value
	//num is number of moves to get to the tile, 0 means both can reach equally
	 @SuppressWarnings("null")
	public static void findNearestQueen(int row, int col) {
		  	Tile[][] board = null;
		  	int ownedByThem = 0;
		  	int ownedByUs = 0;
	        boolean[][] checked = new boolean[10][10];    // 2d board representation of if a spot has been checked
	        checked[row][col]=true; // Mark starting tile as checked
	        boolean isFound = false;
	        // Initialize the queue
	        Queue<Tile> q = new LinkedList<>();
	        q = addQueenMoves(q, row, col, checked);
	        while(!isFound) {
	            Queue<Tile> tempQ = new LinkedList<>();
	            int index = q.size();
	            //If empty tile is trapped, break
	            if(index==0) {
	                isFound=true;
	                break;
	            }
	            for(int i=0; i<index; i++) {
	                Tile currentTile = q.poll();
	                // If queen is found, increase count and break
	                if(board[currentTile.rowPos][currentTile.colPos]!=null && board[currentTile.rowPos][currentTile.colPos].isQueen) {
	                    isFound=true;
	                    boolean enemyQueen=board[currentTile.rowPos][currentTile.colPos].isOpponent;
	                    boolean contested=false;
	                    // Check if tile is contested
	                    for(Tile shell : q) {
	                        // If a queen is found in the rest of the queue, tile is contested
	                        if(board[shell.rowPos][shell.colPos]!=null && board[shell.rowPos][shell.colPos].isQueen && board[shell.rowPos][shell.colPos].isOpponent==enemyQueen)
	                            contested=true;
	                    }
	                    if(contested)
	                        break;
	                    // Determine who's queen it is, increase count accordingly
	                    if(board[currentTile.rowPos][currentTile.colPos].isOpponent){
	                        ownedByThem++;
	                    }
	                    else {
	                        ownedByUs++;
	                    }
	                    // If element is the last in the queue (ie: current 'layer') then break
	                    break;
	                }
	                // If not a queen, mark location as checked
	                else checked[currentTile.rowPos][currentTile.colPos]=true;
	                // If free square (not arrow), add neighbours of it to tempQ
	                if(board[currentTile.rowPos][currentTile.colPos]==null) {
	                    tempQ = addQueenMoves(tempQ, currentTile.rowPos, currentTile.colPos, checked);
	                }
	            }
	            q = tempQ;
	        }
	    }
	 
	 private static Queue<Tile> addQueenMoves(Queue<Tile> q, int curRow, int curCol, boolean[][] checked) {
		// Legal Moves Left

	        for(int i = 1; curCol-i>=0; i++) {
	            Tile lData = new Tile(curRow,curCol-1);
	            if(!checked[curRow][curCol-1])
	                q.add(lData);
	            // If a Game piece was hit, break adding queen moves
	            if(board[curRow][curCol-i]!=null)
	                break;
	        }

	        // Legal Moves Diagonal Left/Up
	        for(int i = 1; curRow-i>=0&&curCol-i>=0; i++) {
	            Tile lData = new Tile(curRow-i,curCol-i);
	            if(!checked[curRow-i][curCol-i])
	                q.add(lData);
	            // If a Game piece was hit, break adding queen moves
	            if(board[curRow-i][curCol-i]!=null)
	                break;
	        }

	        // Legal Moves Up
	        for(int i = 1; curRow-i>=0; i++) {
	            Tile lData = new Tile(curRow-i,curCol);
	            if(!checked[curRow-i][curCol])
	                q.add(lData);
	            // If a Game piece was hit, break adding queen moves
	            if(board[curRow-i][curCol]!=null)
	                break;
	        }

	        // Legal Moves Diagonal Right/Up
	        for(int i = 1; curRow-i>=0&&curCol+i<=9; i++) {
	            Tile lData = new Tile(curRow-i,curCol+i);
	            if(!checked[curRow-i][curCol+i])
	                q.add(lData);
	            // If a Game piece was hit, break adding queen moves
	            if(board[curRow-i][curCol+i]!=null)
	                break;
	        }

	        // Legal Moves Right
	        for(int i = 1; curCol+i<=9; i++) {
	            Tile lData = new Tile(curRow,curCol+i);
	            if(!checked[curRow][curCol+i])
	                q.add(lData);
	            // If a Game piece was hit, break adding queen moves
	            if(board[curRow][curCol+i]!=null)
	                break;
	        }

	        // Legal Moves Diagonal Right/Down
	        for(int i = 1; curRow+i<=9&&curCol+i<=9; i++) {
	            Tile lData = new Tile(curRow+i,curCol+i);
	            if(!checked[curRow+i][curCol+i])
	                q.add(lData);
	            // If a Game piece was hit, break adding queen moves
	            if(board[curRow+i][curCol+i]!=null)
	                break;
	        }

	        // Legal Moves Down
	        for(int i = 1; curRow+i<=9; i++) {
	            Tile lData = new Tile(curRow+i,curCol);
	            if(!checked[curRow+i][curCol])
	                q.add(lData);
	            // If a Game piece was hit, break adding queen moves
	            if(board[curRow+i][curCol]!=null)
	                break;
	        }

	        //Legal Moves Diagonal Left/Down
	        for(int i = 1; curRow+i<=9&&curCol-i>=0; i++) {
	            Tile lData = new Tile(curRow+i,curCol-i);
	            if(!checked[curRow+i][curCol-i])
	                q.add(lData);
	            // If a Game piece was hit, break adding queen moves
	            if(board[curRow+i][curCol-i]!=null)
	                break;
	        }

	        return q;
	    }
	}
