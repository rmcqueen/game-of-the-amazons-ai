package ygraphs.ai.smart_fox.games;

public class Queen extends Tile implements Cloneable{
	private int previousRow;
    private int previousCol;
    private int qRow;
    private int qCol;

	protected Queen clone() {
        Queen qNew = new Queen(row, col, isOpponent);
        return qNew;
    }
	
    protected boolean isOpponent;

    /**
     *
     * @param x: an integer storing the row location
     * @param y: an integer storing the column location
     */
    public Queen(int x, int y) {
        qRow = x;
        qCol = y;
        previousRow = x;
        previousCol = y;
    }
    
    public Queen(int x, int y, boolean isOpponent) {
    	super(x,y);
    	qRow = x;
        qCol = y;
        previousRow = x;
        previousCol = y;
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
        return qCol;
    }

    public void setPreviousRowPosition(int row) {
        previousRow = row;
    }

    public void setPreviousColPosition(int col) {
        previousCol = col;
    }

    public int getPreviousRowPosition() {
        return previousRow;
    }

    public int getPreviousColPosition() {
        return previousCol;
    }

    public void moveQueen(int row, int col) {
        setPreviousRowPosition(qRow);
        setPreviousColPosition(qCol);
        qRow = row;
        qCol = col;
    }

    public int[] combinedMove(int row, int col) {
        int[] move = new int[2];
        move[0] = row;
        move[1] = col;
        return move;
    }

}