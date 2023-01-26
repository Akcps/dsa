package tree;

import java.util.ArrayList;
import java.util.List;

public class TreeKDistanceNode {
    private Tree tree;

    public TreeKDistanceNode(Tree tree) {
        this.tree = tree;
    }

    public List<Integer> kDistanceNodes(int k) {
        List<Integer> result = new ArrayList<>();
        kDistanceNodes(tree.getRoot(), result, k);
        return result;
    }

    private void kDistanceNodes(TreeNode root, List<Integer> result, int k) {
        if (root == null) {
            return;
        }
        if (k == 0) {
            result.add(root.getData());
        }
        kDistanceNodes(root.getLeft(), result, k - 1);
        kDistanceNodes(root.getRight(), result, k - 1);
    }

    public static void main(String[] args) {
        Tree tree = new Tree(10);
        TreeNode root = tree.getRoot();
        root.setLeft(new TreeNode(20));
        root.setRight(new TreeNode(30));
        root.getRight().setLeft(new TreeNode(40));
        root.getRight().setRight(new TreeNode(50));

        System.out.println(tree);
        TreeKDistanceNode t = new TreeKDistanceNode(tree);

        System.out.println("Nodes at Distance K(1): ");
        for (int i : t.kDistanceNodes(1)) {
            System.out.print(i + " ");
        }
    }

}
