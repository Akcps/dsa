package graph;

import java.util.Arrays;


public class DijkstraShortestPath {
    private final WeightedGraph graph;

    public DijkstraShortestPath(WeightedGraph graph) {
        this.graph = graph;
    }

    public int[] shortestPath(int source) {
        int[] distance = new int[graph.getVerticesCount()];
        boolean[] completed = new boolean[graph.getVerticesCount()];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        for (int k = 0; k < graph.getVerticesCount(); k++) {
            int u = -1;
            // TODO: improve time complexity by using a Min Heap
            for (int i = 0; i < graph.getVerticesCount(); i++) {
                if (!completed[i] && (u == -1 || distance[i] < distance[u])) {
                    u = i;
                }
            }
            completed[u] = true;
            for (Edge v: graph.adjacent(u)) {
                if (!completed[v.getDestination()]) {
                    distance[v.getDestination()] = Math.min(distance[v.getDestination()], v.getWeight() + distance[u]);
                }
            }
        }
        return distance;
    }

    public static void main(String[] args) {
        WeightedGraph g = new UndirectedWeightedGraph(4);

        g.addEdge(0, 1, 50);
        g.addEdge(1, 3, 200);
        g.addEdge(1, 2, 30);
        g.addEdge(0, 2, 100);
        g.addEdge(2, 3, 20);

        System.out.print(g);
        System.out.println(g.adjacent(1));
        System.out.println(g.degree(2));

        DijkstraShortestPath shortestPath = new DijkstraShortestPath(g);
        System.out.println("Shortest path");
        int[] dist = shortestPath.shortestPath(0);
        for (int i = 0; i < dist.length; i++) {
            System.out.println(i +": " + dist[i]);
        }
    }
}
