package tree;

import java.util.LinkedList;
import java.util.List;

/**
 * Leetcode 二叉树 两个节点 最低父节点
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
 *
 */
public class lowestCommonAncestor {

  class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  class Solution {
    List<List<TreeNode>> paths = new LinkedList<>();
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
      if (p == q) {
        return p;
      }
      paths.clear();
      List<TreeNode> temp = new LinkedList<>();
      dfs(root, p, q, temp);
      if (paths.size() != 2) {
        return null;
      }
      int index = 0;
      for (; index < paths.get(0).size() && index < paths.get(1).size(); index++) {
        if (paths.get(0).get(index) != paths.get(1).get(index)) {
          return paths.get(0).get(index - 1);
        }
      }
      return paths.get(0).get(index - 1);
    }

    public void dfs(TreeNode root, TreeNode a, TreeNode b, List<TreeNode> temp) {
      if (root == null) {
        return;
      }
      temp.add(root);
      if (root == a) {
        paths.add(new LinkedList<>(temp));
      }
      if (root == b) {
        paths.add(new LinkedList<>(temp));
      }
      dfs(root.left, a, b, temp);
      dfs(root.right, a, b, temp);
      temp.remove(temp.size() - 1);
    }
  }
}
