package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InorderIterativeTraversal implements TreeTraversal {
    private Tree tree;

    public InorderIterativeTraversal(Tree tree) {
        this.tree = tree;
    }

    @Override
    public List<Integer> traverse() {
        List<Integer> result = new ArrayList<>();
        if (tree.getRoot() == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = tree.getRoot();
        while (!stack.isEmpty() || current != null) {
            while (current != null) {
                stack.add(current);
                current = current.getLeft();
            }
            current = stack.pop();
            result.add(current.getData());
            current = current.getRight();
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
        TreeTraversal treeTraversal = new InorderIterativeTraversal(tree);

        System.out.println("Inorder traversal Iterative: ");
        for (int i : treeTraversal.traverse()) {
            System.out.print(i + " ");
        }
    }
}

