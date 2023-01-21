package graph;

import java.util.Arrays;

public class BellmanFordShortestPath {
    private final WeightedGraph graph;

    public BellmanFordShortestPath(WeightedGraph graph) {
        this.graph = graph;
    }

    public int[] shortestPath(int source) {
        int[] dist = new int[graph.getVerticesCount()];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        for (int u = 0; u < graph.getVerticesCount(); u++) {
            for (Edge v: graph.adjacent(u)) {
                if (dist[u] != Integer.MAX_VALUE && dist[u] + v.getWeight() < dist[v.getDestination()] ) {
                    dist[v.getDestination()] = dist[u] + v.getWeight() ;
                }
            }
        }
        if (checkForNegativeWeighCycle(dist)) {
            System.out.println("Graph Contains negative weight cycle");
        }

        return dist;
    }

    public boolean checkForNegativeWeighCycle(int[] dist) {
        for (int u = 0; u < graph.getVerticesCount(); u++) {
            for (Edge v: graph.adjacent(u)) {
                if (dist[u] != Integer.MAX_VALUE && dist[u] + v.getWeight() < dist[v.getDestination()] ) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        WeightedGraph g = new DirectedWeightedGraph(4);

        g.addEdge(0, 1, 1);
        g.addEdge(1, 2, -3);
        g.addEdge(0, 2, 4);
        g.addEdge(1, 3, 2);
        g.addEdge(2, 3, 3);

        System.out.print(g);
        System.out.println(g.adjacent(1));
        System.out.println(g.degree(2));

        BellmanFordShortestPath sp = new BellmanFordShortestPath(g);
        int[] dist = sp.shortestPath(0);
        for (int i = 0; i < dist.length; i++) {
            System.out.println(i + ": " + dist[i]);
        }


        g = new DirectedWeightedGraph(4);

        g.addEdge(0, 1, 4);
        g.addEdge(1, 2, -8);
        g.addEdge(0, 2, 8);
        g.addEdge(3, 1, 3);
        g.addEdge(2, 3, 2);

        System.out.print(g);
        System.out.println(g.adjacent(1));
        System.out.println(g.degree(2));

        sp = new BellmanFordShortestPath(g);
        dist = sp.shortestPath(0);
        for (int i = 0; i < dist.length; i++) {
            System.out.println(i + ": " + dist[i]);
        }
    }
}
