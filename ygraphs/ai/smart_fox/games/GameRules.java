package ygraphs.ai.smart_fox.games;

import java.util.ArrayList;

public class GameRules {
    private boolean enemyMove;
    protected Tile[][] board = new Tile[10][10];
    protected Queen[] enemy;
    protected Queen[] friend;
    protected ArrayList<Arrow> arrows;
    private ArrayList<Queen> legalQueenMoves;

    /**
     * Constructor to start the game, and create the initial board layout
     * @param start: boolean indicating whether or not we are the white player
     */
    protected GameRules(boolean start) {
        // Our move first
        if(start) {
            board = new Tile[][] {
                    { null, null, null, new Queen(0, 3, true), null, null, new Queen(0, 6, true), null, null, null },
                    { null, null, null, null, null, null, null, null, null, null },
                    { null, null, null, null, null, null, null, null, null,	null },
                    { new Queen(3, 0, true), null, null, null, null, null, null, null, null, new Queen(3, 9, true) },
                    { null, null, null, null, null, null, null, null, null,	null },
                    { null, null, null, null, null, null, null, null, null,	null },
                    { new Queen(6, 0, false), null, null, null, null, null, null, null, null, new Queen(6, 9, false) },
                    { null, null, null, null, null, null, null, null, null,	null },
                    { null, null, null, null, null, null, null, null, null,	null },
                    { null, null, null, new Queen(9, 3, false), null, null, new Queen(9, 6, false), null, null, null } };

            // enemies is an array of the opponents queens while friendly is our own queens
            enemy = new Queen[] { (Queen) board[0][3], (Queen) board[0][6], (Queen) board[3][0], (Queen) board[3][9] };
            friend = new Queen[] { (Queen) board[6][0], (Queen) board[6][9], (Queen) board[9][3],	(Queen) board[9][6] };

        }
        // Opponent moves first
        else {
            board = new Tile[][] {
                    { null, null, null, new Queen(0, 3, false), null, null, new Queen(0, 6, false), null, null, null },
                    { null, null, null, null, null, null, null, null, null,	null },
                    { null, null, null, null, null, null, null, null, null,	null },
                    { new Queen(3, 0, false), null, null, null, null, null, null, null, null, new Queen(3, 9, false) },
                    { null, null, null, null, null, null, null, null, null,	null },
                    { null, null, null, null, null, null, null, null, null,	null },
                    { new Queen(6, 0, true), null, null, null, null, null, null, null, null, new Queen(6, 9, true) },
                    { null, null, null, null, null, null, null, null, null,	null },
                    { null, null, null, null, null, null, null, null, null,	null },
                    { null, null, null, new Queen(9, 3, true), null, null,	new Queen(9, 6, true), null, null, null } };

            friend = new Queen[] { (Queen) board[0][3], (Queen) board[0][6], (Queen) board[3][0], (Queen) board[3][9] };
            enemy = new Queen[] { (Queen) board[6][0], (Queen) board[6][9], (Queen) board[9][3],	(Queen) board[9][6] };
        }

        /* Instantiate the ArrayLists for legel arrow shots,
           legal queen moves, and update the moves afterwards
         */
        arrows = new ArrayList<>();
        legalQueenMoves = new ArrayList<>();
        updateLegalQueenMoves();
        } // end of constructor

        /**
         * Constructor for GameRules
         * @param enemy: the opponent's queen positions
         * @param friend: the player's queen positions
         * @param arrow: holds the positions of the "stones"
         */
    protected GameRules(Queen[] enemy, Queen[] friend, ArrayList<Arrow> arrow) {
        this.enemy = enemy;
        this.friend = friend;
        this.arrows = arrow;
        updateAfterMove();
    } // end of constructor

    /**
     * @return: an Array consisting of the enemy queens on the board
     */
    protected Queen[] getEnemy() {
        return this.enemy;
    }

    /**
     *
     * @return: an Array consisting of our queens on the board
     */
    protected Queen[] getFriend() {
        return this.friend;
    }


    /**
     * Clones our game board/game state in order to reduce the space used
     * @return: the board with the new positions of queens/arrows
     */
    protected GameRules deepCopy() {
        Queen[] newFriend = new Queen[4];
        Queen[] newEnemy = new Queen[4];
        ArrayList<Arrow> newArrows = new ArrayList<>();
        for(int i = 0; i < newEnemy.length; i++) {
            newFriend[i] = friend[i].clone();
            newEnemy[i] = enemy[i].clone();
        }

        if(arrows != null) {
            for(Arrow a: arrows) {
                if(a != null) {
                    newArrows.add(new Arrow(a.row, a.col));
                }
            }
        }

        GameRules newRules = new GameRules(newEnemy, newFriend, newArrows);
        return newRules;
    }

