package design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Leetcode 380 https://leetcode.com/problems/insert-delete-getrandom-o1/
 */
class RandomizedSet {
  ArrayList<Integer> values;
  Map<Integer, Integer> mp;
  Random random = new Random();

  /** Initialize your data structure here. */
  public RandomizedSet() {
    values = new ArrayList<>();
    mp = new HashMap<>();
  }

  /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
  public boolean insert(int val) {
    if (mp.containsKey(val)) {
      return false;
    }
    values.add(val);
    mp.put(val, values.size() - 1);
    return true;
  }

  /** Removes a value from the set. Returns true if the set contained the specified element. */
  public boolean remove(int val) {
    if (!mp.containsKey(val)) {
      return false;
    }
    int pos = mp.get(val);
    if (pos != values.size() - 1) {
      mp.put(values.get(values.size() - 1), pos);
      values.set(pos, values.get(values.size() - 1));
    }
    mp.remove(val);
    values.remove(values.size() - 1);
    return true;
  }

  /** Get a random element from the set. */
  public int getRandom() {
    return values.get(random.nextInt(values.size()));
  }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */

public class InsertDeleteGetrandomO1_380 {
  public static void main(String[] args) {
    RandomizedSet s = new RandomizedSet();
    s.insert(1);
    s.remove(2);
    s.insert(2);
    s.getRandom();
    s.insert(2);
    s.remove(1);
    System.out.println(s.getRandom());
  }
}
