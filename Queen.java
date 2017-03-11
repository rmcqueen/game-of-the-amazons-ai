public class Queen {
    protected int rowPosition;
    protected int colPosition;
    protected boolean isFriend;
    protected boolean isOpponent;

    /**
     *
     * @param x: an integer storing the row location
     * @param y: an integer storing the column location
     */
    public Queen(int x, int y) {
        this.rowPosition = x;
        this.colPosition = y;
    }

    /**
     *
     * @return: the row position of the current Queen
     */
    public int getRowPosition() {
        return this.rowPosition;
    }

    /**
     *
     * @return: the column position of the current Queen
     */
    public int getColPosition() {
        return this.colPosition;
    }

    public void moveQueen(int row, int col) {
        this.rowPosition = row;
        this.colPosition = col;
    }

    /**
     *
     * @return: an Integer containing the row, and column combined.
     */
    public int getCombinedPosition() {
        return Integer.parseInt(Integer.toString(this.rowPosition) + Integer.toString(this.colPosition));
    }


}