	/**
     *
     * @param queen: the queen object to check moves for
     * @return: the best legal move a queen can make from it's current position
     * if there are any queens/arrows in the way, break to save on time
     */
    protected ArrayList<Queen> getLegalMoves(Queen queen) {
        ArrayList<Queen> legalMoves = new ArrayList<>();
        int currentRow = queen.row;
        int currentCol = queen.col;

        /*
            Horizontal Movement Checks
         */

        // Legal moves left
        for(int i = 1; currentCol - i >= 0; i++) {
            if(board[currentRow][currentCol-i] == null) {
                legalMoves.add(new Queen(currentRow, currentCol-i));
            }
            else {
                break;
            }
        }

        // Legal moves right
        for(int i = 1; currentCol + i <= 9; i++) {
            if(board[currentRow][currentCol+i] == null) {
                legalMoves.add(new Queen(currentRow, currentCol+i));
            }
            else {
                break;
            }
        }


        /*
            Vertical Movements Checks
         */

        // Legal moves up
        for(int i = 1; currentRow - i >= 0; i++) {
            if(board[currentRow-i][currentCol] == null) {
                legalMoves.add(new Queen(currentRow-i, currentCol));
            }
            else {
                break;
            }
        }

        // Legal moves down
        for(int i = 1; currentRow + i <= 9; i++) {
            if(board[currentRow+i][currentCol] == null) {
                legalMoves.add(new Queen(currentRow+i, currentCol));
            }
            else {
                break;
            }
        }


        /*
            Diagonal Movement Checks
         */


        // Legal moves diagonally left/up
        for(int i = 1; currentRow - i >= 0 && currentCol - i >= 0; i++) {
            if(board[currentRow-i][currentCol-i] == null) {
                legalMoves.add(new Queen(currentRow-i, currentCol-i));
            }
            else {
                break;
            }
        }

        // Legal moves diagonally left/down
        for(int i = 1; currentRow + i <= 9 && currentCol - i >= 0; i++) {
            if(board[currentRow+i][currentCol-i] == null) {
                legalMoves.add(new Queen(currentRow+i, currentCol-i));
            }
            else {
                break;
            }
        }

        // Legal moves diagonally right/up
        for(int i = 1; currentRow - i >= 0 && currentCol + i <= 9; i++) {
            if(board[currentRow-i][currentCol+i] == null) {
                legalMoves.add(new Queen(currentRow-i, currentCol+i));
            }
            else {
                break;
            }
        }

        // Legal moves diagonally right/down
        for(int i = 1; currentRow + i <= 9 && currentCol + i <= 9; i++) {
            if(board[currentRow+i][currentCol+i] == null) {
                legalMoves.add(new Queen(currentRow+i, currentCol+i));
            }
            else {
                break;
            }
        }

    return legalMoves;

    } // end getLegalMoves

    /**
     *
     * @param x: int storing the row of the current queen
     * @param y: int storing the column of the current queen
     * @return: an ArrayList<Arrow> containing the possible moves from the current queen
     * if there are any arrows/queens in the way, break as we know it's blocked
     */
    protected ArrayList<Arrow> getArrowMoves(int x, int y) {
        ArrayList<Arrow> legalArrowMoves = new ArrayList<>();
        int currentRow = x;
        int currentCol = y;

        /*
            Horizontal Movement Checks
         */

        // Legal moves left
        for(int i = 1; currentCol - i >= 0; i++) {
            if(board[currentRow][currentCol-i] == null) {
                legalArrowMoves.add(new Arrow(currentRow, currentCol-i));
            }
            else {
                break;
            }
        }

        // Legal moves right
        for(int i = 1; currentCol + i <= 9; i++) {
            if(board[currentRow][currentCol+i] == null) {
                legalArrowMoves.add(new Arrow(currentRow,currentCol+i));
            }
            else {
                break;
            }
        }

        /*
            Vertical Movement Checks
         */

        // Legal moves up
        for(int i = 1; currentRow - i >= 0; i++) {
            if(board[currentRow-i][currentCol] == null) {
                legalArrowMoves.add(new Arrow(currentRow-i,currentCol));
            }
            else {
                break;
            }
        }

        // Legal moves down
        for(int i = 1; currentRow + i <= 9; i++) {
            if(board[currentRow+i][currentCol] == null) {
                legalArrowMoves.add(new Arrow(currentRow+i,currentCol));
            }
            else {
                break;
            }
        }

        /*
            Diagonal Movement Checks
         */

        // Legal moves diagonal left/up
        for(int i = 1; currentRow - i >= 0 && currentCol - i >= 0; i++) {
            if(board[currentRow-i][currentCol-i] == null) {
                legalArrowMoves.add(new Arrow(currentRow-i,currentCol-i));
            }
            else {
                break;
            }
        }

        // Legal moves diagonal left/down
        for(int i = 1; currentRow + i <= 9 && currentCol - i >= 0; i++) {
            if(board[currentRow+i][currentCol-i] == null) {
                legalArrowMoves.add(new Arrow(currentRow+i,currentCol-i));
            }
            else {
                break;
            }
        }

        // Legal moves diagonal right/up
        for(int i = 1; currentRow - i >= 0 && currentCol + i <= 9; i++) {
            if(board[currentRow-i][currentCol+i] == null) {
                legalArrowMoves.add(new Arrow(currentRow-i,currentCol+i));
            }
            else {
                break;
            }
        }

        // Legal moves diagonal right/down
        for(int i = 1; currentRow + i <= 9 && currentCol + i <= 9; i++) {
            if(board[currentRow+i][currentCol+i] == null) {
                legalArrowMoves.add(new Arrow(currentRow+i,currentCol+i));
            }
            else {
                break;
            }
        }
        return legalArrowMoves;
    } // end of getArrowMoves

