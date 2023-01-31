package tree;

public class Tree {
    private TreeNode root;

    public Tree(int data) {
        this.root = new TreeNode(data);
    }

    public Tree(TreeNode root) {
        this.root = root;
    }

    public TreeNode getRoot() {
        return root;
    }

    @Override
    public String toString() {
        return "Tree{" +
                "root=" + root +
                '}';
    }
}
