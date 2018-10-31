/**
 * $Id: BeanShellSimpleExample.java 80 2017-01-20 04:21:11Z job2wd $
 */
package org.javaweb.showcase.test.chaos;


/**
 * @author weidong_wang
 * @version $Revision: 80 $ $Date: 2017-01-20 12:21:11 +0800 (星期五, 20 一月 2017) $
 */
public class BeanShellSimpleExample {

  private String animal;

  private int num1;
  
  private int num2;
  
  private int sum;
  /**
   * 
   */
  public BeanShellSimpleExample() {

  }

  public void addNum1AndNum2() {
    sum = num1 + num2;
  }

  /**
   * @return the animal
   */
  public String getAnimal() {
    return animal;
  }

  /**
   * @param animal the animal to set
   */
  public void setAnimal(String animal) {
    this.animal = animal;
  }

  /**
   * @return the num1
   */
  public int getNum1() {
    return num1;
  }

  /**
   * @param num1 the num1 to set
   */
  public void setNum1(int num1) {
    this.num1 = num1;
  }

  /**
   * @return the num2
   */
  public int getNum2() {
    return num2;
  }

  /**
   * @param num2 the num2 to set
   */
  public void setNum2(int num2) {
    this.num2 = num2;
  }

  /**
   * @return the sum
   */
  public int getSum() {
    return sum;
  }

  /**
   * @param sum the sum to set
   */
  public void setSum(int sum) {
    this.sum = sum;
  }
}
