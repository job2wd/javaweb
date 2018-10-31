/**
 * $Id: TestFrame.java 80 2017-01-20 04:21:11Z job2wd $
 */
package org.javaweb.showcase.test.agile.chapter6.exm1;

import junit.framework.TestCase;

/**
 * @author weidong_wang
 * @version $Revision: 80 $ $Date: 2017-01-20 12:21:11 +0800 (星期五, 20 一月 2017) $
 */
public class TestFrame extends TestCase {

  private Game g;

  /**
   * @param name
   */
  public TestFrame(String name) {
    super(name);
  }

  /*
   * (non-Javadoc)
   * 
   * @see junit.framework.TestCase#setUp()
   */
  @Override
  protected void setUp() throws Exception {
    g = new Game();
    super.setUp();
  }

  /*
   * (non-Javadoc)
   * 
   * @see junit.framework.TestCase#tearDown()
   */
  @Override
  protected void tearDown() throws Exception {
    super.tearDown();
  }

  public void testScoreNoThrows() {
    Frame f = new Frame();
    assertEquals(0, f.getScore());
  }

  public void testAddOneThrow() {
    Frame f = new Frame();
    f.add(5);
    assertEquals(5, f.getScore());
  }

  public void testAddMutipleThrow() {
    Frame f = new Frame();
    f.add(5);
    assertEquals(5, f.getScore());

    Frame f2 = new Frame();
    f2.add(3);
    assertEquals(3, f2.getScore());

    assertEquals(5, f.getScore());

    // ////////////////////////////////
    Frame f3 = new Frame();
    f3.add(3);
    f3.add(7);
    assertEquals(10, f3.getScore());

  }

//  public void testOneThrows() {
//    g.add(5);
//    assertEquals(5, g.getScore());
//
//    assertEquals(1, g.getCurrentFrame());
//  }

  public void testTwoThrowsNoMark() {
    g.add(5);
    g.add(4);
    assertEquals(9, g.getScore());
    
    assertEquals(2, g.getCurrentFrame());
  }

  public void testFourThrowsNoMark() {
    g.add(5);
    g.add(4);
    g.add(7);
    g.add(2);
    assertEquals(18, g.getScore());
    assertEquals(9, g.getScoreForFrame(1));
    assertEquals(18, g.getScoreForFrame(2));
    
    assertEquals(3, g.getCurrentFrame());
  }

  public void testSimpleSpare() {
    g.add(3);
    g.add(7);
    g.add(3);
    assertEquals(13, g.getScoreForFrame(1));
  }

  public void testSimpleFrameAfterSpare() {
    g.add(3);
    g.add(7);
    g.add(3);
    g.add(2);
    assertEquals(13, g.getScoreForFrame(1));
    assertEquals(18, g.getScoreForFrame(2));

    assertEquals(18, g.getScore());
    
    assertEquals(3, g.getCurrentFrame());

  }
  
  public void testPerfectGame() {
    for (int i = 0; i < 12; i++) {
      g.add(10);
    }
    assertEquals(300, g.getScore());
    assertEquals(10, g.getCurrentFrame());
  }

}
