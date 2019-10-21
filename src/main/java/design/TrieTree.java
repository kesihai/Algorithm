package design;

class Trie {
  Trie[] child;
  boolean end = false;
  /** Initialize your data structure here. */
  public Trie() {
    child = new Trie['z' - 'a' + 1];
  }

  /** Inserts a word into the trie. */
  public void insert(String word) {
    this.insert(word, 0);
  }

  private void insert(String word, int pos) {
    if (pos == word.length()) {
      this.end = true;
      return;
    }
    int index = word.charAt(pos) - 'a';
    if (child[index] == null) {
      child[index] = new Trie();
    }
    child[index].insert(word, pos + 1);
  }

  /** Returns if the word is in the trie. */
  public boolean search(String word) {
    Trie tree = this;
    for (char ch : word.toCharArray()) {
      int index = ch - 'a';
      if (tree.child[index] == null) {
        return false;
      }
      tree = tree.child[index];
    }
    return tree.end;
  }

  /** Returns if there is any word in the trie that starts with the given prefix. */
  public boolean startsWith(String prefix) {
    Trie tree = this;
    for (char ch : prefix.toCharArray()) {
      int index = ch - 'a';
      if (tree.child[index] == null) {
        return false;
      }
      tree = tree.child[index];
    }
    return true;
  }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
public class TrieTree {
}
