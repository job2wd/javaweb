/**
 * $Id: Base64Test.java 80 2017-01-20 04:21:11Z job2wd $
 */
package org.javaweb.showcase.test.util;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import junit.framework.TestCase;

import org.apache.commons.codec.binary.Base64InputStream;
import org.apache.commons.codec.binary.Base64OutputStream;
import org.javaweb.showcase.util.Base64Utils;


/**
 * @author weidong_wang
 * @version $Revision: 80 $ $Date: 2017-01-20 12:21:11 +0800 (星期五, 20 一月 2017) $
 */
public class Base64Test extends TestCase {

  /**
   * @param name
   */
  public Base64Test(String name) {
    super(name);
  }

  /*
   * (non-Javadoc)
   * 
   * @see junit.framework.TestCase#setUp()
   */
  @Override
  protected void setUp() throws Exception {
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

  public void testBase64Encode() throws Exception {
    long start =  System.currentTimeMillis();
    
    FileInputStream fin = new FileInputStream("C:/TEMP/AA.txt");
    FileOutputStream fout = new FileOutputStream("C:/TEMP/100m_encode.base64.bin");

    OutputStream bout = new Base64Utils.OutputStream(fout);

    byte[] b = new byte[1024 * 1024];
    int len = -1;
    while ((len = fin.read(b)) > 0) {
      bout.write(b, 0, len);
    }
    bout.flush();
    bout.close();
    
    long end = System.currentTimeMillis();
    System.out.println("testBase64Encode: " + (end - start));
  }

  public void testBase64Decode() throws Exception {
    long start =  System.currentTimeMillis();
    
    FileInputStream fin = new FileInputStream("C:/TEMP/output.attach.b64");
    
    ByteArrayOutputStream bout = new ByteArrayOutputStream();

    byte[] b = new byte[1024 * 1024];
    int len = -1;
    while ((len = fin.read(b)) > 0) {
      bout.write(b, 0, len);
    }
    byte[] decres = Base64Utils.decode(bout.toByteArray());
    
    System.out.println(new String(decres));
    
    bout.flush();
    bout.close();
    
    assertNotNull(decres);
    
    long end = System.currentTimeMillis();
    System.out.println("testBase64Decode: " + (end - start));
  }

  public void testBase64OutputStreamEncode() throws Exception {
    long start =  System.currentTimeMillis();

    FileInputStream fin = new FileInputStream("C:/TEMP/AA.txt");
    FileOutputStream fout = new FileOutputStream("C:/TEMP/100m_encode.base64OutputStream.bin");

    OutputStream bout = new Base64OutputStream(fout,true);

    byte[] b = new byte[1024 * 1024];
    int len = -1;
    while ((len = fin.read(b)) > 0) {
      bout.write(b, 0, len);
    }
    bout.flush();
    bout.close();
    
    long end = System.currentTimeMillis();
    System.out.println("testBase64OutputStreamEncode: " + (end - start));
  }
  
  public void testBase64InputStreamDecode() throws Exception {
    // first encode
    testBase64OutputStreamEncode();
    
    // decode
    long start =  System.currentTimeMillis();

    FileInputStream fin_base64 = new FileInputStream("C:/TEMP/100m_encode.base64OutputStream.bin");
    FileOutputStream fout = new FileOutputStream("C:/TEMP/100m_decode.base64InputStream.source.rar");

    InputStream bin = new Base64InputStream(fin_base64,false);

    byte[] b = new byte[1024 * 1024];
    int len = -1;
    while ((len = bin.read(b)) > 0) {
      fout.write(b, 0, len);
    }
    fout.flush();
    fout.close();
    
    long end = System.currentTimeMillis();
    System.out.println("testBase64InputStreamDecode: " + (end - start));
    
  }
  
  public void testDecodeBase64Data() throws Exception {
    String bd = "GiGfz1PvcG1XHqdr8URdR6/KcfAkdNWnx21NfZDXxDlSLRHQL8LcIMDoBsyvTVE7oVyTQp1GHogMqld8oRMoaJUZ+Ygf8o4ca8UeWNQAkGQfhsZtgcFrfak1gP0SRPJHrDuMvYkK7/V3AYTRQG95zPLCr1rb+v6/S3SEJzHW+Nk=";
    byte[] res = Base64Utils.decode(bd.trim());
    
    assertNotNull("test base64 decode", res);
  }
}
