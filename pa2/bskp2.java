import java.util.Scanner;

public class bskp2 {

    public static void main(String[] args){
        Bookshelf aBS = new Bookshelf();
        boolean isValidBookshelf;
        Scanner in = new Scanner(System.in);

        isValidBookshelf = createABookshelf(aBS, in);

        if (isValidBookshelf) {
            if (aBS.isSorted()) {
                BookshelfKeeper aBSK = createABookshelfKeeper(aBS);
                performOperations(aBSK, in);
            } else {
                System.out.println("ERROR: Heights must be specified in non-decreasing order.");
            }
        } else {
            System.out.println("ERROR: Height of a book must be positive.");
        }
        System.out.println("Exiting Program.");
    }


    public static boolean createABookshelf(Bookshelf aBS, Scanner line){

        System.out.println("Please enter initial arrangement of books followed by newline:");
        String aLine = line.nextLine();
        Scanner in = new Scanner(aLine);
        boolean hasNoNegative = true;


        while (in.hasNext()){
            int nextInt = in.nextInt();
            if (nextInt < 0){
                hasNoNegative = false;
                break;
            }
            else {
                aBS.addLast(nextInt);
            }
        }
        return hasNoNegative;
    }

    public static BookshelfKeeper createABookshelfKeeper(Bookshelf aBS){
        BookshelfKeeper aBSK;
        aBSK = new BookshelfKeeper(aBS);
        int numOperation = 0;
        reportaBSK(aBSK, numOperation);
        return aBSK;
    }

    private static void reportaBSK(BookshelfKeeper aBSK, int numOperation)
    {
        System.out.println(aBSK.toString() + " " + numOperation + " " + aBSK.getTotalOperations());
    }

    public static void performOperations(BookshelfKeeper aBSK, Scanner line){
        System.out.println("Type pick <index> or put <height> followed by newline. Type end to exit.");
        while (true) {
            String aLine = line.nextLine();
            Scanner in = new Scanner(aLine);
            String command = in.next();
            int numOperation;

            if (command.equals("end")) {
                break;
            }

            if (in.hasNextInt()) {
                int indexOrHeight = in.nextInt();
                if (command.equals("put")) {
                    if (indexOrHeight <= 0){
                        System.out.println("ERROR: Height of a book must be positive.");
                        break;
                    }
                    numOperation = aBSK.putHeight(indexOrHeight);
                    reportaBSK(aBSK, numOperation);
                } else if (command.equals("pick")) {
                    if (indexOrHeight >= aBSK.getNumBooks()) {
                        System.out.println("ERROR: Entered pick operation is invalid on this shelf.");
                        break;
                    }
                    numOperation = aBSK.pickPos(indexOrHeight);
                    reportaBSK(aBSK, numOperation);
                } else {
                    System.out.println("ERROR: Operation should be either pick or put.");
                    break;
                }
            }
        }
    }





}
