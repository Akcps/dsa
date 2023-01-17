package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TopologicalSortBfs implements TopologicalSort {
    private Graph graph;

    public TopologicalSortBfs(Graph graph) {
        this.graph = graph;
    }

    @Override
    // Time Complexity: O(V + E)
    public List<Integer> sort() {
        int[] inDegree = new int[graph.getVerticesCount()];
        boolean[] visited = new boolean[graph.getVerticesCount()];

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < graph.getVerticesCount(); i++) {
            inDegree[i] = graph.degree(i);
        }

        for (int i = 0; i < graph.getVerticesCount(); i++) {
            System.out.println(inDegree[i]);
        }


        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < graph.getVerticesCount(); i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
                visited[i] = true;
            }
        }

        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);
            for (int adj : graph.adjacent(node)) {
                inDegree[adj]--;
                if (inDegree[adj] == 0) {
                    queue.add(adj);
                    visited[adj] = true;
                }
            }
        }
        return result;
    }


    public static void main(String[] args) {
        Graph graph = new DirectedGraphAdjacencyList(5);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(2, 3);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);

        TopologicalSort sort = new TopologicalSortBfs(graph);
        System.out.println(sort.sort());
    }
}
