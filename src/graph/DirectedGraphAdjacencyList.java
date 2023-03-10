package graph;

import java.util.*;

public class DirectedGraphAdjacencyList implements Graph {
    private Map<Integer, Set<Integer>> adjacencyList;
    private final int noOfVertices;
    private int[] inDegree;

    public DirectedGraphAdjacencyList(int noOfVertices) {
        this.noOfVertices = noOfVertices;
        this.initAdjacencyList(noOfVertices);
        this.inDegree = new int[noOfVertices];
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
        this.inDegree[destination]++;
        return this.adjacencyList.get(source).add(destination);

    }

    @Override
    // Time Complexity: O(V)
    public boolean removeEdge(int source, int destination) {
        if (!this.validateSourceAndDestination(source, destination)) {
            return false;
        }
        this.adjacencyList.get(source).remove(destination);
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
            if (destination == i) {
                return true;
            }
        }
        return false;
    }

    @Override
    // Time Complexity: O(1)
    public int degree(int node) {
        return inDegree[node];
    }

    @Override
    // Time Complexity: O(V)
    public List<Integer> adjacent(int node) {
        return this.adjacencyList.get(node).stream().toList();
    }


    @Override
    // Time Complexity: O(V + E)
    public List<Integer> bfs() {
        Traversal t = new BfsTraversal(this);
        return t.traverse();
    }

    @Override
    // Time Complexity: O(V + E)
    public List<Integer> dfs() {
//        Traversal t = new DfsIterativeTraversal(this);
//        return t.traverse();
        Traversal t = new DfsRecursiveTraversal(this);
        return t.traverse();
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


    @Override
    public int getVerticesCount() {
        return this.noOfVertices;
    }

    public static void main(String args[]) {
        Graph g = new DirectedGraphAdjacencyList(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);

        System.out.print(g);
        System.out.println(g.adjacent(2));
        System.out.println(g.degree(2));
        System.out.println("Bfs :" + g.bfs());
        System.out.println("Dfs :" + g.dfs());
    }
}
