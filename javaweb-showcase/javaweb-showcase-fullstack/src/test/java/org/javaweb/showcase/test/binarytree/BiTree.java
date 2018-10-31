/**
 * 
 */
package org.javaweb.showcase.test.binarytree;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * 
 * @see
 * @author wangwd
 * @date Oct 17, 2009 9:09:50 PM
 * @since JDK1.5.0_08
 * @version v1.0
 */
public class BiTree {

  private Object data;
  private BiTree leftChild;
  private BiTree rightChild;

  public BiTree() {
  }

  public BiTree(Object data) {
    this.data = data;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

  public BiTree getLeftChild() {
    return leftChild;
  }

  public void setLeftChild(BiTree bt) {
    leftChild = bt;
  }

  public BiTree getRightChild() {
    return rightChild;
  }

  public void setRightChild(BiTree bt) {
    rightChild = bt;
  }

  // 先序二叉树字符串
  public String toStringByPreOrder() {
    StringBuffer result = new StringBuffer();
    result.append(data);
    result.append("   ");
    if (leftChild != null) {
      result.append(leftChild.toStringByPreOrder());
    }
    if (rightChild != null) {
      result.append(rightChild.toStringByPreOrder());
    }
    return result.toString();
  }

  // 中序二叉树字符串
  public String toStringByInOrder() {
    StringBuffer result = new StringBuffer();
    if (leftChild != null) {
      result.append(leftChild.toStringByInOrder());
    }
    result.append(data);
    result.append("   ");
    if (rightChild != null) {
      result.append(rightChild.toStringByInOrder());
    }
    return result.toString();
  }

  // 后序二叉树字符串
  public String toStringByAfterOrder() {
    StringBuffer result = new StringBuffer();
    if (leftChild != null) {
      result.append(leftChild.toStringByAfterOrder());
    }
    if (rightChild != null) {
      result.append(rightChild.toStringByAfterOrder());
    }
    result.append(data);
    result.append("   ");
    return result.toString();
  }

  // 先序生成二叉树
  public static BiTree createBiTreeByPreOrder(Iterator it) {
    BiTree bt = null;
    Object o = it.next();
    if (o != null) {
      bt = new BiTree(o);
      bt.setLeftChild(createBiTreeByPreOrder(it));
      bt.setRightChild(createBiTreeByPreOrder(it));
    }
    return bt;
  }

  // 中序生成二叉树
  public static BiTree createBiTreeByInOrder(Iterator it) {
    BiTree bt = null;
    Object o = it.next();
    if (o != null) {
      bt = new BiTree();
      bt.setLeftChild(createBiTreeByInOrder(it));
      bt.setData(o);
      bt.setRightChild(createBiTreeByInOrder(it));
    }
    return bt;
  }

  // 后序生成二叉树
  public static BiTree createBiTreeByAfterOrder(Iterator it) {
    BiTree bt = null;
    Object o = it.next();
    if (o != null) {
      bt = new BiTree();
      bt.setLeftChild(createBiTreeByAfterOrder(it));
      bt.setRightChild(createBiTreeByAfterOrder(it));
      bt.setData(o);
    }
    return bt;
  }

  public static void main(String[] args) {
    System.out.println("--------------------     bt     ---------------------");
    BiTree bt = new BiTree("A");
    bt.setLeftChild(new BiTree("B"));
    
    bt.setRightChild(new BiTree("C"));
    System.out.println("pre   order   :   " + bt.toStringByPreOrder());
    System.out.println("in   order   :   " + bt.toStringByInOrder());
    System.out.println("after   order   :   " + bt.toStringByAfterOrder());

    System.out.println("--------------------     bt2     ---------------------");
    BiTree bt2 = null;
    Object[] sequence = new Object[] { "A", "B", "C", null, null, "D", "E", null, "G", null, null, "F", null, null, null };
    List l = Arrays.asList(sequence);
    System.out.println("sequence   :   " + l);

    bt2 = createBiTreeByPreOrder(l.iterator());
    System.out.println("pre   order   :   " + bt2.toStringByPreOrder());

    bt2 = createBiTreeByInOrder(l.iterator());
    System.out.println("in   order   :   " + bt2.toStringByInOrder());

    bt2 = createBiTreeByAfterOrder(l.iterator());
    System.out.println("after   order   :   " + bt2.toStringByAfterOrder());
  }
}
