package design;

class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode(int x) {
    val = x;
  }
}

class Codec {
  private static final String SPLIT = ",";
  private static final String NULL = "null";
  private int pos = 0;

  // Encodes a tree to a single string.
  public String serialize(TreeNode root) {
    StringBuilder builder = new StringBuilder();
    serialize(root, builder);
    return builder.toString();
  }

  private void serialize(TreeNode node, StringBuilder builder) {
    if (node == null) {
      if (builder.length() != 0) {
        builder.append(SPLIT);
      }
      builder.append(NULL);
      return;
    }
    if (builder.length() != 0) {
      builder.append(SPLIT);
    }
    builder.append(node.val);
    serialize(node.left, builder);
    serialize(node.right, builder);
  }

  // Decodes your encoded data to tree.
  public TreeNode deserialize(String data) {
    String[] strings = data.split(SPLIT);
    pos = 0;
    return deserialize(strings);
  }

  private TreeNode deserialize(String[] strs) {
    TreeNode node = null;
    if (strs[pos].equals(NULL)) {
      pos++;
      return node;
    } else {
      node = new TreeNode(Integer.parseInt(strs[pos]));
      pos++;
      node.left = deserialize(strs);
      node.right = deserialize(strs);
    }
    return node;
  }
}

public class SerializeDesializeBinaryTree {
  public static void main(String[] args) {
//    TreeNode node = new TreeNode()
  }
}
