package graph;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestDistanceUndirectedUnweightedGraph {
    private Graph graph;

    public ShortestDistanceUndirectedUnweightedGraph(Graph graph) {
        this.graph = graph;
    }

    public int[] shortestDistance(int source) {
        boolean[] visited = new boolean[graph.getVerticesCount()];
        int[] distance = new int[graph.getVerticesCount()];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < graph.getVerticesCount(); i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        distance[source] = 0;
        queue.add(source);
        visited[source] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int adj : graph.adjacent(node)) {
                if (!visited[adj]) {
                    visited[adj] = true;
                    distance[adj] = distance[node] + 1;
                    queue.add(adj);
                }
            }
        }
        return distance;
    }

    public static void main(String[] args) {
        Graph graph = new UndirectedGraphAdjacencyList(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);

        ShortestDistanceUndirectedUnweightedGraph sd = new ShortestDistanceUndirectedUnweightedGraph(graph);
        int[] distance = sd.shortestDistance(0);
        for (int i = 0; i < graph.getVerticesCount(); i++) {
            System.out.println(i + ": " + distance[i]);
        }
    }
}
