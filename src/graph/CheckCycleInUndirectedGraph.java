package graph;

public class CheckCycleInUndirectedGraph {
    Graph g;

    public CheckCycleInUndirectedGraph(Graph g) {
        this.g = g;
    }

    public boolean hasCycle() {
        boolean[] visited = new boolean[g.getVerticesCount()];
        for (int i = 0; i < g.getVerticesCount(); i++) {
            if (!visited[i]) {
                if (dfsRecursive(i, visited, -1)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfsRecursive(int source, boolean[] visited, int parent) {
        visited[source] = true;
        for (int adj : g.adjacent(source)) {
            if (!visited[adj]) {
                if (dfsRecursive(adj, visited, source)) {
                    return true;
                }
            } else {
                if (adj != parent) {
                    return true;
                }
            }
        }
        return false;
    }


    public static void main(String[] args) {
        Graph g = new UndirectedGraphAdjacencyList(6);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 4);
        g.addEdge(4, 5);
        g.addEdge(1, 3);
        g.addEdge(2, 3);

        CheckCycleInUndirectedGraph checkGraph = new CheckCycleInUndirectedGraph(g);
        System.out.println("Graph: " + g);
        System.out.println("Graph contains cycle: " + checkGraph.hasCycle());

        g = new UndirectedGraphAdjacencyList(3);
        g.addEdge(0, 1);
        g.addEdge(1, 2);

        checkGraph = new CheckCycleInUndirectedGraph(g);
        System.out.println("Graph: " + g);
        System.out.println("Graph contains cycle: " + checkGraph.hasCycle());
    }


}
