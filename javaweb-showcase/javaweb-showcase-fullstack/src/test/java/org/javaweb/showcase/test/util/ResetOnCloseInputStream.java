/**
 * $Id: ResetOnCloseInputStream.java 80 2017-01-20 04:21:11Z job2wd $
 */
package org.javaweb.showcase.test.util;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author weidong_wang
 * @version $Revision: 80 $ $Date: 2017-01-20 12:21:11 +0800 (星期五, 20 一月 2017) $
 */
public class ResetOnCloseInputStream extends InputStream {

  /**
   * 
   */
  private final InputStream decorated;

  /**
   * 
   */

  /**
   * @param anInputStream
   */
  public ResetOnCloseInputStream(InputStream decorated) {
    if (!decorated.markSupported()) {
      throw new IllegalArgumentException("marking not supported");
    }

    decorated.mark(1 << 24); // magic constant: BEWARE
    this.decorated = decorated;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.io.InputStream#read()
   */
  @Override
  public int read() throws IOException {
    return decorated.read();
  }

  /**
   * not close input stream "<code>decorated</code>"</p> call
   * <code>decorated</code>'s <code>{@link #decorated.reset()}</code>
   * 
   * @see java.io.InputStream#close()
   */
  public void close() throws IOException {
    decorated.reset();
  }

  /**
   * Close the input stream: <code>decorated</code>
   * 
   * @param close
   *          </p>if close is true: <code>decorated.close()</code>. else call
   *          <code>{@link #close()}</code>
   * @throws IOException
   */
  public void close(boolean close) throws IOException {
    if (close) {
      decorated.close();
    } else {
      close();
    }
  }
}
