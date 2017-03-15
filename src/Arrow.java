package src;

public class Arrow extends Tile implements Cloneable{
	
	public Arrow clone() throws CloneNotSupportedException{
        return (Arrow) super.clone();
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

}
