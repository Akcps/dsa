package trie;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TrieNode {
    Map<Character, TrieNode> children;
    boolean isEnd;

    public TrieNode() {
        this.children  = new HashMap<>();
        this.isEnd = false;
    }

    public Set<Character> getChildrenKeys() {
        return children.keySet();
    }

    public TrieNode findByKey(char c) {
        return this.children.get(c);
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void removeChild(char c) {
        children.remove(c);
    }

    public TrieNode addChild(char c) {
        if (!children.containsKey(c)) {
            TrieNode newNode = new TrieNode();
            children.put(c, newNode);
            return newNode;
        } else {
            return children.get(c);
        }
    }

    public void markEnd() {
        this.isEnd = true;
    }

    @Override
    public String toString() {
        return "TrieNode{" +
                "children=" + children +
                ", isEnd=" + isEnd +
                '}';
    }
}
