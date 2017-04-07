package ygraphs.ai.smart_fox.games;

public class Queen extends Tile implements Cloneable{
    public int previousRow;
    public int previousCol;
    protected boolean isOpponent;

    /**
     * clone: creates a Queen object at its new location
     * @return: Queen object with its new location
     */
	protected Queen clone() {
        Queen qNew = new Queen(row, col, isOpponent);
        return qNew;
    }


    /**
     * Queen constructor
     * @param x: an int containing the row coordinate of the Queen
     * @param y: an int containing the column coordinate of the Queen
     */
    public Queen(int x, int y) {
        super(x, y);
        previousRow = x;
        previousCol = y;
    }

    /**
     *
     * @param x: an int storing the row location
     * @param y: an int storing the column location
     * @param isOpponent a boolean indicating if the Queen is an opponent or not
     */

    public Queen(int x, int y, boolean isOpponent) {
        super(x, y);
        this.previousRow = x;
        this.previousCol = y;
    	this.isOpponent = isOpponent;
    }

    /**
     * moveQueen: places a Queen object at its new location
     * @param row: an int containing the row position of the Queen
     * @param col: an int containing the column position of the Queen
     */
    public void moveQueen(int row, int col) {
        this.previousRow = qRow;
        this.previousCol = qCol;
        super.row = row;
        super.col = col;
    }

    /**
     * combinedMove: creates an int array storing the row, and column of a Queen in order to pass it
     * to the Game of the Amazons server
     * @param row: an int containing a Queen's row position
     * @param col: an int containing Queen's column position
     * @return: an int array containing the row, and column of a Queen
     */
    public int[] combinedMove(int row, int col) {
        int[] move = new int[2];
        move[0] = row;
        move[1] = col;
        return move;
    } // end of combinedMove
} // end of Queen
