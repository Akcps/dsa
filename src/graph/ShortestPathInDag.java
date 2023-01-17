package graph;

import java.util.List;

public class ShortestPathInDag {
    private final WeightedGraph graph;

    public ShortestPathInDag(WeightedGraph graph) {
        this.graph = graph;
    }

    public int[] shortestPath(int source) {
        int[] distance = new int[graph.getVerticesCount()];
        for (int i = 0; i < graph.getVerticesCount(); i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        distance[source] = 0;

        List<Integer> sortedGraph = graph.bfs();
        for (int vertex: sortedGraph) {
            for (Edge e : graph.adjacent(vertex)) {
                if (distance[e.getSource()] != Integer.MAX_VALUE) {
                    if (distance[e.getDestination()] > distance[e.getSource()] + e.getWeight()) {
                        distance[e.getDestination()] = distance[e.getSource()] + e.getWeight();
                    }
                }
            }
        }
        return distance;
    }

    public static void main(String args[]) {
        WeightedGraph g = new DirectedWeightedGraph(4);

        g.addEdge(0, 1, 1);
        g.addEdge(1, 3, 2);
        g.addEdge(1, 2, 3);
        g.addEdge(2, 3, 4);

        System.out.print(g);
        System.out.println("Bfs :" + g.bfs());

        ShortestPathInDag path = new ShortestPathInDag(g);
        int[] distance = path.shortestPath(1);
        System.out.println();
        System.out.println("Shortest Path");
        for (int i = 0; i < g.getVerticesCount(); i++) {
            String val = distance[i] != Integer.MAX_VALUE ? String.valueOf(distance[i]) : "INF";
            System.out.println(i + ": " + val);
        }
    }
}
