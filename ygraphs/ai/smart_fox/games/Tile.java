package ygraphs.ai.smart_fox.games;

//General parent class for queens and arrows, just holds position
public class Tile { 

	protected int row;
    protected int col;
  
  public Tile(int i, int j) { 
	this.row = i;
    this.col = j;
  }

}
