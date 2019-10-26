package thread;

import java.util.concurrent.Semaphore;

/**
 * 哲学家吃饭问题, 5个人围在一个桌子, 每个人左右两边都有一支筷子, 现在wantsToEat 会被5个线程调用, 怎么让他有序的安排5个哲学家吃饭.
 * <p>
 * 思考: 死锁产生的条件是下面四个,破坏其中任何一个, 死锁都不存在
 *    a. 互斥
 *    b. 不可抢占
 *    c. 占有并且等待.
 *    d. 循环等待
 * <p>
 * 1. 怎么破坏互斥？ 有足够多的筷子, 那么每个人都用自己的, 在这里不适用. (NO)
 * 2. 怎么破坏不可抢占 ？ 这个不好搞, 一个哲学家拿到一支筷子, 别的哲学家不能抢占(从手中抢过来) (NO)
 * 3. 怎么破坏占有并且等待 ?  要么拿不到筷子,要么一次性拿到, 这样不就存在咱有并且等待的问题. (OK)
 * 4. 怎么破坏循环等待 ?
 *    发生循环等待必定是(假如每个人都是先左后右) 每个人拿到了自己的左边， 同时在等待另一边,
 *    a. 如果只让最多4个人吃饭, 那么这个循环等待的问题就不复存在.
 *    b. 如果让0~3 都是先拿左手的筷子 再拿右手的筷子, 而对于4这个人, 先拿右手的筷子, 再拿左手的筷子, 那么这个环路也被破坏了
 *    c. 还有更厉害的人发现 坐标为基数和偶数的人拿筷子的顺序不一样也可以破坏循环等待条件
 */

/*// Solution1 破坏占有并且等待条件 (10ms)
class DiningPhilosophers {
  int n;
  boolean[] availables;
  Object lock;
  public DiningPhilosophers() {
    this(5);
  }
  public DiningPhilosophers(int n) {
    this.n = n;
    // availables 代表的是筷子, 当然了，更好理解的是代表人, 代码需要修改
    this.availables = new boolean[n];
    lock = new Object();
    Arrays.fill(availables, true);
  }

  // call the run() method of any runnable to execute its code
  public void wantsToEat(int philosopher,
                         Runnable pickLeftFork,
                         Runnable pickRightFork,
                         Runnable eat,
                         Runnable putLeftFork,
                         Runnable putRightFork) throws InterruptedException {
    int left = philosopher;
    int right = (philosopher + 1) % n;
    synchronized (lock) {
      while (true) {
        if (!(availables[left] && availables[right])) {
          lock.wait();
          continue;
        }
        availables[left] = availables[right] = false;
        break;
      }
    }
    pickLeftFork.run();
    pickRightFork.run();
    eat.run();
    putLeftFork.run();
    putRightFork.run();
    synchronized (lock) {
      availables[left] = availables[right] = true;
      lock.notifyAll();
    }
  }
}*/

/*// 只允许最多4个人吃饭  20ms
class DiningPhilosophers {
  int n;
  Semaphore eatingPeople;
  Semaphore[] knifes;
  public DiningPhilosophers() {
    this(5);
  }

  public DiningPhilosophers(int n) {
    this.n = n;
    this.eatingPeople = new Semaphore(n - 1);
    knifes = new Semaphore[n];
    for (int i = 0; i < knifes.length; i++) {
      knifes[i] = new Semaphore(1);
    }
  }

  // call the run() method of any runnable to execute its code
  public void wantsToEat(int philosopher,
                         Runnable pickLeftFork,
                         Runnable pickRightFork,
                         Runnable eat,
                         Runnable putLeftFork,
                         Runnable putRightFork) throws InterruptedException {
    eatingPeople.acquire();
    int left = philosopher;
    int right = (philosopher + 1) % n;
    knifes[left].acquire();
    knifes[right].acquire();
    pickLeftFork.run();
    pickRightFork.run();
    eat.run();
    putLeftFork.run();
    putRightFork.run();
    knifes[left].release();
    knifes[right].release();
    eatingPeople.release();
  }
}*/

// 破坏循坏等待,让最后一个人的拿筷子顺序不一样 (10ms)
class DiningPhilosophers {
  int n;
  Semaphore[] semaphores;

  public DiningPhilosophers() {
    this(5);
  }

  public DiningPhilosophers(int n) {
    this.n = n;
    this.semaphores = new Semaphore[n];
    for (int i = 0; i < n; i++) {
      semaphores[i] = new Semaphore(1);
    }
  }

  // call the run() method of any runnable to execute its code
  public void wantsToEat(int philosopher,
                         Runnable pickLeftFork,
                         Runnable pickRightFork,
                         Runnable eat,
                         Runnable putLeftFork,
                         Runnable putRightFork) throws InterruptedException {
    int left = philosopher;
    int right = (philosopher + 1) % n;
    // if the guy is the last one, change its order to pick the chopsticks
    if (left == n - 1) {
      left = 0;
      right = n - 1;
    }
    semaphores[left].acquire();
    semaphores[right].acquire();
    pickLeftFork.run();
    pickRightFork.run();
    eat.run();
    putLeftFork.run();
    putRightFork.run();
    semaphores[left].release();
    semaphores[right].release();
  }
}

public class TheDiningPhilosophers_1226 {
}
