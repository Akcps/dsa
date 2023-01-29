package tree;

public class CheckBalanced {
    private Tree tree;

    public CheckBalanced(Tree tree) {
        this.tree = tree;
    }

    public boolean check() {
        return isBalanced(tree.getRoot()) != -1;
    }

    private int isBalanced(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int lh = isBalanced(root.getLeft());
        if (lh == -1) {
            return  -1;
        }
        int rh = isBalanced(root.getRight());
        if (rh == -1) {
            return -1;
        }

        if (Math.abs(lh - rh) > 1) {
            return  -1;
        }
        return Math.max(lh, rh) + 1;
    }

    public static void main(String[] args) {
        Tree tree = new Tree(10);
        TreeNode root = tree.getRoot();
        root.setLeft(new TreeNode(20));
        root.setRight(new TreeNode(30));
        root.getRight().setLeft(new TreeNode(40));
        root.getRight().setRight(new TreeNode(50));

        System.out.println(tree);

        CheckBalanced c = new CheckBalanced(tree);
        System.out.println(c.check());

        tree = new Tree(20);
        root = tree.getRoot();
        root.setLeft(new TreeNode(8));
        root.getLeft().setLeft(new TreeNode(3));

        System.out.println(tree);

        c = new CheckBalanced(tree);
        System.out.println(c.check());
    }
}
