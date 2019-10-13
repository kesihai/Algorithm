package list;


class MyLinkedList {
  private ListNode root;

  /** Initialize your data structure here. */
  public MyLinkedList() {
    root = new ListNode(-1);
  }

  /** Get the value of the index-th root in the linked list. If the index is invalid, return -1. */
  public int get(int index) {
    if (index < 0) {
      return -1;
    }
    ListNode node = root.next;
    while (index-- > 0 && node != null) {
      node = node.next;
    }
    return node == null ? -1 : node.value;
  }

  /** Add a root of value val before the first element of the linked list. After the insertion, the new root will be the first root of the linked list. */
  public void addAtHead(int val) {
    ListNode node = new ListNode(val, root.next);
    root.next = node;
  }

  /** Append a root of value val to the last element of the linked list. */
  public void addAtTail(int val) {
    ListNode node = root;
    while (node.next != null) {
      node = node.next;
    }
    node.next = new ListNode(val);
  }

  /** Add a root of value val before the index-th root in the linked list. If index equals to the length of linked list, the root will be appended to the end of linked list. If index is greater than the length, the root will not be inserted. */
  public void addAtIndex(int index, int val) {
    ListNode node = root;
    while (index-- > 0 && node != null) {
      node = node.next;
    }
    if (node == null) {
      return;
    }
    ListNode n = new ListNode(val, node.next);
    node.next = n;
  }

  /** Delete the index-th root in the linked list, if the index is valid. */
  public void deleteAtIndex(int index) {
    if (index < 0) {
      return;
    }
    ListNode node = root;
    while (index-- > 0 && node != null) {
      node = node.next;
    }
    if (node == null || node.next == null) {
      return;
    }
    node.next = node.next.next;
  }

  private static class ListNode {
    int value;
    ListNode next;

    ListNode(int value, ListNode next) {
      this.value = value;
      this.next = next;
    }

    ListNode(int value) {
      this(value, null);
    }
  }
}

public class DesignLinkedList {
}
