import java.util.Map;

/*
* @lc app=leetcode.cn id=496 lang=java
*
* [496] 下一个更大元素 I
*/

// @lc code=start
class Solution {
 public int[] nextGreaterElement(int[] nums1, int[] nums2) {
   Stack<Integer> s = new Stack<Integer>();
   Map<Integer, Integer> mp = new HashMap<>();
   for (int i = 0; i < nums2.length; i++) {
     if (s.isEmpty() || nums2[i] <= s.peek()) {
       s.push(nums2[i]);
     } else {
       while (!s.isEmpty() && nums2[i] > s.peek()) {
         mp.put(s.pop(), nums2[i]);
       }
       s.push(nums2[i]);
     }
   }
   int[] ans = new int[nums1.length];
   for (int i = 0; i < ans.length; i++) {
     ans[i] = mp.getOrDefault(nums1[i], -1);
   }
   return ans;
 }
}
// @lc code=end
