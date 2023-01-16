package graph;

public class CheckCycleInDirectedGraph {
    Graph g;

    public CheckCycleInDirectedGraph(Graph g) {
        this.g = g;
    }

    public boolean hasCycle() {
        boolean[] visited = new boolean[g.getVerticesCount()];
        boolean[] recursionStack = new boolean[g.getVerticesCount()];
        for (int i = 0; i < g.getVerticesCount(); i++) {
            if (!visited[i]) {
                if (dfsRecursive(i, visited, recursionStack)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfsRecursive(int source, boolean[] visited, boolean[] recursionStack) {
        visited[source] = true;
        recursionStack[source] = true;
        for (int adj : g.adjacent(source)) {
            if (!visited[adj]) {
                if (dfsRecursive(adj, visited, recursionStack)) {
                    return true;
                }
            } else {
                if (recursionStack[adj]) {
                    return true;
                }
            }
        }
        recursionStack[source] = false;
        return false;
    }


    public static void main(String[] args) {
        Graph g = new DirectedGraphAdjacencyList(6);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 4);
        g.addEdge(4, 5);
        g.addEdge(1, 3);
        g.addEdge(2, 3);
        g.addEdge(5, 2);

        CheckCycleInDirectedGraph checkGraph = new CheckCycleInDirectedGraph(g);
        System.out.println("Graph: " + g);
        System.out.println("Graph contains cycle: " + checkGraph.hasCycle());


        g = new DirectedGraphAdjacencyList(4);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(0, 2);
        g.addEdge(2, 3);

        checkGraph = new CheckCycleInDirectedGraph(g);
        System.out.println("Graph: " + g);
        System.out.println("Graph contains cycle: " + checkGraph.hasCycle());

    }
}
