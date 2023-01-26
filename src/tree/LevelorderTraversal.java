package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelorderTraversal implements TreeTraversal{
    private Tree tree;

    public LevelorderTraversal(Tree tree) {
        this.tree = tree;
    }

    @Override
    public List<Integer> traverse() {
        List<Integer> result = new ArrayList<>();
        if (tree.getRoot() == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(tree.getRoot());
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            result.add(current.getData());
            if (current.getLeft() != null) {
                queue.add(current.getLeft());
            }
            if (current.getRight() != null) {
                queue.add(current.getRight());
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
        TreeTraversal treeTraversal = new LevelorderTraversal(tree);

        System.out.println("Level order traversal: ");
        for (int i : treeTraversal.traverse()) {
            System.out.print(i + " ");
        }
    }
}
