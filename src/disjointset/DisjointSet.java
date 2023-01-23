package disjointset;

public interface DisjointSet {

    void union(int x, int y);

    int find(int x);
}
