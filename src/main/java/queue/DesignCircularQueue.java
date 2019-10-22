package queue;
class MyCircularQueue {

  int[] values;
  int head, tail;
  int size = 0;
  /** Initialize your data structure here. Set the size of the queue to be k. */
  public MyCircularQueue(int k) {
    values = new int[k];
    head = tail = size = 0;
  }

  /** Insert an element into the circular queue. Return true if the operation is successful. */
  public boolean enQueue(int value) {
    if (isFull()) {
      return false;
    }
    values[tail] = value;
    tail = (tail + 1) % values.length;
    size++;
    return true;
  }

  /** Delete an element from the circular queue. Return true if the operation is successful. */
  public boolean deQueue() {
    if (isEmpty()) {
      return false;
    }
    head = (head + 1) % values.length;
    size--;
    return true;
  }

  /** Get the front item from the queue. */
  public int Front() {
    return isEmpty() ? -1 : values[head];
  }

  /** Get the last item from the queue. */
  public int Rear() {
    return isEmpty() ? -1 : values[(tail - 1 + values.length) % values.length];
  }

  /** Checks whether the circular queue is empty or not. */
  public boolean isEmpty() {
    return size == 0;
  }

  /** Checks whether the circular queue is full or not. */
  public boolean isFull() {
    return size == values.length;
  }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */

/**
 * leetcode 设计循环双向队列 https://leetcode.com/problems/design-circular-deque/
 */
public class DesignCircularQueue {
  public static void main(String[] args) {
  }
}
