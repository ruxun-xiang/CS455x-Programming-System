// Name: Ruxun Xiang
// USC NetID: ruxunxia
// CSCI455 PA2
// Fall 2020


/**
 * Class BookshelfKeeper
 *
 * Enables users to perform efficient putPos or pickHeight operation on a bookshelf of books kept in
 * non-decreasing order by height, with the restriction that single books can only be added
 * or removed from one of the two *ends* of the bookshelf to complete a higher level pick or put
 * operation.  Pick or put operations are performed with minimum number of such adds or removes.
 */
public class BookshelfKeeper {

   /**
    Representation invariant:
    -- number of books stored is bookShelf.size()
    -- books are only identified by their height
    -- the height of books must be positive numbers
    -- bookshelf must not be null
    -- books are arranged in increasing order
    */

   // <add instance variables here>
   Bookshelf aBookShelfKeeper;
   private int countOne; // counts for the times of call to the lower level operations
   // for one operation, either pick or put
   private int countAll; // count for the times of calls to lower level
   // operations for all the operations that have performed

   /**
    * Creates a BookShelfKeeper object with an empty bookshelf
    */
   public BookshelfKeeper() {
      aBookShelfKeeper = new Bookshelf();
      assert isValidBookshelfKeeper();
   }

   /**
    * Creates a BookshelfKeeper object initialized with the given sorted bookshelf.
    * Note: method does not make a defensive copy of the bookshelf.
    *
    * PRE: sortedBookshelf.isSorted() is true.
    */
   public BookshelfKeeper(Bookshelf sortedBookshelf) {
      assert sortedBookshelf.isSorted();
      aBookShelfKeeper = sortedBookshelf;
      assert isValidBookshelfKeeper();

   }

   /**
    * Removes a book from the specified position in the bookshelf and keeps bookshelf sorted
    * after picking up the book.
    *
    * Returns the number of calls to mutators on the contained bookshelf used to complete this
    * operation. This must be the minimum number to complete the operation.
    *
    * PRE: position must be in the range [0, getNumBooks()).
    */
   public int pickPos(int position) {
      assert 0 <= position && position < getNumBooks();

      // if position is greater than or equals to a half of the number of books, we perform
      // pick operation from the right of shelf to guarantee a minimum times of operation
      if (position >= getNumBooks() / 2){
         countOne = pickFromLast(position);
      }
      else {// else we perform pick operation from left
         countOne = pickFromFront(position);
      }
      countAll += countOne; // add counter to countAll
      assert isValidBookshelfKeeper();
      return countOne;
   }

   /**
    * Inserts book with specified height into the shelf.  Keeps the contained bookshelf sorted
    * after the insertion.
    *
    * Returns the number of calls to mutators on the contained bookshelf used to complete this
    * operation. This must be the minimum number to complete the operation.
    *
    * PRE: height > 0
    */
   public int putHeight(int height) {
      assert height > 0;

      // if currently there are no books inside, we directly add the book to the last
      if (getNumBooks() == 0){
         aBookShelfKeeper.addLast(height);
         countOne++;// counts for the addLast() operation
         countAll += countOne;
         return countOne;
      }

      int midHeight = aBookShelfKeeper.getHeight(getNumBooks() / 2);// to compare the height of the book
      // we want to put with the height of the book in the middle of the bookshelf

      // if height is greater than or equals to midHeight, we put the book from right to guarantee a minimum
      // times of operation
      if ( height >= midHeight){
         countOne = putFromLast(height);
      }
      else {// else we put the book from left
         countOne = putFromFront(height);
      }
      countAll += countOne;
      assert isValidBookshelfKeeper();
      return countOne;
   }

   /**
    * Returns the total number of calls made to mutators on the contained bookshelf
    * so far, i.e., all the ones done to perform all of the pick and put operations
    * that have been requested up to now.
    */
   public int getTotalOperations() {
      return countAll;
   }

   /**
    * Returns the number of books on the contained bookshelf.
    */
   public int getNumBooks() {
      return aBookShelfKeeper.size();
   }

   /**
    * Returns string representation of this BookshelfKeeper. Returns a String containing height
    * of all books present in the bookshelf in the order they are on the bookshelf, followed
    * by the number of bookshelf mutator calls made to perform the last pick or put operation,
    * followed by the total number of such calls made since we created this BookshelfKeeper.
    *
    * Example return string showing required format: “[1, 3, 5, 7, 33] 4 10”
    *
    */
   public String toString() {
      return aBookShelfKeeper.toString() + " " + countOne + " " + countAll;   // dummy code to get stub to compile
   }

