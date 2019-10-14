/**
 * leetcode 多线程
 * https://leetcode.com/problems/print-foobar-alternately/
 * 给一个n，对于每一个 [1~n] 打印出来, 按照顺序打印出来, 针对不同的value,用不同的线程来消耗.
 * 这个问题可以将n生产者，而输出作为不同的消费者，要干的事情就是不同的消费者之间同步的消费.
 * <p>
 * 那么要解决的问题就是每一个消费者在面对这个产品时判断是不是自己想要的，如果是，那么就消费，否则wait
 */
package thread;

import sun.awt.windows.awtLocalization_ko;

import java.util.function.IntConsumer;

class FizzBuzz {
  private int n;
  private int cur = 0;
  private Object lock = new Object();

  public FizzBuzz(int n) {
    this.n = n;
    this.cur = 1;
  }

  // printFizz.run() outputs "fizz".
  public void fizz(Runnable printFizz) throws InterruptedException {
    synchronized (lock) {
      while (cur <= n) {
        if (! (cur % 3 == 0 && cur % 5 != 0)) {
          lock.wait();
          continue;
        }
        printFizz.run();
        cur++;
        lock.notifyAll();
      }
    }
  }

  // printBuzz.run() outputs "buzz".
  public void buzz(Runnable printBuzz) throws InterruptedException {
    synchronized (lock) {
      while (cur <= n) {
        if (!(cur % 3 != 0 && cur % 5 == 0)) {
          lock.wait();
          continue;
        }
        printBuzz.run();
        cur++;
        lock.notifyAll();
      }
    }
  }

  // printFizzBuzz.run() outputs "fizzbuzz".
  public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
    synchronized (lock) {
      while (cur <= n) {
        if (!(cur % 3 == 0 && cur % 5 == 0)) {
          lock.wait();
          continue;
        }
        cur++;
        printFizzBuzz.run();
        lock.notifyAll();
      }
    }
  }

  // printNumber.accept(x) outputs "x", where x is an integer.
  public void number(IntConsumer printNumber) throws InterruptedException {
    synchronized (lock) {
      while (cur <= n) {
        if (cur % 3 == 0 || cur % 5 == 0) {
          lock.wait();
          continue;
        }
        printNumber.accept(cur);
        cur++;
        lock.notifyAll();
      }
    }

  }
}

public class FizzBuzzMultithread {
}
