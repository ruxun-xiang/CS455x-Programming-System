import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

public class test {
    public static void main(String[] args){
        boolean[][] a = {
                {false, false, true, true},
                {false, false, true, false},
                {false, false, false, true},
                {false, false, true, true}
        };
        MineField amf = new MineField(a);
        amf.populateMineField(3,3);
        System.out.println(amf.toString());


    }
}
