package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BfsTraversal implements Traversal {
    private Graph graph;

    public BfsTraversal(Graph graph) {
        this.graph = graph;
    }

    @Override
    public List<Integer> traverse() {
        List<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[graph.getVerticesCount()];
        for (int i = 0; i < graph.getVerticesCount(); i++) {
            if (!visited[i]) {
                bfs(i, visited, result);
            }
        }
        return result;
    }

    private void bfs(int source, boolean[] visited, List<Integer> result) {

        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);
            for (int adj : graph.adjacent(node)) {
                if (!visited[adj]) {
                    visited[adj] = true;
                    queue.add(adj);
                }
            }
        }
    }
}
