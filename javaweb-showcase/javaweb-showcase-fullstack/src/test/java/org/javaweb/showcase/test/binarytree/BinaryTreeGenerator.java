/**
 * 
 */
package org.javaweb.showcase.test.binarytree;

/**
 * 
 * 
 * @see
 * @author wangwd
 * @date Oct 17, 2009 9:06:49 PM
 * @since JDK1.5.0_08
 * @version v1.0
 */
public class BinaryTreeGenerator {

  private BinaryTreeBean btBean;
  
  /**
   * 
   */
  public BinaryTreeGenerator() {
    
  }
  
  /**
   * 
   */
  public BinaryTreeGenerator(BinaryTreeBean btBean) {
    this.btBean = btBean;
  }

  
  
  /**
   * @return the btBean
   */
  public BinaryTreeBean getBtBean() {
    return this.btBean;
  }

  /**
   * @param btBean the btBean to set
   */
  public void setBtBean(BinaryTreeBean btBean) {
    this.btBean = btBean;
  }

}
