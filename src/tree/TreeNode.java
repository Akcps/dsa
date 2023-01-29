package tree;

public class TreeNode {
    private TreeNode left;
    private TreeNode right;
    private int data;

    public TreeNode() {
        this.data = 0;
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
        StringBuilder sb = new StringBuilder();
        sb.append("TreeNode {");
        sb.append("left: ");
        if (left != null) {
            sb.append(left.data);
        } else {
            sb.append("null");
        }
        sb.append(", ");
        sb.append("right: ");
        if (right != null) {
            sb.append(right.data);
        } else {
            sb.append("null");
        }
        sb.append("}");
        return sb.toString();
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.setLeft(new TreeNode(2));
        node.setRight(new TreeNode(3));
        System.out.println(node);
    }
}
