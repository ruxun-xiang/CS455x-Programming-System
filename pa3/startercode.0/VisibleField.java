// Name: Ruxun Xiang
// USC NetID:ruxunxia
// CS 455 PA3
// Fall 2020


/**
  VisibleField class
  This is the data that's being displayed at any one point in the game (i.e., visible field, because it's what the
  user can see about the minefield), Client can call getStatus(row, col) for any square.
  It actually has data about the whole current state of the game, including  
  the underlying mine field (getMineField()).  Other accessors related to game status: numMinesLeft(), isGameOver().
  It also has mutators related to actions the player could do (resetGameDisplay(), cycleGuess(), uncover()),
  and changes the game state accordingly.
  
  It, along with the MineField (accessible in mineField instance variable), forms
  the Model for the game application, whereas GameBoardPanel is the View and Controller, in the MVC design pattern.
  It contains the MineField that it's partially displaying.  That MineField can be accessed (or modified) from 
  outside this class via the getMineField accessor.  
 */
public class VisibleField {
   // ----------------------------------------------------------   
   // The following public constants (plus numbers mentioned in comments below) are the possible states of one
   // location (a "square") in the visible field (all are values that can be returned by public method 
   // getStatus(row, col)).
   
   // Covered states (all negative values):
   public static final int COVERED = -1;   // initial value of all squares
   public static final int MINE_GUESS = -2;
   public static final int QUESTION = -3;

   // Uncovered states (all non-negative values):
   
   // values in the range [0,8] corresponds to number of mines adjacent to this square
   
   public static final int MINE = 9;      // this loc is a mine that hasn't been guessed already (end of losing game)
   public static final int INCORRECT_GUESS = 10;  // is displayed a specific way at the end of losing game
   public static final int EXPLODED_MINE = 11;   // the one you uncovered by mistake (that caused you to lose)
   // ----------------------------------------------------------

   // <put instance variables here>
   private int[][] aVisibleField;
   private MineField thisMineField;
   private boolean isGameOver; // to judge if the game is over, "true" for game over
   private int numGuess; // number of Guessing the square
   private int numUncover; // number of uncovering the square

