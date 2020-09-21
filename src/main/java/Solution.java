import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Solution {
  List<List<Integer>> list;
  public boolean isTransformable(String s, String t) {
    list = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      list.add(new LinkedList<>());
    }
    for (int i = 0; i < s.length(); i++) {
      int v = s.charAt(i) - '0';
      list.get(v).add(i);
    }
    for (int i = 0; i < t.length(); i++) {
      int v = t.charAt(i) - '0';
      if (list.get(v).size() == 0) {
        return false;
      }
      int index = list.get(v).get(0);
      for (int j = 0; j < v; j++) {
        if (list.get(j).size() > 0 && list.get(j).get(0) <= index) {
          return false;
        }
      }
      list.get(v).remove(0);
    }
    return true;
  }
}