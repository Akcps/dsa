package tree;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConstructTreeFromPostorderAndInorder {
    private int[] postorder;
    private int[] inorder;
    private int postIndex;

    public ConstructTreeFromPostorderAndInorder(int[] postorder, int[] inorder) {
        this.postorder = postorder;
        this.inorder = inorder;
        this.postIndex = inorder.length - 1;
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
        if (left < 0 || left > right || right >= postorder.length) {
            return null;
        }
        int val = postorder[postIndex--];
        TreeNode newNode = new TreeNode(val);
        newNode.setRight(constructTree(inorderMap, inorderMap.get(val) + 1, right));
        newNode.setLeft(constructTree(inorderMap, left, inorderMap.get(val) - 1));
        return newNode;
    }

    public static void main(String[] args) {
        int[] inorder = new int[]{9, 3, 15, 20, 7};
        int[] postorder = new int[]{9, 15, 7, 20, 3};

        ConstructTreeFromPostorderAndInorder c = new ConstructTreeFromPostorderAndInorder(postorder, inorder);
        TreeNode root = c.construct();
        Tree t = new Tree(root);
        System.out.println(root);

        LevelorderTraversalLineByLine lt = new LevelorderTraversalLineByLine(t);
        System.out.println("Level order traversal: ");
        for (List<Integer> level : lt.traverse()) {
            System.out.println(level);
        }
    }

}
