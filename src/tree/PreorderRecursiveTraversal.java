package tree;

import java.util.ArrayList;
import java.util.List;

public class PreorderRecursiveTraversal implements TreeTraversal {
    private Tree tree;

    public PreorderRecursiveTraversal(Tree tree) {
        this.tree = tree;
    }

    @Override
    public List<Integer> traverse() {
        List<Integer> result = new ArrayList<>();
        preorder(tree.getRoot(), result);
        return result;
    }

    private void preorder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        result.add(root.getData());
        preorder(root.getLeft(), result);
        preorder(root.getRight(), result);
    }

    public static void main(String[] args) {
        Tree tree = new Tree(10);
        TreeNode root = tree.getRoot();
        root.setLeft(new TreeNode(20));
        root.setRight(new TreeNode(30));
        root.getRight().setLeft(new TreeNode(40));
        root.getRight().setRight(new TreeNode(50));

        System.out.println(tree);
        TreeTraversal treeTraversal = new PreorderRecursiveTraversal(tree);

        System.out.println("Preorder traversal Recursive: ");
        for (int i : treeTraversal.traverse()) {
            System.out.print(i + " ");
        }
    }
}
