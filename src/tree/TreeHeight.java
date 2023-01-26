package tree;


public class TreeHeight {
    private Tree tree;

    public TreeHeight(Tree tree) {
        this.tree = tree;
    }

    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(height(root.getLeft()), height(root.getRight()));
    }


    public static void main(String[] args) {
        Tree tree = new Tree(10);
        TreeNode root = tree.getRoot();
        root.setLeft(new TreeNode(20));
        root.setRight(new TreeNode(30));
        root.getRight().setLeft(new TreeNode(40));
        root.getRight().setRight(new TreeNode(50));

        System.out.println(tree);


        System.out.println("Height of a tree");

        TreeHeight th = new TreeHeight(tree);
        System.out.println(th.height(tree.getRoot()));
    }
}
