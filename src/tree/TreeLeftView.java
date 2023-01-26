package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeLeftView {
    private Tree tree;

    public TreeLeftView(Tree tree) {
        this.tree = tree;
    }

    public List<Integer> leftView() {
        List<Integer> result = new ArrayList<>();
        if (tree == null || tree.getRoot() == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(tree.getRoot());

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                if (i == 0) {
                    result.add(current.getData());
                }
                if (current.getLeft() != null) {
                    queue.add(current.getLeft());
                }
                if (current.getRight() != null) {
                    queue.add(current.getRight());
                }
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
        TreeLeftView lv = new TreeLeftView(tree);

        System.out.println("Left View: ");
        for (int i : lv.leftView()) {
            System.out.print(i + " ");
        }
    }
}
