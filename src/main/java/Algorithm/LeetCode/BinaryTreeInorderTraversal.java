package Algorithm.LeetCode;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinaryTreeInorderTraversal {
    @Test
    public void inorderTraversalTest() {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);

        treeNode1.right = treeNode2;
        treeNode2.left = treeNode3;
        Assert.assertEquals(inorderTraversal(treeNode1), Arrays.asList(1, 3, 2));
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> answer = new ArrayList<>();
        inorderTravel(answer, root);

        return answer;
    }

    private void inorderTravel(List<Integer> answer, TreeNode root) {
        if (root != null) {
            inorderTravel(answer, root.left);
            answer.add(root.val);
            inorderTravel(answer, root.right);
        }
    }
}
