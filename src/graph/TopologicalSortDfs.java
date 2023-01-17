package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TopologicalSortDfs implements TopologicalSort {
    private Graph graph;

    public TopologicalSortDfs(Graph graph) {
        this.graph = graph;
    }

    @Override
    // Time Complexity O(V + E)
    public List<Integer> sort() {
        List<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[graph.getVerticesCount()];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < graph.getVerticesCount(); i++) {
            if (!visited[i]) {
                dfs(i, visited, stack);
            }
        }

        while (!stack.empty()) {
            result.add(stack.pop());
        }
        return result;
    }

    private void dfs(int source, boolean[] visited, Stack<Integer> stack) {
        visited[source] = true;
        for (int adj: graph.adjacent(source)) {
            if (!visited[adj]) {
                dfs(adj, visited, stack);
            }
        }
        stack.push(source);
    }

    public static void main(String[] args) {
        Graph graph = new DirectedGraphAdjacencyList(5);
        graph.addEdge(0,1);
        graph.addEdge(1, 3);
        graph.addEdge(3, 4);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);

        TopologicalSort sort = new TopologicalSortDfs(graph);
        System.out.println(sort.sort());
    }
}
