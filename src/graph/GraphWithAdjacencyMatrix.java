package graph;

public class GraphWithAdjacencyMatrix implements Graph {
    private final boolean[][] adjacencyMatrix;
    private final int noOfVertices;

    public GraphWithAdjacencyMatrix(int noOfVertices) {
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
    public boolean addEdge(int source, int destination) {
        if (!this.validateSourceAndDestination(source, destination)) {
            return false;
        }
        if (this.adjacencyMatrix[source][destination] || this.adjacencyMatrix[destination][source]) {
            System.out.println("edge already exists");
            return false;
        }
        this.adjacencyMatrix[source][destination] = true;
        this.adjacencyMatrix[destination][source] = true;
        return true;
    }

    @Override
    public boolean removeEdge(int source, int destination) {
        if (!this.validateSourceAndDestination(source, destination)) {
            return false;
        }
        if (!this.adjacencyMatrix[source][destination] || ! this.adjacencyMatrix[destination][source]) {
            System.out.println("edge doesn't exist");
            return false;
        }
        this.adjacencyMatrix[source][destination] = false;
        this.adjacencyMatrix[destination][source] = false;
        return false;
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

    public static void main(String args[]) {
        Graph g = new GraphWithAdjacencyMatrix(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);

        System.out.print(g);
    }
}
