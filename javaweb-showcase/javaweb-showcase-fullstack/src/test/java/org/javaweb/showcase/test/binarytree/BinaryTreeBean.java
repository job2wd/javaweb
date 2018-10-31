/**
 * 
 */
package org.javaweb.showcase.test.binarytree;

import java.io.Serializable;

/**
 * 二叉树实体对象
 * 
 * @see
 * @author wangwd
 * @date Oct 17, 2009 8:48:29 PM
 * @since JDK1.5.0_08
 * @version v1.0
 */
public class BinaryTreeBean implements Serializable {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = -2778541216285517548L;

  private Object data;

  private BinaryTreeBean leftChild;

  private BinaryTreeBean rightChild;

  /**
   * Constructor
   */
  public BinaryTreeBean() {

  }

  /**
   * Constructor by parameter
   */
  public BinaryTreeBean(Object data) {
    this.data = data;
  }

  /**
   * @return the data
   */
  public Object getData() {
    return this.data;
  }

  /**
   * @param data
   *            the data to set
   */
  public void setData(Object data) {
    this.data = data;
  }

  /**
   * @return the leftChild
   */
  public BinaryTreeBean getLeftChild() {
    return this.leftChild;
  }

  /**
   * @param leftChild
   *            the leftChild to set
   */
  public void setLeftChild(BinaryTreeBean leftChild) {
    this.leftChild = leftChild;
  }

  /**
   * @return the rightChild
   */
  public BinaryTreeBean getRightChild() {
    return this.rightChild;
  }

  /**
   * @param rightChild
   *            the rightChild to set
   */
  public void setRightChild(BinaryTreeBean rightChild) {
    this.rightChild = rightChild;
  }

}
