package thread;

import java.util.concurrent.Semaphore;

class FooBar {
  private int n;
  private Semaphore[] sema = new Semaphore[2];
  public FooBar(int n) {
    this.n = n;
    sema[0] = new Semaphore(1);
    sema[1] = new Semaphore(0);
  }

  public void foo(Runnable printFoo) throws InterruptedException {

    for (int i = 0; i < n; i++) {
      sema[0].acquire();
      // printFoo.run() outputs "foo". Do not change or remove this line.
      printFoo.run();
      sema[1].release();
    }
  }

  public void bar(Runnable printBar) throws InterruptedException {

    for (int i = 0; i < n; i++) {
      sema[1].acquire();
      // printBar.run() outputs "bar". Do not change or remove this line.
      printBar.run();
      sema[0].release();
    }
  }
}
/*
  下面的方法也可以
 */
//class FooBar {
//  private int n;
//  private int state = 0;
//  private int cur = 0;
//  private Object lock = new Object();
//
//  public FooBar(int n) {
//    this.n = n;
//  }
//
//  public void foo(Runnable printFoo) throws InterruptedException {
//
//    synchronized (lock) {
//      while (cur < 2 * n) {
//        if (cur % 2 != 0) {
//          lock.wait();
//          continue;
//        }
//        printFoo.run();
//        cur++;
//        lock.notifyAll();
//      }
//    }
//  }
//
//  public void bar(Runnable printBar) throws InterruptedException {
//    synchronized (lock) {
//      while (cur < n) {
//        if (cur % 2 == 0) {
//          lock.wait();
//          continue;
//        }
//        printBar.run();
//        cur++;
//        lock.notifyAll();
//      }
//    }
//  }
//}

/**
 * leetcode 多线程: https://leetcode.com/problems/print-foobar-alternately/
 * 给定一个n: 间隔的输出 foo bar, 输出n次
 */
public class PrintFooBarAlternately {

}
