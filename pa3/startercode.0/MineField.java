// Name: Ruxun Xiang
// USC NetID: ruxunxia
// CS 455 PA3
// Fall 2020


import java.util.Random;

/**
 * MineField
 * class with locations of mines for a game.
 * This class is mutable, because we sometimes need to change it once it's created.
 * mutators: populateMineField, resetEmpty
 * includes convenience method to tell the number of mines adjacent to a location.
 */
public class MineField {

    // values used in iteration of all other 8 squares around the center square
    public static final int UPPERROW = 1;  // specify the row above the center square
    public static final int LOWERROW = 1; // specify the row under the center square
    public static final int LEFTCOL = 1;  // specify the left column of the center square
    public static final int RIGHTCOL = 1; // specify the right column the center square

    // <put instance variables here>
    private boolean[][] mines; // represent mine field, false for non-mine square and true for mine square
    private int numMines; // number of mines
    private Random generator;



    /**
     * Create a minefield with same dimensions as the given array, and populate it with the mines in the array
     * such that if mineData[row][col] is true, then hasMine(row,col) will be true and vice versa.  numMines() for
     * this minefield will corresponds to the number of 'true' values in mineData.
     *
     * @param mineData the data for the mines; must have at least one row and one col,
     *                 and must be rectangular (i.e., every row is the same length)
     */
    public MineField(boolean[][] mineData) {
        int numRows = mineData.length;
        int numCols = mineData[0].length;
        // initialize the mine field with the same rows and columns as the mineData
        mines = new boolean[numRows][numCols];

        // initialize other instance variables
        numMines = 0;
        generator = new Random();

        // copy the values in mineData to our mine field
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                mines[i][j] = mineData[i][j];
                if (mineData[i][j]){
                    numMines++;
                }
            }
        }
    }


    /**
     * Create an empty minefield (i.e. no mines anywhere), that may later have numMines mines (once
     * populateMineField is called on this object).  Until populateMineField is called on such a MineField,
     * numMines() will not correspond to the number of mines currently in the MineField.
     *
     * @param numRows  number of rows this minefield will have, must be positive
     * @param numCols  number of columns this minefield will have, must be positive
     * @param numMines number of mines this minefield will have,  once we populate it.
     *                 PRE: numRows > 0 and numCols > 0 and 0 <= numMines < (1/3 of total number of field locations).
     */
    public MineField(int numRows, int numCols, int numMines) {
        assert numRows > 0 && numCols > 0 && numMines >= 0 && numMines < numCols * numCols / 3.0;

        // initialize our mine field with the given rows, columns and number of mines
        mines = new boolean[numRows][numCols];

        // initialize other instance variables
        generator = new Random();
        this.numMines = numMines; // also record the number of mines
    }


    /**
     * Removes any current mines on the minefield, and puts numMines() mines in random locations on the minefield,
     * ensuring that no mine is placed at (row, col).
     *
     * @param row the row of the location to avoid placing a mine
     * @param col the column of the location to avoid placing a mine
     *            PRE: inRange(row, col)
     */
    public void populateMineField(int row, int col) {
        assert inRange(row, col);
        int putRow;
        int putCol;
        int minesLeft = numMines();

        resetEmpty(); // when populating this mine field, it should be empty,
        // so reset the mine field from the last game set

        // randomly select a location and put a mine into it for "numMines()" times
        while (minesLeft > 0) {
            // select a location (putRow, putCol)
            putRow = generator.nextInt(numRows());
            putCol = generator.nextInt(numCols());
            // judge if the location of the square equals to that of our first uncovered square, and if a mine
            // has already been put into there
            // if both of the answer is no, then we can safely put a mine into the square
            if (!(putRow == row && putCol == col) && !mines[putRow][putCol]) {
                mines[putRow][putCol] = true; // true for a mine inside the square
                minesLeft--;
            }
        }
    }


    /**
     * Reset the minefield to all empty squares.  This does not affect numMines(), numRows() or numCols()
     * Thus, after this call, the actual number of mines in the minefield does not match numMines().
     * Note: This is the state a minefield created with the three-arg constructor is in
     * at the beginning of a game.
     */
    public void resetEmpty() {
        int row = numRows();
        int col = numCols();

        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                mines[i][j] = false; // reset all the squares to empty ("false" represents non-mine(empty) square)
            }
        }
    }


    /**
     * Returns the number of mines adjacent to the specified mine location (not counting a possible
     * mine at (row, col) itself).
     * Diagonals are also considered adjacent, so the return value will be in the range [0,8]
     *
     * @param row row of the location to check
     * @param col column of the location to check
     * @return the number of mines adjacent to the square at (row, col)
     * PRE: inRange(row, col)
     */
    public int numAdjacentMines(int row, int col) {
        assert inRange(row, col);

        int adjMines = 0; // counter for adjacent mines

        // variables used to cover all 8 squares around the square
        int startRow = row - UPPERROW;
        int endRow = row + LOWERROW;
        int startCol = col - LEFTCOL;
        int endCol = col + RIGHTCOL;

        // define a 3*3 field (a total 9 squares) that surround the aimed square, and see if they have a mine inside
        for (int i = startRow; i <= endRow; i++){
            for (int j = startCol; j <= endCol; j++){
                if (inRange(i,j) && mines[i][j]){ // if the row and column indexes are not out of field bound and there
                    // is a mine inside, then count for this mine
                    adjMines++;
                }
            }
        }
        // if the aimed mine itself has a mine inside, we do not count for it because we only need the number of
        // its "adjacent" mines
        if (mines[row][col]){
            adjMines--;
        }

        return adjMines;
    }


    /**
     * Returns true iff (row,col) is a valid field location.  Row numbers and column numbers
     * start from 0.
     *
     * @param row row of the location to consider
     * @param col column of the location to consider
     * @return whether (row, col) is a valid field location
     */
    public boolean inRange(int row, int col) {
        return row >= 0 && row < numRows() && col >= 0 && col < numCols();
    }


    /**
     * Returns the number of rows in the field.
     *
     * @return number of rows in the field
     */
    public int numRows() {
        return mines.length;       // DUMMY CODE so skeleton compiles
    }


    /**
     * Returns the number of columns in the field.
     *
     * @return number of columns in the field
     */
    public int numCols() {
        return mines[0].length;       // DUMMY CODE so skeleton compiles
    }


    /**
     * Returns whether there is a mine in this square
     *
     * @param row row of the location to check
     * @param col column of the location to check
     * @return whether there is a mine in this square
     * PRE: inRange(row, col)
     */
    public boolean hasMine(int row, int col) {
        assert inRange(row, col);

        return mines[row][col];
    }


    /**
     * Returns the number of mines you can have in this minefield.  For mines created with the 3-arg constructor,
     * some of the time this value does not match the actual number of mines currently on the field.  See doc for that
     * constructor, resetEmpty, and populateMineField for more details.
     *
     * @return numMines
     */
    public int numMines() {
        return numMines;
    }


    // <put private methods here>


}

