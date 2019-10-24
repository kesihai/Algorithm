package design;

import cache.LRUCache;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class LFUCache {
  Map<Integer, Integer> key2Value;
  Map<Integer, Integer> count;
  Map<Integer, List<Integer>> countList;
  int capacity;
  int minVisit = -1;
  public LFUCache(int capacity) {
    key2Value = new HashMap<>();
    count = new HashMap<>();
    countList = new HashMap<>();
    this.capacity = capacity;
  }

  public int get(int key) {
    if (!key2Value.containsKey(key)) {
      return -1;
    }
    visit(key);
    return key2Value.get(key);
  }

  public void put(int key, int value) {
    if (capacity == 0) {
      return;
    }
    if (!key2Value.containsKey(key)) {
      checkCapacity();
      key2Value.put(key, value);
      count.put(key, 0);
      countList.putIfAbsent(0, new ArrayList<>(Arrays.asList(key)));
      minVisit = 0;
    } else {
      key2Value.put(key, value);
    }
    visit(key);
  }

  private void visit(int key) {
    Integer num = count.get(key);
    count.put(key, num + 1);
    countList.get(num).remove(Integer.valueOf(key));
    if (!countList.containsKey(num + 1)) {
      countList.put(num + 1, new ArrayList<>());
    }
    countList.get(num + 1).add(key);
    if (num == minVisit && countList.get(num).size() == 0) {
      minVisit++;
    }
  }

  private void checkCapacity() {
    if (capacity == key2Value.size() && capacity != 0) {
      List<Integer> list = countList.get(minVisit);
      Integer key = list.get(0);
      key2Value.remove(key);
      count.remove(key);
      list.remove(0);
      if (list.size() == 0) {
        minVisit++;
      }
    }
  }
}

public class LFUCache_460 {
  public static void main(String[] args) {
    LFUCache cache = new LFUCache(10);
    String str = "[10,13],[3,17],[6,11],[10,5],[9,10],[13],[2,19],[2],[3],[5,25],[8],[9,22],[5,5],[1,30],[11],[9,12],[7],[5],[8],[9],[4,30],[9,3],[9],[10],[10],[6,14],[3,1],[3],[10,11],[8],[2,14],[1],[5],[4],[11,4],[12,24],[5,18],[13],[7,23],[8],[12],[3,27],[2,12],[5],[2,9],[13,4],[8,18],[1,7],[6],[9,29],[8,21],[5],[6,30],[1,12],[10],[4,15],[7,22],[11,26],[8,17],[9,29],[5],[3,4],[11,30],[12],[4,29],[3],[9],[6],[3,4],[1],[10],[3,29],[10,28],[1,20],[11,13],[3],[3,12],[3,8],[10,9],[3,26],[8],[7],[5],[13,17],[2,27],[11,15],[12],[9,19],[2,15],[3,16],[1],[12,17],[9,1],[6,19],[4],[5],[5],[8,1],[11,7],[5,2],[9,28],[1],[2,2],[7,4],[4,22],[7,24],[9,26],[13,28],[11,26]";
    String[] strs = str.split("]");
    for (int i = 0; i < strs.length; i++) {
      strs[i] = strs[i].replace("[", "");
      if (strs[i].charAt(0) == ',') {
        strs[i] = strs[i].substring(1);
      }
    }
    for (int i = 0; i < strs.length; i++) {
      int[] values = Arrays.stream(strs[i].split(",")).mapToInt((s -> Integer.valueOf(s))).toArray();
      if (values.length == 1) {
        System.out.println(cache.get(values[0]));
      } else {
        cache.put(values[0], values[1]);
      }

      System.out.println(values[0] + " -------" + (values.length > 1 ? values[1] : "") + " index " + i);
      System.out.println(cache.key2Value);
      System.out.println(cache.count);
      System.out.println(cache.countList);
      System.out.println(cache.minVisit);
    }
  }
}

