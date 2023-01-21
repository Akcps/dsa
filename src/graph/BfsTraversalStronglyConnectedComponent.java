package graph;

import java.util.LinkedList;
import java.util.Queue;

public class BfsTraversalStronglyConnectedComponent {
    private Graph graph;

    public BfsTraversalStronglyConnectedComponent(Graph graph) {
        this.graph = graph;
    }

    public void traverse() {
        boolean[] visited = new boolean[graph.getVerticesCount()];
        for (int i = 0; i < graph.getVerticesCount(); i++) {
            System.out.println();
            if (!visited[i]) {
                bfs(i, visited);
            }
        }
    }

    private void bfs(int source, boolean[] visited) {

        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");
            for (int adj : graph.adjacent(node)) {
                if (!visited[adj]) {
                    visited[adj] = true;
                    queue.add(adj);
                }
            }
        }
    }
}
