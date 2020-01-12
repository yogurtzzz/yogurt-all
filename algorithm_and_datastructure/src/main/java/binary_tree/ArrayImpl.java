package binary_tree;


import yogurt.data_structure.stack.Stack;

import java.util.Arrays;
import java.util.LinkedList;

public class ArrayImpl {
    public static void main(String[] args) {
        Integer[] nodes = {3,2,9,null,null,10,null,null,8,null,4};
        Integer[] nodes2 = {10,5,2,null,null,9,8,7,null,null,4,null,null,null,3,6,null,null,13,11,null,null,20};
        LinkedList<Integer> inputNodes = new LinkedList<>(Arrays.asList(nodes));
        TreeNode root = createTree(inputNodes);
        //System.out.println("PreOrder:");
        //preOrderWithStack(root);
        //preOrderNonRecursive(root);
//        System.out.println("MidOrder:");
//        midOrderWithStack(root);
        System.out.println("PostOrder:");
        postOrderWithStack(root);
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
'     * @param root
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

    /**
     * 用栈实现的非递归前序遍历
     * @param root
     */
    public static void preOrderWithStack(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        if (root == null)
            return ;
        System.out.println(root.value);
        stack.push(root);
        TreeNode current = root.leftSon;
        while (current != null || !stack.isEmpty()){
            if (current != null){
                System.out.println(current.value);
                stack.push(current);
                current = current.leftSon;
            }else if (!stack.isEmpty()){
                TreeNode parent = stack.pop();
                current = parent.rightSon;
            }
        }
    }

    /**
     * 用栈实现的非递归中序遍历
     * @param root
     */
    public static void midOrderWithStack(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        if (root == null)
            return ;
        stack.push(root);
        TreeNode current = root.leftSon;
        while (current != null || !stack.isEmpty()){
            if (current != null){
                stack.push(current);
                current = current.leftSon;
            }else if (!stack.isEmpty()){
                TreeNode mid = stack.pop();
                System.out.println(mid.value);
                current = mid.rightSon;
            }
        }
    }
    /**
     * 用栈实现的非递归后序遍历
     */
    public static void postOrderWithStack(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> temp = new Stack<>();
        if (root == null)
            return ;
        temp.push(root);
        while (!temp.isEmpty()){
            TreeNode current = temp.pop();
            stack.push(current);
            if (current.leftSon != null)
                temp.push(current.leftSon);
            if (current.rightSon != null)
                temp.push(current.rightSon);
        }

        while (!stack.isEmpty()){
            System.out.println(stack.pop().value);
        }
    }
}
