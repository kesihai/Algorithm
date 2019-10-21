package cache;

import java.util.HashMap;

/**
 * Leetcode LRU cache
 * https://leetcode.com/problems/lru-cache/
 */
public class LRUCache {

  private int capacity;
  private int size;
  private Node head;
  private Node tail;
  private HashMap<Integer, Node> mp;

  public LRUCache(int capacity) {
    this.capacity = capacity;
    this.size = 0;
    mp = new HashMap<>();
    head = new Node(-1, -1);
    tail = new Node(-1, -1, head, head);
    head.pre = head.next = tail;
  }

  public int get(int key) {
    if (mp.containsKey(key)) {
      Node node = mp.get(key);
      moveToFront(node);
      return node.value;
    }
    return -1;
  }

  public void put(int key, int value) {
    if (!mp.containsKey(key)) {
      if (size == capacity) {
        if (size == 0) {
          return;
        }
        removeNode(tail.pre);
      }
      Node node = new Node(key, value);
      putFront(node);
    } else {
      Node node = mp.get(key);
      node.value = value;
      moveToFront(node);
    }
  }

  private void moveToFront(Node node) {
    removeNode(node);
    putFront(node);
  }
  private void removeNode(Node node) {
    node.next.pre = node.pre;
    node.pre.next = node.next;
    size--;
    mp.remove(node.key);
  }
  private void putFront(Node node) {
    node.next = head.next;
    head.next.pre = node;
    node.pre = head;
    head.next = node;
    size++;
    mp.put(node.key, node);
  }

  private class Node {
    int value;
    int key;
    Node pre;
    Node next;
    Node(int key, int value, Node pre, Node next) {
      this.key = key;
      this.value = value;
      this.pre = pre;
      this.next = next;
    }

    Node(int key, int value) {
      this(key, value, null, null);
    }
  }
}
