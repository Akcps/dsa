package tree;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConstructTreeFromPreorderAndInorder {
    private int[] preorder;
    private int[] inorder;
    private int preIndex;

    public ConstructTreeFromPreorderAndInorder(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;
        this.preIndex = 0;
    }

    private Map<Integer, Integer> constructInorderMap() {
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < this.inorder.length; i++) {
            inorderMap.put(this.inorder[i], i);
        }
        return inorderMap;
    }

    public TreeNode construct() {
        TreeNode root = constructTree(constructInorderMap(), 0, inorder.length - 1);
        return root;
    }

    private TreeNode constructTree(Map<Integer, Integer> inorderMap, int left, int right) {
        if (left < 0 || left > right || right >= preorder.length) {
            return null;
        }
        int val = preorder[preIndex++];
        TreeNode newNode = new TreeNode(val);
        newNode.setLeft(constructTree(inorderMap, left, inorderMap.get(val) - 1));
        newNode.setRight(constructTree(inorderMap, inorderMap.get(val) + 1, right));
        return newNode;
    }

    public static void main(String[] args) {
        int[] inorder = new int[]{20, 10, 30};
        int[] preorder = new int[]{10, 20, 30};

        ConstructTreeFromPreorderAndInorder c = new ConstructTreeFromPreorderAndInorder(preorder, inorder);
        TreeNode root = c.construct();
        Tree t = new Tree(root);
        System.out.println(root);

        LevelorderTraversalLineByLine lt = new LevelorderTraversalLineByLine(t);
        System.out.println("Level order traversal: ");
        for (List<Integer> level : lt.traverse()) {
            System.out.println(level);
        }

        inorder = new int[]{40, 20, 50, 10, 30, 80, 70, 90};
        preorder = new int[]{10, 20, 40, 50, 30, 70, 80, 90};

        c = new ConstructTreeFromPreorderAndInorder(preorder, inorder);
        root = c.construct();
        t = new Tree(root);
        System.out.println(root);
        lt = new LevelorderTraversalLineByLine(t);
        System.out.println("Level order traversal: ");
        for (List<Integer> level : lt.traverse()) {
            System.out.println(level);
        }
    }

}
