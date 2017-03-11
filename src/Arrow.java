package src;

public class Arrow extends Tile{

	public Arrow(int i, int j) {
		super(i, j);
	}

	public int getColPosition() {
        return super.col;
    }

    public int getRowPosition() {
        return super.row;
    }

}