   /**
    * Returns true iff the BookshelfKeeper data is in a valid state.
    * (See representation invariant comment for details.)
    */
   private boolean isValidBookshelfKeeper() {

      // there are two representation invariants we are to test:
      // 1 if the heights of the books are all greater than zero
      // 2 if the heights of the books are sorted in non-decreasing order
      // we first test repr. invar. 1, if at some middle step it returns a false
      // then we don't need to test repr. invar. 2, because a valid bookshelf keeper
      // must satisfy both, and in this case clearly it doesn't
      for (int i = 0; i < aBookShelfKeeper.size(); i++ ){
         if (aBookShelfKeeper == null){
            return false;
         }

         if (aBookShelfKeeper.getHeight(i) < 0){
            return false;
         }
      }
      // if no false is returned, we can be sure that there are no negative values or 0 in it,
      // then we test repr. invar. 2, if the isSorted() returns true, then we can be sure the
      // bookshelf keeper is valid, else it's not.
      return aBookShelfKeeper.isSorted();

   }

   // add any other private methods here

   /**
    *
    * conduct "pick" operation from the right(last) side of the bookshelf
    *
    * @param position the position of the book we want to pick
    * @return counter to count for the times we call the lower level operations in this pick operation
    */
   public int pickFromLast(int position){
      // we want a data structure to store the books that are to remove from and re-add to the bookshelf
      // intitialize a stack to deal with it to keep the order exactly the same as before (except for the books we pick)
      // when finished remove and re-add operations
      int stackHeight = getNumBooks();
      int[] aStack = new int[stackHeight];
      int topOfStack = -1; // a pointer that point to the top of the stack
      int counter = 0;

      // for all the books from the last one to the right to the one we are going to pick
      // we first remove them from right of the bookshelf and store them into the stack
      for (int i = getNumBooks() - 1; i >= position; i--){
         topOfStack++;
         aStack[topOfStack] = aBookShelfKeeper.removeLast();
         counter++;
      }

      // now on the top of the stack is the book we want, we don't have to store its information and
      // re-add into the bookshelf anymore, so assign the pointer to the book below it
      topOfStack--;

      // re-add all the books from the stack to the shelf from the right side
      while (topOfStack >= 0){
         aBookShelfKeeper.addLast(aStack[topOfStack]);
         counter++;
         topOfStack--;
      }

      return counter;
   }

   /**
    *
    * conduct "pick" operation from the left side(front) of the bookshelf
    *
    * @param position the position of the book we want to pick
    * @return counter that counts for the times we call the lower level operations during this pick operation
    */
   public int pickFromFront(int position){
      // intialize a stack to store the books that are influenced
      int stackHeight = getNumBooks();
      int[] aStack = new int[stackHeight];
      int topOfStack = -1;
      int counter = 0;

      // for all the books from the first one to the left to the book we want, we remove them
      // from left of the bookshelf and store them and store them into the stack
      for (int i = 0; i <= position; i++){
         topOfStack++;
         aStack[topOfStack] = aBookShelfKeeper.removeFront();
         counter++;
      }

      topOfStack--;// pick the book we want

      // re-add the books in the stack to the bookshelf from the left side
      while (topOfStack >= 0){
         aBookShelfKeeper.addFront(aStack[topOfStack]);
         counter++;
         topOfStack--;
      }
      return counter;

   }

   /**
    *
    * conduct "put" operation from the right side(last) of the bookshelf
    * @param height the height that represents the book we want to put
    * @return counter that counts for times of call to the lower level operations during this "put" operation
    */
   public int putFromLast(int height){
      // initialize a stack to store the books that are influenced
      int counter = 0;
      int[] aStack = new int[getNumBooks()];
      int topOfStack = -1;

      // we find all the books that are higher than the book we want to put from the right side,
      // and remove them from last and store them into the stack
      while (height < aBookShelfKeeper.getHeight(getNumBooks() - 1))
      {
         topOfStack++;
         aStack[topOfStack] = aBookShelfKeeper.removeLast();
         counter++;
      }

      // put the book to the last
      aBookShelfKeeper.addLast(height);
      counter++;

      // re-add the books in the stack to the bookshelf from the right side
      while (topOfStack >= 0){
         aBookShelfKeeper.addLast(aStack[topOfStack]);
         counter++;
         topOfStack--;
      }
      return counter;
   }

   /**
    *
    * conduct "put" operation from the left side(front) of the bookshelf
    * @param height the height that represents the book we want to put
    * @return counters that counts for tiems of call to the lower level operations during this "put" operation
    */
   public int putFromFront(int height){
      //intialize a stack
      int counter = 0;
      int[] aStack = new int[getNumBooks()];
      int topOfStack = -1;

      // first find all the books that are lower than the books we want to put from the left side,
      // and remove them from front and store them into the stack
      while (height > aBookShelfKeeper.getHeight(0)){
         topOfStack++;
         aStack[topOfStack] = aBookShelfKeeper.removeFront();
         counter++;
      }

      aBookShelfKeeper.addFront(height);// put the book from front
      counter++;

      // re-add the books in the stack to the bookshelf from the left side
      while (topOfStack >= 0){
         aBookShelfKeeper.addFront(aStack[topOfStack]);
         counter++;
         topOfStack--;
      }
      return counter;
   }
}
