package graph;

import java.util.LinkedList;
import java.util.Queue;

public class CheckCycleInDirectedGraphUsingBfs {
    private Graph graph;

    public CheckCycleInDirectedGraphUsingBfs(Graph graph) {
        this.graph = graph;
    }

    public boolean hasCycle() {
        int[] inDegree = new int[graph.getVerticesCount()];

        int count = 0;
        for (int i = 0; i < graph.getVerticesCount(); i++) {
            inDegree[i] = graph.degree(i);
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < graph.getVerticesCount(); i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int node = queue.poll();
            count++;
            for (int adj : graph.adjacent(node)) {
                inDegree[adj]--;
                if (inDegree[adj] == 0) {
                    queue.add(adj);
                }
            }
        }
        return count != graph.getVerticesCount();
    }

    public static void main(String[] args) {
        Graph graph = new DirectedGraphAdjacencyList(5);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);
        graph.addEdge(4, 3);

        CheckCycleInDirectedGraphUsingBfs graphCheck = new CheckCycleInDirectedGraphUsingBfs(graph);
        System.out.println(graphCheck.hasCycle());


        graph = new DirectedGraphAdjacencyList(5);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(4, 1);
        graph.addEdge(4, 3);

        graphCheck = new CheckCycleInDirectedGraphUsingBfs(graph);
        System.out.println(graphCheck.hasCycle());
    }


}
