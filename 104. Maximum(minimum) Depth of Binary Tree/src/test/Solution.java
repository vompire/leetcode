package test;

import java.util.LinkedList;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
	//学习方法的嵌套
	public int maxDepth(TreeNode root) {
		return root==null? 0 : Math.max(maxDepth(root.left), maxDepth(root.right))+1;
    }
	
	public int minDepth(TreeNode root)
	{
	    /**
	     * 思路1：
	     * 深度优先遍历（DFS）
	     * 如果当前节点是空，则最小深度为 0，返回
	     * 效率低
	     */
		if(root==null)
        { 
            return 0;
        }else if((root.left==null)||(root.right==null)){
            return root==null? 0 : Math.max(minDepth(root.left), minDepth(root.right))+1;
        }else{
            return root==null? 0 : Math.min(minDepth(root.left), minDepth(root.right))+1;
        }
	}
	
	public int minDepth2(TreeNode root)
	{
	    /**
	     * 思路2：
	     * 广度优先遍历（BFS）
	     * 找到第一个叶子结点就可以停止遍历
	     * 效率高
	     */
		if (root == null) {
            return 0;
        }
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        LinkedList<TreeNode> layerList = new LinkedList<TreeNode>();
        queue.addFirst(root);
        int start = 0;
        int end = 1;
        int level = 1;
        while (!queue.isEmpty()) {
            TreeNode temp = queue.removeLast();
            start++;
            layerList.addFirst(temp);
            if (temp.left == null && temp.right == null) {
                return level;
            }
            if (temp.left != null) {
                queue.addFirst(temp.left);
            }
            if (temp.right != null) {
                queue.addFirst(temp.right);
            }
            if (start == end) {
                level++;
                start = 0;
                end = queue.size();
                layerList = new LinkedList<TreeNode>();
            }
        }
         
        return level;
	}
	

	 public class TreeNode {
	     int val;
	     TreeNode left;
	     TreeNode right;
	     TreeNode(int x) { val = x; }
	 }
	 
	 public static void main(String[] args)
	 {
		 
	 }
}
