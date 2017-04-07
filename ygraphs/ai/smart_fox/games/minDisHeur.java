package ygraphs.ai.smart_fox.games;

import java.util.LinkedList;
import java.util.Queue;

public class minDisHeur {
    Tile[][] board = null;
    GameRules bl = null;
    Queen[] queens = new Queen[8];
    public int ownedByThem = 0;
    public int ownedByUs = 0;

    public minDisHeur() {
        board = null;
        bl = null;
        queens = new Queen[8];
        ownedByThem = 0;
        ownedByUs = 0;
    }

    /**
     *
     * @param b: the current GameRules board
     */
    public void calculate(GameRules b) {
        bl = b;
        board = bl.board;
        // queens <- all queens (friendly and enemy)
        for (int i = 0; i < 8; i++) {
            if (i < 4) {
                queens[i] = b.getFriend()[i];
            } else {
                queens[i] = b.getEnemy()[i-4];
            }
        }
        ownedByUs = 0;
        ownedByThem = 0;

        // For every tile in the board
        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                // If tile is empty, check who 'owns it'
                if (bl.board[i][j] == null) {
                    findNearestQueen(i, j);
                }
            }
        }
    }


    /**
     *
     * @param row: the row of the current Tile
     * @param col: the column of the current Tile
     */
    public void findNearestQueen(int row, int col) {
        boolean[][] checked = new boolean[10][10];
        checked[row][col] = true;
        boolean isFound = false;

        Queue<Tile> q = new LinkedList<>();
        // All moves a queen could make to reach this tile
        q = addQueenMoves(q, row, col, checked);
        while (!isFound) {
            Queue<Tile> tempQ = new LinkedList<>();
            int index = q.size();
            // No valid moves, tile blocked in
            if (index == 0) {
                isFound = true;
                break;
            }
            // Check every move possible from current tile
            for (int i = 0; i < index; i++) {
                Tile currentTile = (Tile) q.poll();


                // Current tile has queen here
                if ((board[currentTile.row][currentTile.col] != null) && ((board[currentTile.row][currentTile.col] instanceof Queen))) {
                    isFound = true;
                    boolean enemyQueen = ((Queen) board[currentTile.row][currentTile.col]).isOpponent;
                    boolean contested = false;

                    // If queen found in q, queen is 1 move away, checks if opposing queens 1 move away
                    for (Tile shell : q)
                    {
                        // Opposing queen found 1 move away, tile contested
                        if ((board[shell.row][shell.col] != null) && (board[shell.row][shell.col] instanceof Queen) && !(((Queen) board[shell.row][shell.col]).isOpponent == enemyQueen))
                            contested = true;
                    }
                    // Owned by no one
                    if (contested) {
                        break;
                    }
                    if (((Queen) board[currentTile.row][currentTile.col]).isOpponent) {
                        ownedByThem++;
                    } else {
                        ownedByUs++;
                    }
                    break;
                } else {
                    checked[currentTile.row][currentTile.col] = true;
                }

                // Tile has no arrow or queen
                if (board[currentTile.row][currentTile.col] == null) {
                    tempQ = addQueenMoves(tempQ, currentTile.row, currentTile.col, checked);
                }
            }
            q = tempQ;
        }
    }


    /**
     *
     * @param q: the current Queue
     * @param curRow: the current row of the board
     * @param curCol: the current column of the board
     * @param checked: a 2D array of booleans determining whether or not the current Tile has been checked
     * @return: A Queue holding the possible places to move
     */
    public Queue<Tile> addQueenMoves(Queue<Tile> q, int curRow, int curCol, boolean[][] checked) {
        for (int i = 1; curCol - i >= 0; i++) {
            Tile lData = new Tile(curRow, curCol - i);
            if (checked[curRow][(curCol - i)] == false) {
                q.add(lData);
            }
            if (board[curRow][(curCol - i)] != null) {
                break;
            }
        }

        for (int i = 1; (curRow - i >= 0) && (curCol - i >= 0); i++) {
            Tile lData = new Tile(curRow - i, curCol - i);
            if (checked[(curRow - i)][(curCol - i)] == false) {
                q.add(lData);
            }
            if (board[(curRow - i)][(curCol - i)] != null) {
                break;
            }
        }

        for (int i = 1; curRow - i >= 0; i++) {
            Tile lData = new Tile(curRow - i, curCol);
            if (checked[(curRow - i)][curCol] == false) {
                q.add(lData);
            }
            if (board[(curRow - i)][curCol] != null) {
                break;
            }
        }

        for (int i = 1; (curRow - i >= 0) && (curCol + i <= 9); i++) {
            Tile lData = new Tile(curRow - i, curCol + i);
            if (checked[(curRow - i)][(curCol + i)] == false) {
                q.add(lData);
            }
            if (board[(curRow - i)][(curCol + i)] != null) {
                break;
            }
        }

        for (int i = 1; curCol + i <= 9; i++) {
            Tile lData = new Tile(curRow, curCol + i);
            if (checked[curRow][(curCol + i)] == false) {
                q.add(lData);
            }
            if (board[curRow][(curCol + i)] != null) {
                break;
            }
        }

        for (int i = 1; (curRow + i <= 9) && (curCol + i <= 9); i++) {
            Tile lData = new Tile(curRow + i, curCol + i);
            if (checked[(curRow + i)][(curCol + i)] == false) {
                q.add(lData);
            }
            if (board[(curRow + i)][(curCol + i)] != null) {
                break;
            }
        }

        for (int i = 1; curRow + i <= 9; i++) {
            Tile lData = new Tile(curRow + i, curCol);
            if (checked[(curRow + i)][curCol] == false) {
                q.add(lData);
            }
            if (board[(curRow + i)][curCol] != null) {
                break;
            }
        }

        for (int i = 1; (curRow + i <= 9) && (curCol - i >= 0); i++) {
            Tile lData = new Tile(curRow + i, curCol - i);
            if (checked[(curRow + i)][(curCol - i)] == false) {
                q.add(lData);
            }
            if (board[(curRow + i)][(curCol - i)] != null) {
                break;
            }
        }
        return q;
    }
}
