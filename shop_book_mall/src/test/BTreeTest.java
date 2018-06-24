/**
 * @author DonY15
 * @description 基本二叉树实现原理
 * @create 2018\6\19 0019
 */
public class BTreeTest {

    public static void main(String[] args) {
        int[] arr={5,6,8,7,2,9,1,3,4};
        Node tree = new Node();
        tree.value=arr[0];
        for (int i = 1; i < arr.length; i++) {
            insertNode(tree,arr[i]);
        }
        //测试打印
        printTree(tree);
    }

    //中序打印
    public static void printTree(Node tree){
        if (tree.left!=null){
            printTree(tree.left);
        }
        System.out.print(tree.value+" ");
        if(tree.right!=null){
            printTree(tree.right);
        }

    }

    //插入二叉树
    public static void insertNode(Node tree,int value){
        if (value>tree.value){
            if (tree.right!=null){
                insertNode(tree.right,value);
            }else{
                tree.right=new Node();
                tree.right.value=value;
            }
        }else{
            if (tree.left!=null){
                insertNode(tree.left,value);
            }else{
                tree.left=new Node();
                tree.left.value=value;
            }
        }
    }
}


class Node{
    public int value;
    public Node left;
    public Node right;
}
