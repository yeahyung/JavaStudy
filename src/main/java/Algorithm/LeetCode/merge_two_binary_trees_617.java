package Algorithm.LeetCode;

public class merge_two_binary_trees_617 {

    public static void main(String[] args){
        TreeNode root11 = new TreeNode(1);
        TreeNode root12 = new TreeNode(3);
        root11.left = root12;
        TreeNode root13 = new TreeNode(2);
        root11.right = root13;
        TreeNode root14 = new TreeNode(5);
        root12.left = root14;

        TreeNode root21 = new TreeNode(2);
        TreeNode root22 = new TreeNode(1);
        root21.left = root22;
        TreeNode root23 = new TreeNode(3);
        root21.right = root23;
        TreeNode root24 = new TreeNode(4);
        root22.right = root24;
        TreeNode root25 = new TreeNode(7);
        root23.right = root25;

        TreeNode anw = mergeTrees(root11, root21);
        display(anw);
    }

    public static TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if(root1 == null)
            return root2;
        else if(root2 == null)
            return root1;

        root1.val += root2.val;
        root1.left = mergeTrees(root1.left, root2.left);
        root1.right = mergeTrees(root1.right, root2.right);

        return root1;
    }

    public static void display(TreeNode root){
        if(root != null){
            System.out.println(root.val);
            display(root.left);
            display(root.right);
        }
    }
}
