package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class Resource {
  public ReentrantLock lock = new ReentrantLock();
  public Condition[] conditions = new Condition[3];
  volatile int cur = 0;
  int n = 0;

  Resource(int n) {
    this.n = n;
    for (int i = 0; i < conditions.length; i++) {
      conditions[i] = lock.newCondition();
    }
  }

  void print(Condition from, Condition next) throws InterruptedException {
    try {
      lock.lock();
      while (cur <= n) {
        System.out.println(Thread.currentThread().getName() + " " + cur);
        cur++;
        next.signal();
        from.await();
      }
      next.signal();
    } finally {
      lock.unlock();
    }
  }
}

public class ThreeThreadn {
  public static void main(String[] args) throws InterruptedException {
    Resource r = new Resource(99);
    for (int i = 0; i < 3; i++) {
      final int index = i;
      new Thread(new Runnable() {
        @Override
        public void run() {
          try {
            r.print(r.conditions[index], r.conditions[(index + 1) % 3]);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }, "thread-" + i).start();
      Thread.sleep(100);
    }
  }
}
