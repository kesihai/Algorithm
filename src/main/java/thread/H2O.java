package thread;

class H2O {
  Object lock = new Object();
  volatile int H = 0;
  volatile int O = 0;

  public H2O() {
  }

  public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
    synchronized (lock) {
      if (H == 2) {
        lock.wait();
        releaseHydrogen.run();
        H = 0;
        O = 0;
      }
      if (H == 0) {
        releaseHydrogen.run();
        H++;
        return;
      }
      if (H == 1) {
        if (O == 0) {
          releaseHydrogen.run();
          H++;
        } else {
          releaseHydrogen.run();
          O = 0;
          H = 0;
          notifyAll();
        }
        return;
      }
    }
  }

  public void oxygen(Runnable releaseOxygen) throws InterruptedException {
    synchronized (lock) {
      if (O == 0) {
        releaseOxygen.run();
        O++;
      } else {
        if (H < 2) {
          lock.wait();
          H = 0;
          O = 0;
        } else {
          H = O = 0;
          lock.notifyAll();
        }
      }
    }
  }
}

