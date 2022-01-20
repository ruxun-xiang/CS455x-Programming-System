// Name: Ruxun Xiang
// USC NetID: ruxunxia
// CSCI455 PA2
// Fall 2020

import java.util.Scanner;

/**
 * Class BookshelfKeeperProg
 *
 * Creates a virtual platform based on console for users to put or pick a book they want
 * on a bookshelf (books kept in non-decreasing order by height).
 * Pick or put operations are performed with minimum times of the lower level adds or removes.
 * The console will then display the operating times defined in bookshelf class to
 * perform one pick or put operation and all the  operations.
 */

public class BookshelfKeeperProg {

    /**
     * main method
     *
     * entrance of this whole program, users input information based on the reminder on the console,
     * they will input heights of books to initialize the bookshelf, command(pick/put) to perform
     * operations and corresponding parameters(index/height)
     */
    public static void main(String[] args){
        Bookshelf aBookshelf = new Bookshelf();// create a bookshelf object without books
        boolean isValidBookshelf;// used to judge if the bookshelf is valid,
        // that is the height of books in the bookshelf are all greater than zero.

        Scanner in = new Scanner(System.in);// used to read inputs from console or a file

        isValidBookshelf = createABookshelf(aBookshelf, in);// initialize the bookshelf with inputs

        // if the bookshelf is valid (height of books greater than 0),
        // the program continues, or an error occurs and the program ends.
        if (isValidBookshelf) {
            // we have to use a sorted bookshelf to create a bookshelf keeper,
            // and if the bookshelf is sorted, the program continues, or an error occurs and the program ends
            if (aBookshelf.isSorted()) {
                BookshelfKeeper aBookshelfKeeper = createABookshelfKeeper(aBookshelf);// create a bookshelf keeper
                performOperations(aBookshelfKeeper, in);// perform a series pick or put operations
            } else { // if the bookshelf is not sorted, prints an error message
                System.out.println("ERROR: Heights must be specified in non-decreasing order.");
            }
        } else { // if the bookshelf is not valid, prints an error message
            System.out.println("ERROR: Height of a book must be positive.");
        }
        System.out.println("Exiting Program.");// end of program
    }

    /**
     * Creates a bookshelf object, the height of books come from input of users or a file.
     *
     * @param aBookshelf an empty bookshelf object
     * @param in a Scanner object, used to read inputs (height of books to initialize the bookshelf)
     * from console or a file
     * @return boolean hasNoNegative, if the bookshelf does not have negative height books in it,
     * then the value becomes true
     */
    public static boolean createABookshelf(Bookshelf aBookshelf, Scanner in){

        System.out.println("Please enter initial arrangement of books followed by newline:");
        String line = in.nextLine();//read the next line of input
        Scanner aLine = new Scanner(line);//switch the string object into a scanner object so that we can use hasNext(),
        // nextInt() method to split the elements from input

        boolean hasNoNegative = true;

        // keeps read integers from aLine, and add them to the bookshelf object one by one
        // if we get a negative value, stop reading and set "hasNoNegative" to false
        while (aLine.hasNext()){
            int nextInt = aLine.nextInt();
            if (nextInt < 0){
                hasNoNegative = false;
                break;
            }
            else {
                aBookshelf.addLast(nextInt); //add an integer (height of book) to the end of the bookshelf
            }
        }
        return hasNoNegative;
    }

    /**
     * Creates a bookshelf keeper object using a bookshelf object (books must be sorted).
     *
     * @param aBookshelf an initialized bookshelf, with books placed inside
     * @return BookshelfKeeper aBSK, the initialized bookshelf keeper object
     */
    public static BookshelfKeeper createABookshelfKeeper(Bookshelf aBookshelf){
        BookshelfKeeper aBSK;
        aBSK = new BookshelfKeeper(aBookshelf);
        int numOperation = 0;// number of calls to the 4 base operation for one pick/put

//        reportaBSK(aBSK, numOperation);
        System.out.println(aBSK.toString());
        return aBSK;
    }

//    /**
//     * Prints all the books in the bookshelf keeper, number of calls to the 4 base operation for one pick/put
//     * and all the pick/put operations that have performed
//     *
//     * @param aBookshelfKeeper a bookshelf keeper object
//     * @param numOperation number of calls to the 4 base operation for one pick/put
//     */
//    private static void reportaBSK(BookshelfKeeper aBookshelfKeeper, int numOperation)
//    {
//        System.out.println(aBookshelfKeeper.toString() +
//                " " + numOperation +
//                " " + aBookshelfKeeper.getTotalOperations());
//    }

    /**
     * The main part of this program, performing a series of put or pick operations
     * according to inputs from users until it receives an "end".
     *
     * @param aBookshelfKeeper a bookshelf keeper object
     * @param in a Scanner object, used to read inputs (command and index/height) from console or a file
     */
    public static void performOperations(BookshelfKeeper aBookshelfKeeper, Scanner in){
        System.out.println("Type pick <index> or put <height> followed by newline. Type end to exit.");
        while (in.hasNextLine()) {
            String line = in.nextLine();
            Scanner aLine = new Scanner(line);
            String command = "";
            if (aLine.hasNext())
            {
                command = aLine.next();//read the next element, that is "command"
            }
//            int numOperation;

            // when command is  "end", exit the while loop.
            if (command.equals("end")) {
                break;
            }

            // if the command is not "end", then it has to be "pick" or "put",
            // and if there is another integer followed by the command then it has to be the index of the book
            // that the user want to pick, or the height that represents a book the user want to put into the bookshelf
            if (aLine.hasNextInt()) {
                int indexOrHeight = aLine.nextInt();// get the integer
                if (command.equals("put")) {
                    // if the integer, the height of the book according to command "put" is less than 0,
                    // print an error message, and exit the while loop
                    if (indexOrHeight <= 0){
                        System.out.println("ERROR: Height of a book must be positive.");
                        break;
                    }
                    // put the height of the book into the bookshelf keeper
                    aBookshelfKeeper.putHeight(indexOrHeight);
//                    reportaBSK(aBookshelfKeeper, numOperation);// print the current status of the bookshelf keeper
                    System.out.println(aBookshelfKeeper.toString());
                    // and number of operations
                } else if (command.equals("pick")) {
                    // if the integer, the index of the books in the bookshelf according to command "pick" is greater
                    // than or equals to total number of the books, print an error message and exit the while loop
                    if (indexOrHeight >= aBookshelfKeeper.getNumBooks()) {
                        System.out.println("ERROR: Entered pick operation is invalid on this shelf.");
                        break;
                    }
                    // pick the book according to the index
                    aBookshelfKeeper.pickPos(indexOrHeight);
//                    reportaBSK(aBookshelfKeeper, numOperation);
                    System.out.println(aBookshelfKeeper.toString());
                } else {// when a user inputs an undefined command that is neither pick or put, prints an error message
                    // and exit the while loop
                    System.out.println("ERROR: Operation should be either pick or put.");
                    break;
                }
            }
        }
    }
}

