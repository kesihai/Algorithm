/**
 * Leetcode 多线程
 * https://leetcode.com/problems/print-in-order/
 * 多线程按照顺序来打印 1 2 3
 */
package thread;

import java.util.concurrent.Semaphore;

//class Foo {
//
//  Semaphore[] sema = new Semaphore[2];
//  public Foo() {
//    for (int i = 0; i < sema.length; i++) {
//      sema[i] = new Semaphore(0);
//    }
//  }
//
//  public void first(Runnable printFirst) throws InterruptedException {
//    printFirst.run();
//    sema[0].release();
//  }
//
//  public void second(Runnable printSecond) throws InterruptedException {
//    sema[0].acquire();
//    printSecond.run();
//    sema[1].release();
//  }
//
//  public void third(Runnable printThird) throws InterruptedException {
//    sema[1].acquire();
//    printThird.run();
//  }
//}

class Foo {

  Object obj = new Object();
  int state = 0;

  public Foo() {
  }

  public void first(Runnable printFirst) throws InterruptedException {
    synchronized (obj) {
      while (state != 0) {
        obj.wait();
      }
      printFirst.run();
      obj.notifyAll();
      state++;
    }
  }

  public void second(Runnable printSecond) throws InterruptedException {
    synchronized (obj) {
      while (state != 1) {
        obj.wait();
      }
      printSecond.run();
      obj.notifyAll();
      state++;
    }
  }

  public void third(Runnable printThird) throws InterruptedException {
    synchronized (obj) {
      while (state != 2) {
        obj.wait();
      }
      printThird.run();
      obj.notifyAll();
      state = 0;
    }
  }
}

class MyRunnable implements Runnable {
  private String str;

  public MyRunnable(String str) {
    this.str = str;
  }

  @Override
  public void run() {
    System.out.println(this.str);
  }
}

public class PrintInOrder {
  public static void main(String[] args) throws InterruptedException {
    Foo f = new Foo();
    f.first(new MyRunnable("A.java"));
    f.second(new MyRunnable("B"));
    f.third(new MyRunnable("C"));
  }

}
