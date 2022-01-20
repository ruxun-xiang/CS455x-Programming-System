
import java.util.ArrayList;

public class BookshelfTester {
    public static void main(String[] args){
        Bookshelf aBookShelf = new Bookshelf();
        System.out.println("---Create an empty bookshelf exp[]---");
        System.out.print("Books in the empty bookshelf: ");
        System.out.println(aBookShelf.toString());
        System.out.println("");



        System.out.println("---Create a bookshelf with 3 books exp[5,4,6]---");
        ArrayList<Integer> threeBooks = new ArrayList<>(3);
        threeBooks.add(5);
        threeBooks.add(4);
        threeBooks.add(6);
        Bookshelf bBookShelf = new Bookshelf(threeBooks);
        System.out.print("Books in the bookshelf: ");
        System.out.println(bBookShelf.toString());
        System.out.println("");

        System.out.println("---Add a book (3) to the front of the bookshelf exp[3,5,4,6]---");
        bBookShelf.addFront(3);
        System.out.print("Books in the bookshelf: ");
        System.out.println(bBookShelf.toString());
        System.out.println("");

        System.out.println("---Add a book (7) to the last of the bookshelf exp[3,5,4,6,7]---");
        bBookShelf.addLast(7);
        System.out.print("Books in the bookshelf: ");
        System.out.println(bBookShelf.toString());
        System.out.println("");

        System.out.println("---Remove the first book of the bookshelf exp[5,4,6,7]---");
        bBookShelf.removeFront();
        System.out.print("Books in the bookshelf: ");
        System.out.println(bBookShelf.toString());
        System.out.println("");

        System.out.println("---Remove the last book of the bookshelf exp[5,4,6]---");
        bBookShelf.removeLast();
        System.out.print("Books in the bookshelf: ");
        System.out.println(bBookShelf.toString());
        System.out.println("");

        System.out.println("---Number of books in the bookshelf exp[3]---");
        System.out.println("There are " + bBookShelf.size() + " books in the bookshelf");
        System.out.println("");

        System.out.println("---Get the height of the first book in the bookshelf exp[5]---");
        System.out.println("The height is: " + bBookShelf.getHeight(0));
        System.out.println("");

        System.out.println("---If the bookshelf is sorted? exp[false]---");
        System.out.println(bBookShelf.isSorted());
        System.out.println("");
    }
}


