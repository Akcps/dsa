package graph;

import java.util.*;

public class KosarajuStronglyConnectedComponent {
    private Graph graph;

    public KosarajuStronglyConnectedComponent(Graph graph) {
        this.graph = graph;
    }

    public void printStronglyConnectedComponents() {
        List<Integer> dfs = graph.dfs();
        Map<Integer, Set<Integer>> reverseAdjacencyList = reverseAdjacencyList();
        Graph reverseGraph = new DirectedGraphAdjacencyList(graph.getVerticesCount());
        for (int i = 0; i < graph.getVerticesCount(); i++) {
            for (int adj: reverseAdjacencyList.get(i)) {
                reverseGraph.addEdge(i, adj);
            }
        }
        BfsTraversalStronglyConnectedComponent traversal = new BfsTraversalStronglyConnectedComponent(reverseGraph);
        traversal.traverse();
    }

    private Map<Integer, Set<Integer>> reverseAdjacencyList() {
        Map<Integer, Set<Integer>> reverseAdjList = new HashMap<>();
        for (int i = 0; i < graph.getVerticesCount(); i++) {
            reverseAdjList.put(i, new HashSet<>());
        }
        for (int i = 0; i < graph.getVerticesCount(); i++) {
            for (int adj : graph.adjacent(i)) {
                reverseAdjList.get(adj).add(i);
            }
        }
        return reverseAdjList;
    }


    public static void main(String[] args) {
        Graph graph = new DirectedGraphAdjacencyList(6);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 0);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 4);

        KosarajuStronglyConnectedComponent ks = new KosarajuStronglyConnectedComponent(graph);
        ks.printStronglyConnectedComponents();

    }
}
