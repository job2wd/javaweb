/**
 * $Id: Frame.java 80 2017-01-20 04:21:11Z job2wd $
 */
package org.javaweb.showcase.test.agile.chapter6.exm1;

/**
 * @author weidong_wang
 * @version $Revision: 80 $ $Date: 2017-01-20 12:21:11 +0800 (星期五, 20 一月 2017) $
 */
public class Frame {

  /**
   * 
   */
  public Frame() {
    // TODO Auto-generated constructor stub
  }

  public int getScore() {
    // TODO Auto-generated method stub
    return itsScore;
  }

  public void add(int pins) {
    itsScore += pins;
  }

  private int itsScore;
}
