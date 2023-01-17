package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DfsIterativeTraversal implements Traversal {
    private Graph graph;

    public DfsIterativeTraversal(Graph graph) {
        this.graph = graph;
    }

    @Override
    public List<Integer> traverse() {
        List<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[graph.getVerticesCount()];
        for (int i = 0; i < graph.getVerticesCount(); i++) {
            if (!visited[i]) {
                dfsIterative(i, visited, result);
            }
        }
        return result;
    }

    private void dfsIterative(int source, boolean[] visited, List<Integer> result) {
        Stack<Integer> stack = new Stack<>();
        stack.add(source);
        visited[source] = true;

        while (!stack.isEmpty()) {
            int node = stack.pop();
            result.add(node);
            for (int adj : graph.adjacent(node)) {
                if (!visited[adj]) {
                    visited[adj] = true;
                    stack.add(adj);
                }
            }
        }
    }
}
