package tree;

import java.util.*;

public class SpiralTraversalLineByLine {
    private Tree tree;

    public SpiralTraversalLineByLine(Tree tree) {
        this.tree = tree;
    }


    public List<List<Integer>> traverse() {
        List<List<Integer>> result = new ArrayList<>();
        if (tree.getRoot() == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(tree.getRoot());

        boolean leftToRight = true;
        while (!queue.isEmpty()) {
            List<Integer> currentLevel = new ArrayList<>();
            Stack<Integer> stack = new Stack<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                if (current.getLeft() != null) {
                    queue.add(current.getLeft());
                }
                if (current.getRight() != null) {
                    queue.add(current.getRight());
                }
                if (leftToRight) {
                    currentLevel.add(current.getData());
                } else {
                    stack.add(current.getData());
                }
            }
            if (!leftToRight) {
                while (!stack.isEmpty()) {
                    currentLevel.add(stack.pop());
                }
            }
            leftToRight = !leftToRight;
            result.add(currentLevel);
        }
        return result;
    }

    public static void main(String[] args) {
        Tree tree = new Tree(1);
        TreeNode root = tree.getRoot();
        root.setLeft(new TreeNode(2));
        root.setRight(new TreeNode(3));
        root.getLeft().setLeft(new TreeNode(4));
        root.getLeft().setRight(new TreeNode(5));
        root.getRight().setLeft(new TreeNode(6));
        root.getRight().setRight(new TreeNode(7));
        root.getLeft().getLeft().setLeft(new TreeNode(8));
        root.getLeft().getLeft().setRight(new TreeNode(9));
        root.getRight().getRight().setLeft(new TreeNode(10));

        System.out.println(tree);
        SpiralTraversalLineByLine treeTraversal = new SpiralTraversalLineByLine(tree);

        System.out.println("Spiral Level order traversal line by line: ");
        for (List<Integer> level : treeTraversal.traverse()) {
            System.out.println(level);
        }
    }
}