   /**
      Create a visible field that has the given underlying mineField.
      The initial state will have all the mines covered up, no mines guessed, and the game
      not over.
      @param mineField  the minefield to use for for this VisibleField
    */
   public VisibleField(MineField mineField) {
      int row = mineField.numRows();
      int col = mineField.numCols();

      // initialize the VisibleField instance aVisibleField (all the squares should in COVERED state),
      // set all values of squares in visible field to COVERED
      aVisibleField = new int[row][col];
      for (int i = 0; i < row; i++){
         for (int j = 0; j < col; j++){
            aVisibleField[i][j] = COVERED;
         }
      }
      thisMineField = mineField; // record the mineField underlying the visible field
      // initialize other instance variables
      isGameOver = false;
      numGuess = 0;
      numUncover = 0;

   }
   
   
   /**
      Reset the object to its initial state (see constructor comments), using the same underlying
      MineField. 
   */     
   public void resetGameDisplay() {
      int row = aVisibleField.length;
      int col = aVisibleField[0].length;

      // reset the visible field to the initial state(all squares are COVERED)
      for (int i = 0; i < row; i++){
         for (int j = 0; j < col; j++){
            aVisibleField[i][j] = COVERED;
         }
      }

      // reset all other instance variables
      isGameOver = false;
      numGuess = 0;
      numUncover = 0;
   }
  
   
   /**
      Returns a reference to the mineField that this VisibleField "covers"
      @return the minefield
    */
   public MineField getMineField() {
      return thisMineField;
   }
   
   
   /**
      Returns the visible status of the square indicated.
      @param row  row of the square
      @param col  col of the square
      @return the status of the square at location (row, col).  See the public constants at the beginning of the class
      for the possible values that may be returned, and their meanings.
      PRE: getMineField().inRange(row, col)
    */
   public int getStatus(int row, int col) {
      assert getMineField().inRange(row, col);

      return aVisibleField[row][col];
   }

   
   /**
      Returns the the number of mines left to guess.  This has nothing to do with whether the mines guessed are correct
      or not.  Just gives the user an indication of how many more mines the user might want to guess.  This value can
      be negative, if they have guessed more than the number of mines in the minefield.     
      @return the number of mines left to guess.
    */
   public int numMinesLeft() {
      return thisMineField.numMines() - numGuess; // the number of mines left to guess equals to the total number of
      // mines minus the number of guess operation
   }
 
   
   /**
      Cycles through covered states for a square, updating number of guesses as necessary.  Call on a COVERED square
      changes its status to MINE_GUESS; call on a MINE_GUESS square changes it to QUESTION;  call on a QUESTION square
      changes it to COVERED again; call on an uncovered square has no effect.  
      @param row  row of the square
      @param col  col of the square
      PRE: getMineField().inRange(row, col)
    */
   public void cycleGuess(int row, int col) {
      assert getMineField().inRange(row,col);

      int status = getStatus(row, col);

      // change COVERED state to MINE_GUESS state, and add a guess
      if (status == COVERED){
         aVisibleField[row][col] = MINE_GUESS;
         numGuess++;
      }
      // change MINE_GUESS state to QUESTION state, and delete a guess
      else if (status == MINE_GUESS){
         aVisibleField[row][col] = QUESTION;
         numGuess--;
      }
      // change QUESTION state back to COVERED state
      else if (status == QUESTION){
         aVisibleField[row][col] = COVERED;
      }
   }

   
   /**
      Uncovers this square and returns false iff you uncover a mine here.
      If the square wasn't a mine or adjacent to a mine it also uncovers all the squares in 
      the neighboring area that are also not next to any mines, possibly uncovering a large region.
      Any mine-adjacent squares you reach will also be uncovered, and form 
      (possibly along with parts of the edge of the whole field) the boundary of this region.
      Does not uncover, or keep searching through, squares that have the status MINE_GUESS. 
      Note: this action may cause the game to end: either in a win (opened all the non-mine squares)
      or a loss (opened a mine).
      @param row  of the square
      @param col  of the square
      @return false   iff you uncover a mine at (row, col)
      PRE: getMineField().inRange(row, col)
    */
   public boolean uncover(int row, int col) {
      assert getMineField().inRange(row, col);

      // if the uncovered square has a mine, change the state of the square to EXPLODED_MINE,
      // add one uncovered square, and the game is over, the player lost the game,  so set isGameOver to true
      // we have to do some final settings for other squares to specify this lost situation,
      // which are encapsulated in method lostSet()
      if (thisMineField.hasMine(row, col)) {
         aVisibleField[row][col] = EXPLODED_MINE;
         numUncover++;
         isGameOver = true;
         lostSet();
         return false;// this uncover method returns false, and isGameOver equals to true represents we lost the game
      }else { // else if there is no mine under the square, then we can safely uncover it
         recursiveUncover(row, col);
      }

      winCheck(); // after every uncover operation, check if the current game status satisfy wining condition, if yes,
      // the game is over, the player won the game and we still have to do some settings for other squares to specify
      // the wining situation (if necessary).

      return true; // this uncover method returns true, and isGameOver equals to true represents we won the game
   }

   /**
      Returns whether the game is over.
      (Note: This is not a mutator.)
      @return whether game over
    */
   public boolean isGameOver() {
      return isGameOver;
   }
 
   
   /**
      Returns whether this square has been uncovered.  (i.e., is in any one of the uncovered states, 
      vs. any one of the covered states).
      @param row of the square
      @param col of the square
      @return whether the square is uncovered
      PRE: getMineField().inRange(row, col)
    */
   public boolean isUncovered(int row, int col) {
      assert getMineField().inRange(row, col);

      int status = getStatus(row, col);
      // if the status of a square does not belong to any of the "covered" states, it is uncovered
      return !(status == COVERED || status == MINE_GUESS || status == QUESTION);
   }
   
 
   // <put private methods here>

