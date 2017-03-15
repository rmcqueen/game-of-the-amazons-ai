package src;

public class Queen extends Tile implements Cloneable{
	private int currentRow;
    private int currentCol;

	public Queen clone() throws CloneNotSupportedException{
        return (Queen) super.clone();
    }
	
    protected boolean isOpponent;

    /**
     *
     * @param currentRow: an integer storing the row location
     * @param currentCol: an integer storing the column location
     * @param newRow: an integer storing the new Queen's row location
     * @param newCol: an integer storing the new Queen's column location
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

    public void setPreviousRowPosition(int row) {
        this.currentRow = row;
    }

    public void setPreviousColPosition(int col) {
        this.currentCol = col;
    }

    public int getPreviousRowPosition() {
        return this.currentRow;
    }

    public int getPreviousColPosition() {
        return this.currentCol;
    }

    public void moveQueen(int row, int col) {
        super.row = row;
        super.col = col;
    }

}