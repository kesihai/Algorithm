package thread;

import sun.swing.BakedArrayList;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 银行叫号模拟:
 * 1. Customer: serviceTime.
 * 2. CustomerLine: 同步的Customer queue.
 * 3. CustomerGenerator: customer 创造者.
 * 4. Teller: 柜台人员, 有serving/doOther 状态, ID, servedCount.
 * 5. TellerManager: Teller 管理者, 定期的调整Teller 状态.
 * 6. BankSimulator: 将前面5位组织起来.
 */
public class Banker {

  private static class Customer {
    private final int serveTime;

    Customer(int time) {
      this.serveTime = time;
    }

    @Override
    public String toString() {
      return "[" + serveTime + "]";
    }
  }

  private static class CustomerLine extends ArrayBlockingQueue<Customer> {

    public CustomerLine(int capacity) {
      super(capacity);
    }
  }

  private static class CustomerGenerator implements Runnable {
    private CustomerLine customers;
    private static Random random = new Random(47);
    private int internalSec = 300;

    public CustomerGenerator(CustomerLine customerLine) {
      this(customerLine, 10);
    }

    public CustomerGenerator(CustomerLine customerLine, int internalSec) {
      this.customers = customerLine;
      this.internalSec = internalSec;
    }

    @Override
    public void run() {
      try {
        int count = 0;
        while (!Thread.interrupted()) {
          Thread.sleep(random.nextInt(internalSec));
          // TODO: just hard code the customer service time up bound.
          customers.add(new Customer(random.nextInt(300)));
          count++;
          if (count > 10000) {
            break;
          }
        }
      } catch (InterruptedException e) {
        System.out.println("customer generator interrupted");
        e.printStackTrace();
      }
    }
  }

  private static class Teller implements Runnable, Comparable<Teller> {
    private static int counter = 0;
    private final int id = counter++;

    private int customerServed = 0;
    private CustomerLine customers;
    private boolean servingCustomerLine = true;

    public Teller(CustomerLine cq) {
      customers = cq;
    }

    @Override
    public void run() {
      try {
        while (!Thread.interrupted()) {
          Customer customer = customers.take();
          System.out.println("customer served: " + customer);
          Thread.sleep(customer.serveTime);
          synchronized (this) {
            customerServed++;
            while (!servingCustomerLine) {
              wait();
            }
          }
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    public synchronized void serveCustomerLine() {
      servingCustomerLine = true;
      notifyAll();
    }

    public synchronized void doSomethingElse() {
      servingCustomerLine = false;
      customerServed = 0;
    }

    @Override
    public synchronized int compareTo(Teller o) {
      return customerServed - o.customerServed;
    }

    @Override
    public synchronized String toString() {
      return "Teller Id " + id + " " +  customerServed + " ";
    }
  }

  private static class TellerManager implements Runnable {
    private int adjustTime;
    private CustomerLine customers;
    private Queue<Teller> workingTellers = new LinkedList<>();
    private Queue<Teller> doOtherTellers = new LinkedList<>();
    private ExecutorService service = Executors.newFixedThreadPool(10);

    public TellerManager(CustomerLine customers, int adjustTime) {
      this.customers = customers;
      this.adjustTime = adjustTime;
    }

    private void doAdjust() {
      if (workingTellers.size() * 2 < customers.size()) {
        // If there are too many customers
        addNewTeller();

      } else if (workingTellers.size() > customers.size()) {
        // If there are few customers
        removeTeller();
      }
    }

    private void addNewTeller() {
      if (doOtherTellers.size() == 0) {
        Teller teller = new Teller(customers);
        service.submit(teller);
        workingTellers.add(teller);
      } else {
        Teller teller = doOtherTellers.poll();
        teller.serveCustomerLine();
        workingTellers.add(teller);
      }
    }

    private void removeTeller() {
      if (workingTellers.size() == 0) {
        return;
      }
      Teller teller = workingTellers.poll();
      teller.doSomethingElse();
      doOtherTellers.add(teller);
    }

    @Override
    public void run() {
      try {
        while (!Thread.interrupted()) {
          doAdjust();
          System.out.print(customers + " { ");
          for (Teller teller : workingTellers) {
            System.out.print(teller);
          }
          System.out.println("}");
          Thread.sleep(adjustTime);
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
        service.shutdownNow();
      }
    }
  }

  private static class BankSimulator implements Runnable {

    @Override
    public void run() {
      CustomerLine customers = new CustomerLine(1000);
      ExecutorService service = Executors.newFixedThreadPool(10);
      service.submit(new CustomerGenerator(customers));

      TellerManager manager = new TellerManager(customers, 1000);
      service.submit(manager);

      try {
        Thread.sleep(50000);
        service.shutdownNow();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) {
    BankSimulator simulator = new BankSimulator();
    simulator.run();
  }
}
