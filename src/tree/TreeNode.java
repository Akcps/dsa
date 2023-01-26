package tree;

public class TreeNode {
    private TreeNode left;
    private TreeNode right;
    private int data;

    public TreeNode() {
    }

    public TreeNode(int data) {
        this.data = data;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public void setData(int data) {
        this.data = data;
    }

    public TreeNode getLeft() {
        return left;
    }

    public TreeNode getRight() {
        return right;
    }

    public int getData() {
        return data;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "left=" + left.data +
                ", right=" + right.data +
                ", data=" + data +
                '}';
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.setLeft(new TreeNode(2));
        node.setRight(new TreeNode(3));
        System.out.println(node);
    }
}
