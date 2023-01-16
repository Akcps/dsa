package graph;

import java.util.*;

public class GraphAdjacencyList implements Graph {
    private Map<Integer, Set<Integer>> adjacencyList;
    private final int noOfVertices;

    public GraphAdjacencyList(int noOfVertices) {
        this.noOfVertices = noOfVertices;
        this.initAdjacencyList(noOfVertices);
    }

    private void initAdjacencyList(int noOfVertices) {
        this.adjacencyList = new HashMap<>();
        for (int i = 0; i < noOfVertices; i++) {
            this.adjacencyList.put(i, new HashSet<>());
        }
    }

    public Map<Integer, Set<Integer>> getAdjacencyList() {
        return adjacencyList;
    }

    public int getNoOfVertices() {
        return noOfVertices;
    }

    @Override
    // Time Complexity: O(1)
    public boolean addEdge(int source, int destination) {
        if (!this.validateSourceAndDestination(source, destination)) {
            return false;
        }
        return this.adjacencyList.get(source).add(destination) && this.adjacencyList.get(destination).add(source);
    }

    @Override
    // Time Complexity: O(V)
    public boolean removeEdge(int source, int destination) {
        if (!this.validateSourceAndDestination(source, destination)) {
            return false;
        }
        this.adjacencyList.get(source).remove(destination);
        this.adjacencyList.get(destination).remove(source);
        return true;
    }

    private boolean validateSourceAndDestination(int source, int destination) {
        if (source < 0 || source >= noOfVertices || destination < 0 || destination >= noOfVertices) {
            System.out.println("source or destination is invalid");
            return false;
        }
        return true;
    }

    @Override
    // Time Complexity: O(V)
    public boolean hasEdge(int source, int destination) {
        if (!validateSourceAndDestination(source, destination)) {
            return false;
        }
        Set<Integer> adjacentVertices = this.adjacencyList.get(source);
        for (int i : adjacentVertices) {
            if (source == i) {
                return true;
            }
        }
        return false;
    }

    @Override
    // Time Complexity: O(1)
    public int degree(int node) {
        return this.adjacencyList.get(node).size();
    }

    @Override
    // Time Complexity: O(V)
    public List<Integer> adjacent(int node) {
        return this.adjacencyList.get(node).stream().toList();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < noOfVertices; i++) {
            s.append(i).append(": ");
            for (int j : this.adjacencyList.get(i)) {
                s.append(j).append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    public static void main(String args[]) {
        Graph g = new GraphAdjacencyList(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);

        System.out.print(g);
        System.out.println(g.adjacent(2));
        System.out.println(g.degree(2));
    }
}
