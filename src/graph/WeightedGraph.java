package graph;

import java.util.List;

public interface WeightedGraph {
    boolean addEdge(int source, int destination, int weight);

    boolean removeEdge(int source, int destination);

    boolean hasEdge(int source, int destination);

    int degree(int node);

    List<Edge> adjacent(int node);

    List<Integer> bfs();

    List<Integer> dfs();

    int getVerticesCount();
}
