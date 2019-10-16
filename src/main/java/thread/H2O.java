package thread;

import java.util.concurrent.Semaphore;

/**
 * Leetcode 多线程 https://leetcode.com/problems/building-h2o/
 * 给一组H O， 保证h * 2 = o, 现在要求要输出过程中,始终保证 h * 2 <= o
 */
class H2O {
 private Object lock = new Object();
 volatile int h = 0;
 volatile int o = 0;

 public H2O() {
 }

 public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
   synchronized (lock) {
    while (h > o * 2) {
     lock.wait();
    }
    releaseHydrogen.run();
    h++;
    lock.notifyAll();
   }
 }

 public void oxygen(Runnable releaseOxygen) throws InterruptedException {
   synchronized (lock) {
     while (o * 2 > h) {
       lock.wait();
     }
     releaseOxygen.run();
     o++;
     if (o > 100000) {
       h = h - (o - 1) * 2;
       o = 1;
     }
     lock.notifyAll();
   }
 }
}
/*
class H2O {
  Semaphore[] semas = new Semaphore[2];
  volatile int H = 0;

  public H2O() {
    semas[0] = new Semaphore(2);
    semas[1] = new Semaphore(0);
  }

  public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
    semas[0].acquire();
    releaseHydrogen.run();
    H++;
    if (H == 2) {
      semas[1].release();
    }
  }

  public void oxygen(Runnable releaseOxygen) throws InterruptedException {
    semas[1].acquire();
    releaseOxygen.run();
    H = 0;
    semas[0].release(2);
  }

}

*/
