package tree;

public class ChildSumProperty {
    private Tree tree;

    public ChildSumProperty(Tree tree) {
        this.tree = tree;
    }

    public boolean validate() {
        return validate(tree.getRoot());
    }

    private boolean validate(TreeNode root) {
        if (root == null) {
            return true;
        }

        if (root.getLeft() == null && root.getRight() == null) {
            return true;
        }

        int sum = 0;
        if (root.getLeft() != null) {
            sum += root.getLeft().getData();
        }
        if (root.getRight() != null) {
            sum += root.getRight().getData();
        }
        return root.getData() == sum && validate(root.getLeft()) && validate(root.getRight());
    }

    public static void main(String[] args) {
        Tree tree = new Tree(10);
        TreeNode root = tree.getRoot();
        root.setLeft(new TreeNode(20));
        root.setRight(new TreeNode(30));
        root.getRight().setLeft(new TreeNode(40));
        root.getRight().setRight(new TreeNode(50));

        System.out.println(tree);

        ChildSumProperty c = new ChildSumProperty(tree);
        System.out.println(c.validate());

        tree = new Tree(20);
        root = tree.getRoot();
        root.setLeft(new TreeNode(8));
        root.setRight(new TreeNode(12));
        root.getLeft().setLeft(new TreeNode(3));
        root.getLeft().setRight(new TreeNode(5));

        System.out.println(tree);

        c = new ChildSumProperty(tree);
        System.out.println(c.validate());

    }
}