    /**
     * Check to see if the enemy has any queens which can move on the board
     * if any can move, break the loop to save on time
     */
    public void canEnemyMove() {
        for(Queen q: enemy) {
            int initialRow = q.row;
            int initialCol = q.col;
            
            if(initialRow - 1 >= 0 && board[initialRow - 1][initialCol] == null){
                enemyMove = true;
                break;
            }

            if(initialRow + 1 <= 9 && board[initialRow + 1][initialCol] == null){
                enemyMove = true;
                break;
            }

            if(initialCol - 1 >= 0 && board[initialRow][initialCol - 1] == null){
                enemyMove = true;
                break;
            }
            if(initialCol + 1 <= 9 && board[initialRow][initialCol + 1] == null){
                enemyMove = true;
                break;
            }

            if((initialRow - 1 >= 0 && initialCol - 1 >= 0) && board[initialRow - 1][initialCol - 1] == null){
                enemyMove = true;
                break;
            }

            if((initialRow + 1 <= 9 && initialCol - 1 >= 0) && board[initialRow + 1][initialCol - 1] == null){
                enemyMove = true;
                break;
            }

            if((initialRow + 1 <= 9 && initialCol + 1 <= 9) && board[initialRow + 1][initialCol + 1] == null){
                enemyMove = true;
                break;
            }
            if((initialRow - 1 >= 0 && initialCol + 1 <= 9) && board[initialRow - 1][initialCol + 1] == null){
                enemyMove = true;
                break;
            }
        }
    } // end of canEnemyMove

    /**
     * Updates each friendly queen's possible moves at the current state
     */
    public void updateLegalQueenMoves() {
        legalQueenMoves.clear();
        for(Queen q: friend) {
            legalQueenMoves.addAll(getLegalMoves(q));
        }
    } // end of updateLegalQueenMoves


    /**
     *
     * @param newArrow: the new Arrow object to add
     */
    protected void addArrow(Arrow newArrow) {
        arrows.add(newArrow);
        updateAfterMove();
    } // end of addArrow

    /**
     * Reset the board
     */
    private void clearBoard() {
        for(int i = 0; i <= 9; i++) {
            for(int j = 0; j <= 9; j++) {
                board[i][j] = null;
            }
        }
    } // end of clearBoard

    /**
     * Update the board after each move is made
     * Assigns queens to their new positions, and places new/existing arrows on the board
     */
    protected void updateAfterMove() {
        clearBoard();

        // Reset the position of each friendly queen
        for(Queen q: friend) {
        	if(friend != null){
        		board[q.row][q.col] = q;
        	}
        }

        // Reset the position of each enemy queen
        for(Queen q: enemy) {
        	if(enemy != null) {
                board[q.row][q.col] = q;
        	}
        }

        // Reset each arrow on the board
        for(Arrow arrow: arrows) {
            if(arrow != null) {
                board[arrow.row][arrow.col] = arrow;
            }

        }

    } // end of updateAfterMove


    /**
     *
     * @return: boolean indicating if we have won, or lost
     */
    protected boolean goalTest() {
        if (enemyMove == false || legalQueenMoves.size() == 0) {
            if (enemyMove == false) {
                System.out.println("We win!");
            } else {
                System.out.println("We lose!");
            }
            return true;
        }
        else {
            return false;
        }
    } // end of goalTest


    public void printBoard() {
        String boardLayout = "";
        String line = "\nx--- --- --- --- --- --- --- --- --- ---x";
        for (int i = 0; i < 10; i++) {
            boardLayout += line + "\n";
            for (int j = 0; j < 10; j++) {
                boardLayout += "| ";
                if (board[i][j] == null) boardLayout += "  ";
                else if (board[i][j] instanceof Queen) {
                    if (board[i][j] == getEnemy()[0] || board[i][j] == getEnemy()[1] ||
                            board[i][j] == getEnemy()[2] || board[i][j] == getEnemy()[3]) {
                        boardLayout += "B ";
                    } else boardLayout += "W ";
                } else boardLayout += "a ";
            }
            boardLayout += "|";
        }
        boardLayout += line;
        System.out.println(boardLayout);
    } // end of printBoard
}
