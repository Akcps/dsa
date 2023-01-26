package tree;

import java.util.ArrayList;
import java.util.List;

public class InorderRecursiveTraversal implements TreeTraversal {
    private Tree tree;

    public InorderRecursiveTraversal(Tree tree) {
        this.tree = tree;
    }

    @Override
    public List<Integer> traverse() {
        List<Integer> result = new ArrayList<>();
        inorder(tree.getRoot(), result);
        return result;
    }

    private void inorder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        inorder(root.getLeft(), result);
        result.add(root.getData());
        inorder(root.getRight(), result);
    }

    public static void main(String[] args) {
        Tree tree = new Tree(10);
        TreeNode root = tree.getRoot();
        root.setLeft(new TreeNode(20));
        root.setRight(new TreeNode(30));
        root.getRight().setLeft(new TreeNode(40));
        root.getRight().setRight(new TreeNode(50));

        System.out.println(tree);
        TreeTraversal treeTraversal = new InorderRecursiveTraversal(tree);

        System.out.println("Inorder traversal Recursive: ");
        for (int i: treeTraversal.traverse()) {
            System.out.print(i + " ");
        }
    }
}
