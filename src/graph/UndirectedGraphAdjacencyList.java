package graph;

import java.util.*;

public class UndirectedGraphAdjacencyList implements Graph {
    private Map<Integer, Set<Integer>> adjacencyList;
    private final int noOfVertices;

    public UndirectedGraphAdjacencyList(int noOfVertices) {
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
    // Time Complexity: O(V + E)
    public List<Integer> bfs(int source) {
        List<Integer> result = new ArrayList<>();

        boolean[] visited = new boolean[this.noOfVertices];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);
            for (int adj : this.adjacent(node)) {
                if (!visited[adj]) {
                    visited[adj] = true;
                    queue.add(adj);
                }
            }
        }
        return result;
    }

    @Override
    // Time Complexity: O(V + E)
    public List<Integer> dfs(int source) {
        return dfsIterative(source);
//        return dfsRecursive(source);
    }

    private List<Integer> dfsIterative(int source) {
        List<Integer> result = new ArrayList<>();

        boolean[] visited = new boolean[this.noOfVertices];
        Stack<Integer> stack = new Stack<>();
        stack.add(source);
        visited[source] = true;

        while (!stack.isEmpty()) {
            int node = stack.pop();
            result.add(node);
            for (int adj : this.adjacent(node)) {
                if (!visited[adj]) {
                    visited[adj] = true;
                    stack.add(adj);
                }
            }
        }
        return result;
    }


    private List<Integer> dfsRecursive(int source) {
        List<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[this.noOfVertices];
        dfsRecurse(source, visited, result);
        return result;
    }

    private void dfsRecurse(int source, boolean[] visited, List<Integer> result) {
        result.add(source);
        visited[source] = true;
        for (int adj : this.adjacent(source)) {
            if (!visited[adj]) {
                visited[adj] = true;
                dfsRecurse(adj, visited, result);
            }
        }
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
        Graph g = new UndirectedGraphAdjacencyList(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);

        System.out.print(g);
        System.out.println(g.adjacent(2));
        System.out.println(g.degree(2));
        System.out.println("Bfs :" + g.bfs(2));
        System.out.println("Dfs :" + g.dfs(2));
    }
}
