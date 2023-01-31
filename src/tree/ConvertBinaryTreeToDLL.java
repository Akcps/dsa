package tree;

public class ConvertBinaryTreeToDLL {
    public Tree tree;
    private TreeNode prev;

    public ConvertBinaryTreeToDLL(Tree tree) {
        this.tree = tree;
        this.prev = null;
    }

    public TreeNode convertToDLL(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode current = convertToDLL(root.getLeft());
        if (prev == null) {
            current = root;
        } else {
            root.setLeft(prev);
            prev.setLeft(root);
        }
        prev = root;
        convertToDLL(root.getRight());

        return current;
    }

    public static void main(String[] args) {
        Tree tree = new Tree(10);
        TreeNode root = tree.getRoot();
        root.setLeft(new TreeNode(20));
        root.setRight(new TreeNode(30));
        root.getRight().setLeft(new TreeNode(40));
        root.getRight().setRight(new TreeNode(50));

        System.out.println(tree);
        ConvertBinaryTreeToDLL convertBinaryTreeToDLL = new ConvertBinaryTreeToDLL(tree);
        TreeNode head = convertBinaryTreeToDLL.convertToDLL(tree.getRoot());
        System.out.println(head);
    }
}
