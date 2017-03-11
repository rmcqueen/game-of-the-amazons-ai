package cosc332_server_test;

import java.util.LinkedList;
import java.util.Queue;










public class minDisHeur
{
  public minDisHeur() {}
  
  static Tile[][] board = null;
  
  public static void main(String[] args) { Tile[][] board = null;
    int[] bQueens = null;
    int[] wQueens = null;
    int[] arrows = null;
    
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++)
      {
        if (board[i][j] == null) {
          findNearestQueen(i, j);
        }
      }
    }
  }
  


  public static void findNearestQueen(int row, int col)
  {
    Tile[][] board = null;
    int ownedByThem = 0;
    int ownedByUs = 0;
    boolean[][] checked = new boolean[10][10];
    checked[row][col] = 1;
    boolean isFound = false;
    
    Queue<Tile> q = new LinkedList();
    q = addQueenMoves(q, row, col, checked);
    while (!isFound) {
      Queue<Tile> tempQ = new LinkedList();
      int index = q.size();
      
      if (index == 0) {
        isFound = true;
        break;
      }
      for (int i = 0; i < index; i++) {
        Tile currentTile = (Tile)q.poll();
        
        if ((board[rowPos][colPos] != null) && (rowPos][colPos].isQueen)) {
          isFound = true;
          boolean enemyQueen = rowPos][colPos].isOpponent;
          boolean contested = false;
          
          for (Tile shell : q)
          {
            if ((board[rowPos][colPos] != null) && (rowPos][colPos].isQueen) && (rowPos][colPos].isOpponent == enemyQueen))
              contested = true;
          }
          if (contested) {
            break;
          }
          if (rowPos][colPos].isOpponent) {
            ownedByThem++;
            break;
          }
          ownedByUs++;
          

          break;
        }
        
        checked[rowPos][colPos] = 1;
        
        if (board[rowPos][colPos] == null) {
          tempQ = addQueenMoves(tempQ, rowPos, colPos, checked);
        }
      }
      q = tempQ;
    }
  }
  

  private static Queue<Tile> addQueenMoves(Queue<Tile> q, int curRow, int curCol, boolean[][] checked)
  {
    for (int i = 1; curCol - i >= 0; i++) {
      Tile lData = new Tile(curRow, curCol - 1);
      if (checked[curRow][(curCol - 1)] == 0) {
        q.add(lData);
      }
      if (board[curRow][(curCol - i)] != null) {
        break;
      }
    }
    
    for (int i = 1; (curRow - i >= 0) && (curCol - i >= 0); i++) {
      Tile lData = new Tile(curRow - i, curCol - i);
      if (checked[(curRow - i)][(curCol - i)] == 0) {
        q.add(lData);
      }
      if (board[(curRow - i)][(curCol - i)] != null) {
        break;
      }
    }
    
    for (int i = 1; curRow - i >= 0; i++) {
      Tile lData = new Tile(curRow - i, curCol);
      if (checked[(curRow - i)][curCol] == 0) {
        q.add(lData);
      }
      if (board[(curRow - i)][curCol] != null) {
        break;
      }
    }
    
    for (int i = 1; (curRow - i >= 0) && (curCol + i <= 9); i++) {
      Tile lData = new Tile(curRow - i, curCol + i);
      if (checked[(curRow - i)][(curCol + i)] == 0) {
        q.add(lData);
      }
      if (board[(curRow - i)][(curCol + i)] != null) {
        break;
      }
    }
    
    for (int i = 1; curCol + i <= 9; i++) {
      Tile lData = new Tile(curRow, curCol + i);
      if (checked[curRow][(curCol + i)] == 0) {
        q.add(lData);
      }
      if (board[curRow][(curCol + i)] != null) {
        break;
      }
    }
    
    for (int i = 1; (curRow + i <= 9) && (curCol + i <= 9); i++) {
      Tile lData = new Tile(curRow + i, curCol + i);
      if (checked[(curRow + i)][(curCol + i)] == 0) {
        q.add(lData);
      }
      if (board[(curRow + i)][(curCol + i)] != null) {
        break;
      }
    }
    
    for (int i = 1; curRow + i <= 9; i++) {
      Tile lData = new Tile(curRow + i, curCol);
      if (checked[(curRow + i)][curCol] == 0) {
        q.add(lData);
      }
      if (board[(curRow + i)][curCol] != null) {
        break;
      }
    }
    
    for (int i = 1; (curRow + i <= 9) && (curCol - i >= 0); i++) {
      Tile lData = new Tile(curRow + i, curCol - i);
      if (checked[(curRow + i)][(curCol - i)] == 0) {
        q.add(lData);
      }
      if (board[(curRow + i)][(curCol - i)] != null) {
        break;
      }
    }
    return q;
  }
}
