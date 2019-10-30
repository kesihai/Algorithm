package tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;


class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode(int x) {
    val = x;
  }
}

class Solution {
  public List<Integer> postorderTraversal(TreeNode root) {
    LinkedList<Integer> ans = new LinkedList<>();
    TreeNode preVisitNode = null;
    Stack<TreeNode> s = new Stack<>();
    if (root != null) {
      s.push(root);
    }
    while (!s.empty()) {
      TreeNode node = s.peek();
      if ((node.left == null && node.right == null)
          || (node.left == preVisitNode && node.right == null)
          || (node.right != null && node.right == preVisitNode)) {
        ans.add(node.val);
        preVisitNode = node;
        s.pop();
      } else {
        if (node.right != null) {
          s.push(node.right);
        }
        if (node.left != null) {
          s.push(node.left);
        }
      }
    }
    return ans;
  }
}

public class BinaryTreePostVisit_145 {
}
