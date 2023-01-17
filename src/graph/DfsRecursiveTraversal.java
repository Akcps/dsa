package graph;

import java.util.ArrayList;
import java.util.List;

public class DfsRecursiveTraversal implements Traversal {
    private Graph graph;

    public DfsRecursiveTraversal(Graph graph) {
        this.graph = graph;
    }

    @Override
    public List<Integer> traverse() {
        List<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[graph.getVerticesCount()];
        for (int i = 0; i < graph.getVerticesCount(); i++) {
            if (!visited[i]) {
                dfsRecursive(i, visited, result);
            }
        }
        return result;
    }

    private void dfsRecursive(int source, boolean[] visited, List<Integer> result) {
        result.add(source);
        visited[source] = true;
        for (int adj : graph.adjacent(source)) {
            if (!visited[adj]) {
                visited[adj] = true;
                dfsRecursive(adj, visited, result);
            }
        }
    }
}
