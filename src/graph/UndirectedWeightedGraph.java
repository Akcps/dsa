package graph;

import java.util.*;

public class UndirectedWeightedGraph implements WeightedGraph {

    private Map<Integer, Set<Edge>> adjacencyList;
    private final int noOfVertices;

    public UndirectedWeightedGraph(int noOfVertices) {
        this.noOfVertices = noOfVertices;
        this.initAdjacencyList(noOfVertices);
    }

    private void initAdjacencyList(int noOfVertices) {
        this.adjacencyList = new HashMap<>();
        for (int i = 0; i < noOfVertices; i++) {
            this.adjacencyList.put(i, new HashSet<>());
        }
    }

    public Map<Integer, Set<Edge>> getAdjacencyList() {
        return adjacencyList;
    }

    public int getNoOfVertices() {
        return noOfVertices;
    }

    @Override
    // Time Complexity: O(1)
    public boolean addEdge(int source, int destination, int weight) {
        if (!this.validateSourceAndDestination(source, destination)) {
            return false;
        }
        return this.adjacencyList.get(source).add(new Edge(source, destination, weight)) &&
                this.adjacencyList.get(destination).add(new Edge(destination, source, weight));
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
        Set<Edge> adjacentVertices = this.adjacencyList.get(source);
        for (Edge i : adjacentVertices) {
            if (destination == i.getDestination()) {
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
    public List<Edge> adjacent(int node) {
        return this.adjacencyList.get(node).stream().toList();
    }


    @Override
    // Time Complexity: O(V + E)
    public List<Integer> bfs() {
        List<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[this.getVerticesCount()];
        for (int i = 0; i < this.getVerticesCount(); i++) {
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
            for (Edge adj : this.adjacent(node)) {
                if (!visited[adj.getDestination()]) {
                    visited[adj.getDestination()] = true;
                    queue.add(adj.getDestination());
                }
            }
        }
    }


    @Override
    // Time Complexity: O(V + E)
    public List<Integer> dfs() {
        // TODO: similar to normal DFS traversal
        return null;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < noOfVertices; i++) {
            s.append(i).append(": ");
            for (Edge j : this.adjacencyList.get(i)) {
                s.append(j.getDestination()).append(" ");
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
        WeightedGraph g = new UndirectedWeightedGraph(4);

        g.addEdge(0, 1, 9);
        g.addEdge(0, 2, 10);
        g.addEdge(0, 3, 5);
        g.addEdge(1, 2, 8);
        g.addEdge(2, 3, 7);

        System.out.print(g);
        System.out.println(g.adjacent(1));
        System.out.println(g.degree(2));
    }
}

