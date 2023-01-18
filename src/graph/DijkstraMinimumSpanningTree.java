package graph;

import java.util.HashSet;
import java.util.Set;

public class DijkstraMinimumSpanningTree {
    private WeightedGraph graph;

    public DijkstraMinimumSpanningTree(WeightedGraph graph) {
        this.graph = graph;
    }

    public int length() {
        int length = 0;
        Set<Integer> used = new HashSet<>();
        for (int i = 0; i < graph.getVerticesCount(); i++) {
            for (int j = 0; j < graph.getVerticesCount(); j++) {
                if (!used.contains(j)) {
                   used.add(j);
                }


            }
        }
        return length;
    }
}
