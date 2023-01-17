package graph;

import java.util.*;

public class UndirectedGraphAdjacencyMatrix implements Graph {
    private final boolean[][] adjacencyMatrix;
    private final int noOfVertices;

    public UndirectedGraphAdjacencyMatrix(int noOfVertices) {
        this.noOfVertices = noOfVertices;
        this.adjacencyMatrix = new boolean[this.noOfVertices][this.noOfVertices];
    }

    public boolean[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public int getNoOfVertices() {
        return noOfVertices;
    }

    @Override
    public int getVerticesCount() {
        return noOfVertices;
    }

    @Override
    // Time Complexity: O(1)
    public boolean addEdge(int source, int destination) {
        if (!this.validateSourceAndDestination(source, destination)) {
            return false;
        }
        this.adjacencyMatrix[source][destination] = true;
        this.adjacencyMatrix[destination][source] = true;
        return true;
    }

    @Override
    // Time Complexity: O(1)
    public boolean removeEdge(int source, int destination) {
        if (!this.validateSourceAndDestination(source, destination)) {
            return false;
        }

        this.adjacencyMatrix[source][destination] = false;
        this.adjacencyMatrix[destination][source] = false;
        return false;
    }

    @Override
    // Time Complexity: O(v)
    public int degree(int node) {
        int count = 0;
        for (boolean j : this.adjacencyMatrix[node]) {
            if (j) {
                count++;
            }
        }
        return count;
    }

    @Override
    // Time Complexity: O(V)
    public List<Integer> adjacent(int node) {
        List<Integer> adjacent = new ArrayList<>();
        for (int i = 0; i < this.noOfVertices; i++) {
            if (this.adjacencyMatrix[node][i]) {
                adjacent.add(i);
            }
        }
        return adjacent;
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
        Traversal t = new DfsIterativeTraversal(this);
        return t.traverse();
//        Traversal t = new DfsRecursiveTraversal(this);
//        return t.traverse();
    }

    private boolean validateSourceAndDestination(int source, int destination) {
        if (source < 0 || source >= noOfVertices || destination < 0 || destination >= noOfVertices) {
            System.out.println("source or destination is invalid");
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < noOfVertices; i++) {
            s.append(i).append(": ");
            for (boolean j : adjacencyMatrix[i]) {
                s.append(j ? 1 : 0).append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    @Override
    // Time Complexity: O(1)
    public boolean hasEdge(int source, int destination) {
        return this.validateSourceAndDestination(source, destination) && this.adjacencyMatrix[source][destination];
    }

    public static void main(String args[]) {
        Graph g = new UndirectedGraphAdjacencyMatrix(4);

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
