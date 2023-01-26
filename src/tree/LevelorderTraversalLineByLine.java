package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelorderTraversalLineByLine {
    private Tree tree;

    public LevelorderTraversalLineByLine(Tree tree) {
        this.tree = tree;
    }


    public List<List<Integer>> traverse() {
        List<List<Integer>> result = new ArrayList<>();
        if (tree.getRoot() == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(tree.getRoot());

        while (!queue.isEmpty()) {
            List<Integer> currentLevel = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                currentLevel.add(current.getData());
                if (current.getLeft() != null) {
                    queue.add(current.getLeft());
                }
                if (current.getRight() != null) {
                    queue.add(current.getRight());
                }
            }
            result.add(currentLevel);
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
        LevelorderTraversalLineByLine treeTraversal = new LevelorderTraversalLineByLine(tree);

        System.out.println("Level order traversal line by line: ");
        for (List<Integer> level : treeTraversal.traverse()) {
            System.out.println(level);
        }
    }
}
