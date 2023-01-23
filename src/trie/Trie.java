package trie;

import java.util.ArrayList;
import java.util.List;

public class Trie {
    private final TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    // Time Complexity: O(L) -> length of the word
    public void insert(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            current = current.addChild(c);
        }
        current.markEnd();
    }

    // Time Complexity: O(L) -> length of the word to be searched
    public boolean search(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            current = current.findByKey(c);
            if (current == null) {
                return false;
            }
        }
        return current.isEnd();
    }

    // Time Complexity: O(M * N) -> M - no of words found, N length of the longest word found
    public List<String> searchByPrefix(String word) {
        TrieNode current = root;
        List<String> results = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (char c : word.toCharArray()) {
            sb.append(c);
            current = current.findByKey(c);
            if (current == null) {
                return results;
            }
        }
        dfs(current, results, sb);
        return results;
    }

    // Time Complexity: O(L) -> length of the word to be removed
    public void remove(String word) {
        remove(root, word, 0);
    }

    private TrieNode remove(TrieNode current, String word, int len) {
        if (current == null) {
            return null;
        }

        if (len == word.length()) {
            if (current.isEnd()) {
                current.isEnd = false;
            }
            if (current.getChildrenKeys().size() == 0) {
                return null;
            }
            return current;
        }

        char ch = word.charAt(len);
        if (remove(current.findByKey(ch), word, len + 1) == null) {
            current.removeChild(ch);
        }

        if (current.getChildrenKeys().size() == 0 && !current.isEnd()) {
            return null;
        }
        return current;
    }

    private void dfs(TrieNode current, List<String> words, StringBuilder sb) {
        if (current == null) {
            return;
        }

        if (current.isEnd()) {
            System.out.println(sb.toString());
            words.add(sb.toString());
        }

        for (char childrenKey : current.getChildrenKeys()) {
            dfs(current.findByKey(childrenKey), words, sb.append(childrenKey));
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("andaman");
        trie.insert("and");
        trie.insert("ant");
        trie.insert("anteater");

        System.out.println(trie);
        System.out.println(trie.search("anf"));
        System.out.println(trie.search("and"));
        System.out.println(trie.search("ant"));

        List<String> words = trie.searchByPrefix("an");
        System.out.println("Prefix Search");
        for (String word : words) {
            System.out.println(word);
        }

        System.out.println(trie.search("and"));
        System.out.println("Remove");
        trie.remove("and");
        System.out.println(trie.search("and"));
    }
}


