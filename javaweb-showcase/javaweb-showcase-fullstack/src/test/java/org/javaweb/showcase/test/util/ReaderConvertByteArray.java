/**
 * $Id: ReaderConvertByteArray.java 80 2017-01-20 04:21:11Z job2wd $
 */
package org.javaweb.showcase.test.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

/**
 * @author weidong_wang
 * @version $Revision: 80 $ $Date: 2017-01-20 12:21:11 +0800 (星期五, 20 一月 2017) $
 */
public class ReaderConvertByteArray {

  /**
   * 
   */
  public ReaderConvertByteArray() {
    super();
  }

  /**
   * @param reader
   * @return
   * @throws IOException
   */
  public byte[] toByteArray(Reader reader) throws IOException {
    return toByteArray(reader, null);
  }

  /**
   * @param reader
   * @return
   * @throws IOException
   */
  public byte[] toByteArrayBuffered(Reader reader) throws IOException {
    return toByteArrayBuffered(reader, null);
  }

  /**
   * @param reader
   * @param encoding
   * @return
   * @throws IOException
   */
  public byte[] toByteArray(Reader reader, String encoding) throws IOException {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    Writer writer = (encoding != null) ? new OutputStreamWriter(out, encoding) : new OutputStreamWriter(out);
    char[] c = new char[1024 * 10];
    int len = -1;
    while ((len = reader.read(c)) != -1) {
      writer.write(c, 0, len);
    }
    //writer.flush();
    //out.flush();
    byte[] b = out.toByteArray();
    //writer.close();
    //out.close();
    return b;
  }

  public String toString(Reader reader) throws IOException {
    Writer writer = new StringWriter();
    char[] c = new char[1024 * 10];
    int len = -1;
    while ((len = reader.read(c)) != -1) {
      writer.write(c, 0, len);
    }
    return writer.toString();
  }
  /**
   * @param reader
   * @param encoding
   * @return
   * @throws IOException
   */
  public byte[] toByteArrayBuffered(Reader reader, String encoding) throws IOException {
    return toByteArray(new BufferedReader(reader), encoding);
  }
}
