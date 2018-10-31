/**
 * $Id: SimpleJavaBean.java 80 2017-01-20 04:21:11Z job2wd $
 */
package org.javaweb.showcase.test.xstream;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Wang WD
 * @version $Revision: 80 $ $Date: 2017-01-20 12:21:11 +0800 (星期五, 20 一月 2017) $
 */
public class SimpleJavaBean {

  private String                     id;
  private String                     name;
  private String                     parentNode;

  private Collection<SimpleJavaBean> children = new ArrayList<SimpleJavaBean>();

  /**
   * 
   */
  public SimpleJavaBean() {
    super();
  }

  public void addChild(SimpleJavaBean child) {
    children.add(child);
  }

  /**
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * @param id
   *          the id to set
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name
   *          the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the parentNode
   */
  public String getParentNode() {
    return parentNode;
  }

  /**
   * @param parentNode
   *          the parentNode to set
   */
  public void setParentNode(String parentNode) {
    this.parentNode = parentNode;
  }

  /**
   * @return the children
   */
  public Collection<SimpleJavaBean> getChildren() {
    return children;
  }

  /**
   * @param children
   *          the children to set
   */
  public void setChildren(Collection<SimpleJavaBean> children) {
    this.children = children;
  }

}
