package map;

/*
leetcode:
https://leetcode.com/problems/design-hashmap/

 */
class MyHashMap {

  ListNode[] buckets = new ListNode[100000];
  /** Initialize your data structure here. */
  public MyHashMap() {

  }

  /** value will always be non-negative. */
  public void put(int key, int value) {
    int index = getHashValue(key);
    if (buckets[index] == null) {
      buckets[index] = new ListNode(key, value);
    } else {
      if (buckets[index].key == key) {
        buckets[index].value = value;
        return;
      }
      ListNode preNode = findPreNode(buckets[index], key);
      if (preNode.next == null) {
        preNode.next = new ListNode(key, value);
      } else {
        preNode.next.value = value;
      }
    }
  }

  /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
  public int get(int key) {
    int index = getHashValue(key);
    if (buckets[index] != null && buckets[index].key == key) {
      return buckets[index].value;
    }
    ListNode node = findPreNode(buckets[index], key);
    if (node == null || node.next == null) {
      return -1;
    }
    return node.next.value;
  }

  /** Removes the mapping of the specified value key if this map contains a mapping for the key */
  public void remove(int key) {
    int index = getHashValue(key);
    if (buckets[index] != null && buckets[index].key == key) {
      buckets[index] = buckets[index].next;
      return;
    }
    ListNode node = findPreNode(buckets[index], key);
    if (node == null || node.next == null) {
      return;
    }
    node.next = node.next.next;
  }

  private int getHashValue(int key) {
    return Integer.hashCode(key) % buckets.length;
  }

  private ListNode findPreNode(ListNode node, int key) {
    if (node == null) {
      return null;
    }
    ListNode pre = node;
    ListNode cur = node.next;
    while (cur != null && cur.key!= key) {
      pre = pre.next;
      cur = cur.next;
    }
    return pre;
  }

  private static class ListNode {
    int key;
    int value;
    ListNode next;
    ListNode(int key, int value, ListNode next) {
      this.key= key;
      this.value = value;
      this.next = next;
    }

    ListNode(int key, int value) {
      this(key, value, null);
    }
  }
}
public class DesignHashMap {
}
