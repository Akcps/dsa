package disjointset;

public class DisjointSetUnionByRank implements DisjointSet {
    private final int[] parent;
    private final int[] rank;

    public DisjointSetUnionByRank(int length) {
        parent = new int[length];
        rank = new int[length];
        for (int i = 0; i < length; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    @Override
    public void union(int x, int y) {
        int xParent = find(x);
        int yParent = find(y);

        if (xParent == yParent) {
            return;
        }

        if (rank[xParent] < rank[yParent]) {
            parent[xParent] = yParent;
        } else if (rank[xParent] > rank[yParent]) {
            parent[yParent] = xParent;
        } else {
            parent[yParent] = xParent;
            rank[xParent]++;
        }
    }

    @Override
    public int find(int x) {
        int p = parent[x];
        if (p == x) {
            return p;
        }
        return find(p);
    }

    public static void main(String[] args) {
        DisjointSet ds = new DisjointSetUnionByRank(5);
        ds.union(0, 2);
        ds.union(2, 4);
        ds.union(1, 3);
        for (int i = 0; i < 5; i++) {
            System.out.println("parent: " + i + ": " + ds.find(i));
        }
    }
}
