package graph;

import java.util.List;

public interface Graph {
    boolean addEdge(int source, int destination);

    boolean removeEdge(int source, int destination);

    boolean hasEdge(int source, int destination);

    int degree(int node);

    List<Integer> adjacent(int node);

    List<Integer> bfs();

    List<Integer> dfs();

    int getVerticesCount();
}
