package queue;

class MyCircularDeque {
  private static class Node {
    int value;
    Node pre;
    Node next;
    Node (int value, Node pre, Node next) {
      this.value = value;
      this.next = next;
      this.pre = pre;
    }
    Node (int value) {
      this(value, null, null);
    }
  }
  private Node head;
  private Node tail;
  int size = 0;
  int cur = 0;
  /** Initialize your data structure here. Set the size of the deque to be k. */
  public MyCircularDeque(int k) {
    this.size = k;
    this.cur = 0;
    head = new Node(-1);
    tail = new Node(-1, head, head);
    head.pre = head.next = tail;
  }

  /** Adds an item at the front of Deque. Return true if the operation is successful. */
  public boolean insertFront(int value) {
    if (cur >= size) {
      return false;
    }
    Node node = new Node(value, head, head.next);
    head.next.pre = node;
    head.next = node;
    cur++;
    return true;
  }

  /** Adds an item at the rear of Deque. Return true if the operation is successful. */
  public boolean insertLast(int value) {
    if (cur >= size) {
      return false;
    }
    Node node = new Node(value, tail.pre, tail);
    tail.pre.next = node;
    tail.pre = node;
    cur++;
    return true;
  }

  /** Deletes an item from the front of Deque. Return true if the operation is successful. */
  public boolean deleteFront() {
    if (cur == 0) {
      return false;
    }
    head.next.next.pre = head;
    head.next = head.next.next;
    cur--;
    return true;
  }

  /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
  public boolean deleteLast() {
   if (cur == 0) {
     return false;
   }
    tail.pre.pre.next = tail;
    tail.pre = tail.pre.pre;
   cur--;
   return true;
  }

  /** Get the front item from the deque. */
  public int getFront() {
    return cur > 0 ? head.next.value : -1;
  }

  /** Get the last item from the deque. */
  public int getRear() {
    return cur > 0 ? tail.pre.value : -1;
  }

  /** Checks whether the circular deque is empty or not. */
  public boolean isEmpty() {
    return cur == 0;
  }

  /** Checks whether the circular deque is full or not. */
  public boolean isFull() {
    return cur >= size;
  }
}

/**
 * leetcode 设计循环双向队列 https://leetcode.com/problems/design-circular-deque/
 */
public class DesignCircularQueue {
  public static void main(String[] args) {
    MyCircularDeque q = new MyCircularDeque(3);
    System.out.println(q.insertLast(1));
    System.out.println(q.insertLast(2));
    System.out.println(q.insertFront(3));
    System.out.println(q.insertFront(4));
    System.out.println(q.deleteLast());
    System.out.println(q.insertFront(4));
    System.out.println(q.getFront());

  }
}
