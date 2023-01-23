package disjointset;

public class DisjointSetPathCompression implements DisjointSet {
    private final int[] parent;

    public DisjointSetPathCompression(int size) {
        this.parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }

    @Override
    public void union(int x, int y) {
        int xParent = find(x);
        int yParent = find(y);
        parent[xParent] = yParent;
    }

    @Override
    public int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        parent[x] = find(x);
        return parent[x];
    }

    public static void main(String[] args) {
        DisjointSet ds = new DisjointSetPathCompression(5);
        ds.union(0, 2);
        ds.union(2, 4);
        ds.union(1, 3);
        for (int i = 0; i < 5; i++) {
            System.out.println("parent: " + i + ": " + ds.find(i));
        }
    }
}
