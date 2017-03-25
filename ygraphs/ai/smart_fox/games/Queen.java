package ygraphs.ai.smart_fox.games;

public class Queen extends Tile implements Cloneable{
    public int previousRow;
    public int previousCol;
    public int qRow;
    public int qCol;
    protected boolean isOpponent;

	protected Queen clone() {
        Queen qNew = new Queen(row, col, isOpponent);
        return qNew;
    }
	


    public Queen(int x, int y) {
        super(x, y);
        previousRow = x;
        previousCol = y;
    }
    /**
     *
     * @param x: an integer storing the row location
     * @param y: an integer storing the column location
     * @param isOpponent a boolean indicating if the Queen is an opponent or not
     */

    public Queen(int x, int y, boolean isOpponent) {
        super(x, y);
        this.previousRow = x;
        this.previousCol = y;
        this.qRow = x;
        this.qCol =  y;
    	this.isOpponent = isOpponent;
    }

    /**
     *
     * @return: the row position of the current Queen
     */
    public int getRowPosition() {
        return qRow;
    }

    /**
     *
     * @return: the column position of the current Queen
     */
    public int getColPosition() {
        return this.qCol;
    }

    public void moveQueen(int row, int col) {
        this.previousRow = qRow;
        this.previousCol = qCol;
        this.qRow = row;
        this.qCol = col;
        super.row = row;
        super.col = col;
    }

    public int[] combinedMove(int row, int col) {
        int[] move = new int[2];
        move[0] = row;
        move[1] = col;
        return move;
    }

}