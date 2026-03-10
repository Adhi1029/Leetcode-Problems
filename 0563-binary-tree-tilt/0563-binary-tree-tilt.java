/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private int totalTilt;
    public int findTilt(TreeNode root) {
        totalTilt = 0;
        calculateSubtreeSum(root);
        return totalTilt;
    }
    private int calculateSubtreeSum(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftSubtreeSum = calculateSubtreeSum(node.left);
        int rightSubtreeSum = calculateSubtreeSum(node.right);
        int currentNodeTilt = Math.abs(leftSubtreeSum - rightSubtreeSum);
        totalTilt += currentNodeTilt;
        return leftSubtreeSum + rightSubtreeSum + node.val;
    }
}