/**
 * 
 */
package org.javaweb.showcase.test.binarytree;

/**
 * 二叉树遍历操作
 * 
 * @see
 * @author wangwd
 * @date Oct 17, 2009 8:51:36 PM
 * @since JDK1.5.0_08
 * @version v1.0
 */
public class BinaryTreeIterator {

  public BinaryTreeBean binaryTree;

  /**
   * Constructor
   */
  public BinaryTreeIterator() {

  }

  /**
   * Constructor by parameter
   */
  public BinaryTreeIterator(BinaryTreeBean binaryTree) {
    this.binaryTree = binaryTree;
  }

  
  
  
  /**
   * @return the binaryTree
   */
  public BinaryTreeBean getBinaryTree() {
    return this.binaryTree;
  }

  /**
   * @param binaryTree
   *            the binaryTree to set
   */
  public void setBinaryTree(BinaryTreeBean binaryTree) {
    this.binaryTree = binaryTree;
  }

}
