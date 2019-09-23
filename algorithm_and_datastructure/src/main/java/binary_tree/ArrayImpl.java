package binary_tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Stack;

public class ArrayImpl {
    public static void main(String[] args) {
        Integer[] nodes = {3,2,9,null,null,10,null,null,8,null,4};
        LinkedList<Integer> inputNodes = new LinkedList<>(Arrays.asList(nodes));
        TreeNode root = createTree(inputNodes);
        System.out.println("PreOrder:");
        preOrder(root);
        System.out.println("MidOrder:");
        midOrder(root);
        System.out.println("PostOrder:");
        postOrder(root);
    }

    /**
     * 入参数组是一个二叉树的前序遍历
     * @param nodes
     * @return
     */
    private static TreeNode createTree(LinkedList<Integer> nodes){
        if (nodes == null || nodes.size() == 0){
            return null;
        }
        Integer val = nodes.removeFirst();
        if (val != null){
            TreeNode node = new TreeNode(val);
            node.leftSon = createTree(nodes);
            node.rightSon = createTree(nodes);
            return node;
        }
        return null;
    }

    /**
     * 前序遍历
     * @param root
     */
    public static void preOrder(TreeNode root){
        if (root == null)
            return ;
        System.out.println(root.value);
        preOrder(root.leftSon);
        preOrder(root.rightSon);
    }

    /**
     * 中序遍历
     * @param root
     */
    public static void midOrder(TreeNode root){
        if (root == null)
            return ;
        midOrder(root.leftSon);
        System.out.println(root.value);
        midOrder(root.rightSon);
    }

    /**
     * 后序遍历
     * @param root
     */
    public static void postOrder(TreeNode root){
        if (root == null)
            return ;
        postOrder(root.leftSon);
        postOrder(root.rightSon);
        System.out.println(root.value);
    }
    /**
     * 前序遍历：非递归实现
     */
    public static void preOrderNonRecursive(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode curNode = stack.pop();
            if (curNode.rightSon != null)
                stack.push(curNode.rightSon);
            if (curNode.leftSon != null)
                stack.push(curNode.leftSon);
            System.out.println(curNode.value);
        }
    }

    /**
     * 中序遍历：非递归实现
     * @param root
     */
    public static void midOrderNonRecursive(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curNode = root;
        while (true){
            if (curNode.leftSon != null){
                stack.push(curNode);
                curNode = curNode.leftSon;
            }else{
                System.out.println(curNode.value);
                if (curNode.rightSon != null) {
                    curNode = curNode.rightSon;
                }else{
                    if (stack.isEmpty())
                        return;
                    TreeNode traceBack = stack.pop();
                    System.out.println(traceBack);
                    if (traceBack.rightSon != null)
                        curNode = traceBack.rightSon;
                }
            }
        }
    }
}
