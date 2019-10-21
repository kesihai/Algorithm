package design;

import java.util.Iterator;

// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
class PeekingIterator implements Iterator<Integer> {
  int cur = 0;
  boolean slow = false;
  Iterator<Integer> iterator;
  public PeekingIterator(Iterator<Integer> iterator) {
    // initialize any member here.
    this.iterator = iterator;
    cur = 0;
  }

  // Returns the next element in the iteration without advancing the iterator.
  public Integer peek() {
    if (slow) {
      return cur;
    } else {
      cur = iterator.next();
      slow = true;
      return cur;
    }
  }

  // hasNext() and next() should behave the same as in the Iterator interface.
  // Override them if needed.
  @Override
  public Integer next() {
    if (slow) {
      slow = false;
      return cur;
    } else {
      slow = false;
      cur = iterator.next();
      return cur;
    }
  }

  @Override
  public boolean hasNext() {
    return slow || iterator.hasNext();
  }
}
public class PeekIterator {
}
