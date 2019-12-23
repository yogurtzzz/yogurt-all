package binary_tree;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 由单独由前序遍历，和后续遍历，无法唯一确定一个二叉树
 */
public class TreeTranverse {
	public static TreeNode buildTree(LinkedList<Integer> inputList){
		if (inputList == null || inputList.isEmpty()){
			return null;
		}
		TreeNode node = null;
		Integer data = inputList.removeFirst();
		if (data != null){
			node = new TreeNode(data);
			node.leftSon = buildTree(inputList);
			node.rightSon = buildTree(inputList);
		}
		return node;
	}
	public static void preOrder(TreeNode root){
		if (root == null)
			return ;
		System.out.println(root.value);
		preOrder(root.leftSon);
		preOrder(root.rightSon);
	}
	public static void midOrder(TreeNode root){
		if(root == null)
			return;
		midOrder(root.leftSon);
		System.out.println(root.value);
		midOrder(root.rightSon);
	}
	public static void postOrder(TreeNode root){
		if (root == null)
			return;
		postOrder(root.leftSon);
		postOrder(root.rightSon);
		System.out.println(root.value);
	}

	public static void main(String[] args) {
		LinkedList<Integer> input = new LinkedList<>(Arrays.asList(new Integer[]{1,2,3,null,null,4,null,null,5,6,null,null,7,null,null}));
		TreeNode root = buildTree(input);
		//preOrder(root);
		midOrder(root);
		//postOrder(root);
	}
}
