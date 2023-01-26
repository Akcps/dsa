package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PostorderIterativeTraversal implements TreeTraversal {
    private Tree tree;

    public PostorderIterativeTraversal(Tree tree) {
        this.tree = tree;
    }

    @Override
    public List<Integer> traverse() {
        List<Integer> result = new ArrayList<>();
        if (tree.getRoot() == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.add(tree.getRoot());
        while (!stack.isEmpty()) {
            TreeNode current = stack.peek();
            if (current.getRight() == null && current.getLeft() == null) {
                result.add(stack.pop().getData());
            }

            if (current.getRight() != null) {
                stack.add(current.getRight());
                current.setRight(null);
            }
            if (current.getLeft() != null) {
                stack.add(current.getLeft());
                current.setLeft(null);
            }
        }
        return result;
    }


    public static void main(String[] args) {
        Tree tree = new Tree(10);
        TreeNode root = tree.getRoot();
        root.setLeft(new TreeNode(20));
        root.setRight(new TreeNode(30));
        root.getRight().setLeft(new TreeNode(40));
        root.getRight().setRight(new TreeNode(50));

        System.out.println(tree);
        TreeTraversal treeTraversal = new PostorderIterativeTraversal(tree);

        System.out.println("Postorder traversal Iterative: ");
        for (int i : treeTraversal.traverse()) {
            System.out.print(i + " ");
        }
    }
}
