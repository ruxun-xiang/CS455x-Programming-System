import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class openFile {
    public static Scanner openFileR(Scanner in){
        boolean isValid = false;
        String fileName = "";
        Scanner inFile = null;
        while (!isValid)
        {
            System.out.print("Name of file to read: ");
            fileName = in.next();
            try{
                File aFile = new File(fileName);
                inFile = new Scanner(aFile);
                isValid = true;
            }
            catch (FileNotFoundException e){
                System.out.println("File " + fileName + " does not exist");
            }
        }
        return inFile;
    }

    public static void main(String[] args){
        Scanner inFile = openFileR(new Scanner(System.in));
    }
}
