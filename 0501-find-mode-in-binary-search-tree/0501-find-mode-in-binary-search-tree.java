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
    private int maxFrequency;
    private int currentCount;
    private TreeNode previousNode;
    private List<Integer> modeList;
    public int[] findMode(TreeNode root) {
        modeList = new ArrayList<>();
        inOrderTraversal(root);
        int[] result = new int[modeList.size()];
        for (int i = 0; i < modeList.size(); i++) result[i] = modeList.get(i);
        return result;
    }
    private void inOrderTraversal(TreeNode node) {
        if (node == null) return;
        inOrderTraversal(node.left);
        currentCount = (previousNode != null && previousNode.val == node.val) ? currentCount + 1 : 1;
        if (currentCount > maxFrequency) {
            modeList = new ArrayList<>(Arrays.asList(node.val));
            maxFrequency = currentCount;
        } else if (currentCount == maxFrequency) modeList.add(node.val);
        previousNode = node;
        inOrderTraversal(node.right);
    }
}