   /**
    *
    * when there is no mine under the square, we can safely uncover it. After uncovering, we use 2 kinds of values to
    * specify the uncovered state of the square on the visible field: equals to 0 and greater than 0 (1-8),
    * which are the numbers of the adjacent Mines for this square.
    * if the value is greater than 0, we just assign this value to the square on the visible field to represent
    * its uncovered state.
    * but if the values is 0, we should also uncover all other 8 squares around it, until we find
    * 1 there are some squares locate on the edge of the field, we won't discover places outside the field
    * 2 there are some squares underlying a mine . So we at most only uncover squares that are adjacent to
    * one or more mines (number of Adjacent Mines doesn't equal to 0)
    * 3 there are some squares in "GUESSED" status
    * 4 there are some squares that already uncovered
    * we will not uncover these squares
    *
    * we do the uncover process by recursion, and for the 4 situations below, we stop the recursion
    *
    * @param row row of the square
    * @param col column of the square
    */
   private void recursiveUncover(int row, int col){
      // base cases
      // stop if the location we want to discover is out of the field
      if (!thisMineField.inRange(row, col) ){
         return;
      }
      // stop if there is a mine under the square, or the square is guessed by a user or the square is already uncovered
      else if (thisMineField.hasMine(row,col) || aVisibleField[row][col] == MINE_GUESS || isUncovered(row, col)){
         return;
      }

      aVisibleField[row][col] = thisMineField.numAdjacentMines(row,col); // assign the number of adjacent mines to
      // represent the uncovered state
      numUncover++;

      // define a 3*3 field (a total 9 squares) that surround the value-0 square,
      // recursively uncover every square except for the center one (the value-0 square itself)
      if (aVisibleField[row][col] == 0){
         recursiveUncover(row - 1, col - 1);
         recursiveUncover(row - 1, col);
         recursiveUncover(row - 1, col + 1);
         recursiveUncover(row, col - 1);
         recursiveUncover(row, col + 1);
         recursiveUncover(row + 1, col - 1);
         recursiveUncover(row + 1, col);
         recursiveUncover(row + 1, col + 1);
      }
   }

   /**
    *
    * settings for lost in this game, except for the uncovered mine, we also have to show player other mines
    * either is still covered or is labeled with a question mark.
    * also we have to show the player his/her incorrect guess of the mines
    */
   private void lostSet(){
      int row = getMineField().numRows();
      int col = getMineField().numCols();

      // for all squares on the visible field
      for (int i = 0; i < row; i++){
         for (int j = 0; j < col; j++){
            // find out the other mines that are still covered or in QUESTION state
            if(getMineField().hasMine(i, j) && (aVisibleField[i][j] == COVERED || aVisibleField[i][j] == QUESTION)){
               aVisibleField[i][j] = MINE; // set the square to MINE state
            } else if (!getMineField().hasMine(i, j) && aVisibleField[i][j] == MINE_GUESS){ // find out the squares that
               // the player guessed wrong
               aVisibleField[i][j] = INCORRECT_GUESS; // set the square to INCORRECT_GUESS state
            }
         }
      }
   }

   /**
    *
    * check if the current situation of this visible field can satisfy the wining situation, that is all normal squares
    * are uncovered without touching any of the mines (or labeled part of or all of them correctly).
    */
   private void winCheck() {
      int row = getMineField().numRows();
      int col = getMineField().numCols();
      int mines = getMineField().numMines();
      // if all non-mine squares are uncovered
      if (numUncover == row * col - mines){
         isGameOver = true; // the player won the game, so set isGameOver equals to true
         if(numGuess != mines) { // there exists a wining situation that the player won without labeling some apparent
            // mines that he/her can't be bothered to do so
            for(int i = 0; i < row; i++) {
               for(int j = 0; j < col; j++) {
                  if(getStatus(i, j) == COVERED) {
                     aVisibleField[i][j] = MINE_GUESS; // for this case, automatically uncover those mines with
                     // MINE_GUESS status
                  }
               }
            }
         }
      }
   }
}
