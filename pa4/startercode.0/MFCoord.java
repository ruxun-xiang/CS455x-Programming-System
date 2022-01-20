import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MFCoord {

    private int row;
    private int col;
    private static final int ALPHA = 31;

    public MFCoord(int row, int col) {
        this.row = row;
        this.col = col;
    }

    // other methods not shown here . . .

    public int hashCode() {
        return  ALPHA * row + col;
    }

    public boolean equals(Object other) {

        if (other == null) { return false; }
        MFCoord oo = (MFCoord) other;
        return this.row == oo.row && this.col == oo.col;
    }

    public static void main (String [] args){

        Set<MFCoord> mySet = new HashSet<MFCoord>();

        mySet.add(new MFCoord(3, 5));

        MFCoord a1 = new MFCoord(3, 5);


        System.out.println(mySet.contains(a1));

        mySet.add(a1);
        System.out.println(mySet.contains(a1));

        mySet.add(new MFCoord(3, 5));
        System.out.println(mySet.contains(new MFCoord(3, 5)));

    }

}
