package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KruskalMinimumSpanningTree {
    private final List<Edge> edges;
    private final int[] parent;
    private final int[] rank;
    private final int noOfVertices;

    public KruskalMinimumSpanningTree(List<Edge> edges, int noOfVertices) {
        this.noOfVertices = noOfVertices;
        this.edges = edges;
        Collections.sort(edges);
        this.parent = new int[noOfVertices];
        this.rank = new int[noOfVertices];
        for (int i = 0; i < this.noOfVertices; i++) {
            this.parent[i] = i;
        }
    }

    // union by rank
    private void union(int x, int y) {
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

    // path compression
    private int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        parent[x] = find(parent[x]);
        return parent[x];
    }

    public int mst() {
        int result = 0;

        for (int i = 0, s = 0; i < edges.size() && s < this.noOfVertices; i++) {
            Edge e = edges.get(i);
            int uParent = find(e.getSource());
            int vParent = find(e.getDestination());
            if (uParent != vParent) {
                result += e.getWeight();
                union(uParent, vParent);
                s++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 10));
        edges.add(new Edge(0, 2, 8));
        edges.add(new Edge(1, 2, 5));
        edges.add(new Edge(1, 3, 3));
        edges.add(new Edge(2, 3, 4));
        edges.add(new Edge(2, 4, 12));
        edges.add(new Edge(3, 4, 15));

        KruskalMinimumSpanningTree kmst = new KruskalMinimumSpanningTree(edges, 5);
        System.out.println("Minimum spanning tree:  " + kmst.mst());
    }
}
