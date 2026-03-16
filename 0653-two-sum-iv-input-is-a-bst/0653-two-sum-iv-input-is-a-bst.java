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
    private Set<Integer> visitedValues = new HashSet<>();
    private int targetSum;
    public boolean findTarget(TreeNode root, int k) {
        this.targetSum = k;
        return depthFirstSearch(root);
    }
    private boolean depthFirstSearch(TreeNode node) {
        if (node == null) return false;
        if (visitedValues.contains(targetSum - node.val)) return true;
        visitedValues.add(node.val);
        return depthFirstSearch(node.left) || depthFirstSearch(node.right);
    }
}