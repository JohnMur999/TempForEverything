package Ya;

import java.util.concurrent.atomic.AtomicInteger;

public class YaMaxPathInTree {

    private int result;

    public YaMaxPathInTree() {
        this.result = Integer.MIN_VALUE;
    }

    public static class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public int getMaxTreePath(TreeNode root) {
        getMaxStraightPath(root);
        return result;
    }

    private int getMaxStraightPath(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int maxL = Math.max(getMaxStraightPath(root.left), 0);
        int maxR = Math.max(getMaxStraightPath(root.right), 0);

        result = Math.max(result, maxL + root.val + maxR);

        return root.val + Math.max(maxL, maxR);
    }

    public static void main(String[] args) {
        YaMaxPathInTree solver = new YaMaxPathInTree();

        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(2);
        root.right = new TreeNode(10);
        root.left.left = new TreeNode(20);
        root.left.right = new TreeNode(1);
        root.right.right = new TreeNode(-25);
        root.right.right.left = new TreeNode(3);
        root.right.right.right = new TreeNode(4);

        int maxPathSum = solver.getMaxTreePath(root);
        System.out.println("Максимальная сумма пути: " + maxPathSum);
    }
}
