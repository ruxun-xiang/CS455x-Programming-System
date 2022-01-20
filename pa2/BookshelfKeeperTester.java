import java.util.ArrayList;

public class BookshelfKeeperTester {
    public static void main(String[] args){
        ArrayList<Integer> al = new ArrayList<>();
        al.add(8);
        al.add(12);
        al.add(33);
        al.add(44);
        al.add(55);
        al.add(66);
        al.add(77);
        Bookshelf bs = new Bookshelf(al);
        BookshelfKeeper bsk = new BookshelfKeeper(bs);
        System.out.print(bsk.toString());
        int counter = bsk.pickPos(1);
        System.out.println(" " + counter);
        System.out.println(bsk.toString());

        counter = bsk.putHeight(56);
        System.out.print(bsk.toString());
        System.out.println(" " + counter);



    }
}
