/**
 * $Id: Game.java 80 2017-01-20 04:21:11Z job2wd $
 */
package org.javaweb.showcase.test.agile.chapter6.exm1;

/**
 * @author weidong_wang
 * @version $Revision: 80 $ $Date: 2017-01-20 12:21:11 +0800 (星期五, 20 一月 2017) $
 */
public class Game {

  private int itsCurrentThrows;
  private int[] itsThrows = new int[21];
  private boolean firstThrow = true;

  private int itsScore;
  private int itsCurrentFrame = 1;
  /**
   * 
   */
  public Game() {
    // TODO Auto-generated constructor stub
  }

  public int getScore() {
    // TODO Auto-generated method stub
    return getScoreForFrame(getCurrentFrame()-1);
  }

  public void add(int pins) {
    itsThrows[itsCurrentThrows++] = pins;
    itsScore += pins;
    
    adjustCurrentFrame(pins);
  }

  /**
   * 
   */
  private void adjustCurrentFrame(int pins) {
    if (firstThrow) {
      if (pins == 10) {
        itsCurrentFrame++;
      } else {
        firstThrow = false;
      }
    } else{
      firstThrow = true;
      itsCurrentFrame++;
    }
  }

  public int getScoreForFrame_ori(int frame) {
    int score = 0;
    for (int ball = 0; frame > 0 && ball < itsCurrentThrows; ball += 2, frame--) {
      score += itsThrows[ball] + itsThrows[ball + 1];
    }
    return score;
  }

  public int getScoreForFrame_refactor1(int frame) {
    int score = 0;
    int ball = 0;
    for (int currentFrame = 0; currentFrame < frame; currentFrame++) {
      int firstThrow = itsThrows[ball++];
      int secondThrow = itsThrows[ball++];
      score += firstThrow + secondThrow;
    }
    return score;
  }

  public int getScoreForFrame_refactor2(int frame) {
    int score = 0;
    int ball = 0;
    for (int currentFrame = 0; currentFrame < frame; currentFrame++) {
      int firstThrow = itsThrows[ball++];
      int secondThrow = itsThrows[ball++];
      int frameScore = firstThrow + secondThrow;
      if (frameScore == 10) {
        score += frameScore + itsThrows[ball];
      } else {
        score += frameScore;
      }
    }
    return score;
  }
  
  public int getScoreForFrame(int frame) {
    int score = 0;
    int ball = 0;
    for (int currentFrame = 0; currentFrame < frame; currentFrame++) {
      int firstThrow = itsThrows[ball++];
      if (firstThrow == 10) {
        score += 10 + itsThrows[ball] + itsThrows[ball+1];
      } else {
        int secondThrow = itsThrows[ball++];
        int frameScore = firstThrow + secondThrow;
        if (frameScore == 10) {
          score += frameScore + itsThrows[ball];
        } else {
          score += frameScore;
        }
      }
    }
    return score;
  }

  public int getCurrentFrame() {
    // TODO Auto-generated method stub
    return itsCurrentFrame;
  }
}
