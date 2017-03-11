package ygraphs.ai.smart_fox.games;

public class Queen extends Tile{
	
    protected boolean isOpponent;

    /**
     *
     * @param x: an integer storing the row location
     * @param y: an integer storing the column location
     */
    public Queen(int x, int y) {
    	super(x,y);
    }
    
    public Queen(int x, int y,boolean isOpponent) {
    	super(x,y);
    	this.isOpponent = isOpponent;
    }

    /**
     *
     * @return: the row position of the current Queen
     */
    public int getRowPosition() {
        return super.row;
    }

    /**
     *
     * @return: the column position of the current Queen
     */
    public int getColPosition() {
        return super.col;
    }

    public void moveQueen(int row, int col) {
        super.row = row;
        super.col = col;
    }

    /**
     *
     * @return: an Integer containing the row, and column combined.
     */
    public int getCombinedPosition() {
        return Integer.parseInt(Integer.toString(super.row) + Integer.toString(super.col));
    }


}