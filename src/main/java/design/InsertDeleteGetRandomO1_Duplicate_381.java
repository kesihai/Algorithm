package design;

import java.util.*;

/**
 * leetcode 381, 设计一种数据结构 insert/remove/getRandom 都是 o(1), 其中insert 允许重复的
 */
class RandomizedCollection {
  ArrayList<Integer> list = new ArrayList<>();
  Map<Integer, Set<Integer>> mp = new HashMap<>();
  Random random = new Random();
  /** Initialize your data structure here. */
  public RandomizedCollection() {
  }

  /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
  public boolean insert(int val) {
    if (!mp.containsKey(val)) {
      mp.put(val, new HashSet<>());
    }
    mp.get(val).add(list.size());
    list.add(val);
    return true;
  }

  /** Removes a value from the collection. Returns true if the collection contained the specified element. */
  public boolean remove(int val) {
    if (!mp.containsKey(val) || mp.get(val).size() == 0) {
      return false;
    }
    Set<Integer> indexs = mp.get(val);
    int index = indexs.iterator().next();
    indexs.remove(index);
    if (index != list.size() - 1) {
      int value = list.get(list.size() - 1);
      Set<Integer> set = mp.get(value);
      set.remove(list.size() - 1);
      list.set(index, value);
      set.add(index);
    }
    list.remove(list.size() - 1);
    return true;
  }

  /** Get a random element from the collection. */
  public int getRandom() {
    return list.get(random.nextInt(list.size()));
  }
}

public class InsertDeleteGetRandomO1_Duplicate_381 {
}
