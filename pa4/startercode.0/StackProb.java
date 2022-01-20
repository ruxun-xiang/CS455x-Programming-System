import java.util.Stack;

public class StackProb {

    public static void main(String[] args) {
        Stack<Integer> s = new Stack<Integer>();

        s.push(7);
        s.push(4);
        s.push(11);
        System.out.println(s);
        Stack<Integer> t = foo(s);
        System.out.println(t);

    }

    public static Stack<Integer> foo(Stack<Integer> s) {

        Stack<Integer> tmp = new Stack<Integer>();

        System.out.println(tmp);
        System.out.println(s);
        // Point A.  (in 3.1 show current contents of s and tmp)

        while (!s.empty()) {
            tmp.push(s.peek());
            s.pop();
        }
        System.out.println(tmp);
        System.out.println(s);
        // Point B.  (in 3.1 show current contents of s and tmp)

        while (!tmp.empty()) {
            s.push(tmp.peek());
            tmp.pop();
        }
        System.out.println(tmp);
        System.out.println(s);
        // Point C. (in 3.1 show current contents of s and tmp)

        Stack<Integer> news = s;
        return news;

    }

}