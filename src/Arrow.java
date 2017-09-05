package ygraphs.ai.smart_fox.games;

public class Arrow extends Tile implements Cloneable {
	protected Arrow clone() {
        Arrow aNew = new Arrow(row, col);
        return aNew;
    }
	
	public Arrow(int i, int j) {
		super(i, j);
	}

	public int getColPosition() {
        return super.col;
    }

    public int getRowPosition() {
        return super.row;
    }

    public int[] combinedMove(int row, int col) {
        int[] move = new int[2];
        move[0] = row;
        move[1] = col;
        return move;
    }

}
