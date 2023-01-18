package graph;

import java.util.Arrays;

public class PrimMinimumSpanningTree {
    private final WeightedGraph graph;

    public PrimMinimumSpanningTree(WeightedGraph graph) {
        this.graph = graph;
    }

    public int mst() {
        int totalLength = 0;
        int[] distance = new int[graph.getVerticesCount()];
        boolean[] completed = new boolean[graph.getVerticesCount()];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[0] = 0;
        for (int k = 0; k < graph.getVerticesCount(); k++) {
            int u = -1;
            for (int i = 0; i < graph.getVerticesCount(); i++) {
                if (!completed[i] && (u == -1 || distance[i] < distance[u])) {
                    u = i;
                }
            }
            completed[u] = true;
            totalLength += distance[u];

            for (Edge v : graph.adjacent(u)) {
                if (!completed[v.getDestination()]) {
                    distance[v.getDestination()] = Math.min(distance[v.getDestination()], v.getWeight());
                }
            }
        }
        return totalLength;
    }

    public static void main(String[] args) {
        WeightedGraph g = new UndirectedWeightedGraph(4);

        g.addEdge(0, 1, 9);
        g.addEdge(0, 2, 10);
        g.addEdge(0, 3, 5);
        g.addEdge(1, 2, 8);
        g.addEdge(2, 3, 7);

        System.out.print(g);
        System.out.println(g.adjacent(1));
        System.out.println(g.degree(2));

        PrimMinimumSpanningTree tree = new PrimMinimumSpanningTree(g);
        System.out.println("distance : " + tree.mst());
    }
}
