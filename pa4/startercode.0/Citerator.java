import java.util.Stack;

public class Citerator {

    private Node gTable;

    public class Node { //表节点
        int flag; // "1"表示该节点代表"["或","，"0"表示该节点代表数字"0-9"
        Integer data; //用来存储数字"0-9"，若非数字节点则设置为Null
        Node head; // 头节点，用来链接（下一层）表示"["的节点或表示数字"0-9"的节点
        Node tail; // 尾节点，用来链接（当前层）表示","的节点

        public Node(Node head, Node tail, int flag, Integer data) { // 节点初始化
            this.flag = flag;
            this.data = data;
            this.head = head;
            this.tail = tail;
        }
    }


    public Citerator(String str){
        gTable = new Node(null, null, 1, null); // 广义表初始化

        Node insertNode;
        Node gTablePointer = gTable; //指针用来链接其余节点，初始化为表头

        Stack<Character> charStack = new Stack<>(); // 存储"["
        Stack<Node> nodeStack = new Stack<>(); // 存储表示"("的节点

        // 构造表
        for (int i = 0; i < str.length(); i++){
            char currChar = str.charAt(i);
            if (currChar == '['){
                insertNode = new Node(null, null, 1, null);
                charStack.push(currChar);
                if (charStack.size() > 1){ //表示现在有嵌套 e.g."[["
                    nodeStack.push(gTablePointer); // 保存当前节点，以便完成一个"[]"内的操作后重新找到该节点
                    gTablePointer.head = insertNode; //将当前节点head指针链接至这个新的表示"["的节点
                    gTablePointer = gTablePointer.head;// 移动指针，指向这个新节点（下一层）
                } else {
                    gTablePointer.tail = insertNode; // 初始情况，直接将新节点接在当前节点尾指针后
                    gTablePointer = gTablePointer.tail;// 移动指针，指向这个新节点（当前层）
                }
            } else if (currChar >= '0' && currChar <= '9'){
                insertNode = new Node(null,null, 0,
                        Integer.parseInt(String.valueOf(currChar)));
                gTablePointer.head = insertNode; // 移动指针，指向这个新节点（下一层）
            } else if (currChar == ','){
                gTablePointer.tail = new Node(null, null, 1, null);
                gTablePointer = gTablePointer.tail; // 移动指针，指向这个新节点（当前层）
            } else if (currChar == ']') { // 处理完一对"[]"里的内容
                if (charStack.size() > 1){
                    gTablePointer = nodeStack.pop(); // 指针重新指向上层表示","的节点
                }
                charStack.pop();
            }
        }
    }

    public void next(){
        iterate(gTable); //递归遍历表
        System.out.println("Null");
    }

    public boolean hasNext(Node gNode){ //判断是否存在下个节点
        return gNode != null;
    }

    private void iterate(Node gNode) {
        if (gNode == null) { // 如果指针内容为空，直接返回
            return;
        }
        if (gNode.flag == 0) {
            System.out.print(gNode.data + " "); //输出数字节点内容
        }
        if (hasNext(gNode.head)){
            iterate(gNode.head);
        }
        if (hasNext(gNode.tail)){
            iterate(gNode.tail);
        }
    }

    public static void main (String[] args){
        String str = "[[1,1],2,[1,[1,2]]]";
        Citerator list = new Citerator(str);
        list.next();
    }
}
