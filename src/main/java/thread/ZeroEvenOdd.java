package thread;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;


/**
 * Leetcode 多线程
 * https://leetcode.com/problems/print-zero-even-odd/
 * 给一个n，按照 01020304的顺序打印
 */

class ZeroEvenOdd {
  private int n;
  private Semaphore[] sema = new Semaphore[3];
  volatile private int cur = 1;
  public ZeroEvenOdd(int n) {
    this.n = n;
    sema[0] = new Semaphore(1);
    sema[1] = new Semaphore(0);
    sema[2] = new Semaphore(0);
  }

  // printNumber.accept(x) outputs "x", where x is an integer.
  public void zero(IntConsumer printNumber) throws InterruptedException {
    int index = 1;
    while (cur <= n) {
      sema[0].acquire();
      if (cur <= n) {  // 这个地方太重要了, 因为前面的acquire可能先阻塞了
        printNumber.accept(0);
      }
      sema[index].release();
      index = (index % 2) + 1;
    }
  }

  public void even(IntConsumer printNumber) throws InterruptedException {
    while (cur <= n) {
      sema[2].acquire();
      if (cur <= n) {  // 同理，这个地方太重要了
        printNumber.accept(cur++);
      }
      sema[0].release();
    }
  }

  public void odd(IntConsumer printNumber) throws InterruptedException {
    while (cur <= n) {
      sema[1].acquire();
      if (cur <= n) {  // 同理，这个地方太重要了
        printNumber.accept(cur++);
      }
      sema[0].release();
    }
  }
}
/*

// 下面这个也是可以的
class ZeroEvenOdd {
  private int n;
  private Object lock = new Object();
  private int cur = 1;
  private int state = 0;

  public ZeroEvenOdd(int n) {
    this.n = n;
  }

  // printNumber.accept(x) outputs "x", where x is an integer.
  public void zero(IntConsumer printNumber) throws InterruptedException {
    synchronized (lock) {
      while (cur <= n) {
        if (state != 0 && state != 2) {
          lock.wait();
          continue;
        }
        printNumber.accept(0);
        state = state + 1;
        lock.notifyAll();
      }
    }
  }

  public void even(IntConsumer printNumber) throws InterruptedException {
    synchronized (lock) {
      while (cur <= n) {
        if (state != 3) {
          lock.wait();
          continue;
        }
        printNumber.accept(cur);
        cur++;
        state = 0;
        lock.notifyAll();
      }
    }

  }

  public void odd(IntConsumer printNumber) throws InterruptedException {
    synchronized (lock) {
      while (cur <= n) {
        if (state != 1) {
          lock.wait();
          continue;
        }
        printNumber.accept(cur);
        cur++;
        state = state + 1;
        lock.notifyAll();
      }
    }
  }
}*/


