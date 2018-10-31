/**
 * 
 */
package org.javaweb.showcase.test.binarytree;

/**
 * 
 * 
 * @see
 * @author wangwd
 * @date Oct 17, 2009 9:56:12 PM
 * @since JDK1.5.0_08
 * @version v1.0
 */
public class BinaryTree {
  private TreeNode root;

  public BinaryTree() {
  }

  public BinaryTree(Object[] objects) {
    for (int i = 0; i < objects.length; i++) {
      insert(objects[i]);
    }
  }

  public boolean insert(Object o) {
    if (root == null) {
      root = new TreeNode(o);
    } else {
      // 位于父结点
      TreeNode parent = null;
      TreeNode current = root;
      while (current != null) {
        if (((Comparable) o).compareTo(current.element) < 0) {
          parent = current;
          current = current.left;
        } else if (((Comparable) o).compareTo(current.element) > 0) {
          parent = current;
          current = current.right;
        } else {
          return false; // 已经存在一个相同的元素
        }
      }

      // 建立一个新的结点并加到父结点后
      if (((Comparable) o).compareTo(parent.element) < 0) {
        parent.left = new TreeNode(o);
      } else {
        parent.right = new TreeNode(o);
      }
    }

    return true; // 元素已经插入
  }

  public void inorder() {
    inorder(root);
  }

  private void inorder(TreeNode root) {
    if (root == null) {
      return;
    }

    inorder(root.left);
    System.out.print(root.element + " ");
    inorder(root.right);
  }

  public void postorder() {
    postorder(root);
  }

  private void postorder(TreeNode root) {
    if (root == null) {
      return;
    }

    postorder(root.left);
    postorder(root.right);
    System.out.print(root.element + " ");
  }

  public void preorder() {
    preorder(root);
  }

  private void preorder(TreeNode root) {
    if (root == null) {
      return;
    }

    System.out.print(root.element + " ");
    preorder(root.left);
    preorder(root.right);
  }

  private static class TreeNode {
    Object element;
    TreeNode left;
    TreeNode right;

    public TreeNode(Object o) {
      element = o;
    }
  }
}
