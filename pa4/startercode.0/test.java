import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class test {

    public static void printReversed(Scanner in){
        Stack<String> aStack = new Stack<>();
        while (in.hasNext()){
            String word = in.next();
            if (word.charAt(word.length()-1) != '.'){
                aStack.push(word);
            }
            else {
                aStack.push(word.substring(0,word.length()-1));
                int size = aStack.size();
                for (int i = 0; i < size - 1; i++){
                    System.out.print(aStack.pop() + " ");
                }
                System.out.print(aStack.pop());
                System.out.print(". ");
            }
        }

    }


    public static void main(String[] args){
        printReversed(new Scanner(System.in));
    }
}
