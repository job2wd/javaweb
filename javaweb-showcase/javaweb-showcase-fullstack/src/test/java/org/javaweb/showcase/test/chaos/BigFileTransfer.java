/**
 * $Id: BigFileTransfer.java 80 2017-01-20 04:21:11Z job2wd $
 */
package org.javaweb.showcase.test.chaos;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import junit.framework.TestCase;

/**
 * @author weidong_wang
 * @version $Revision: 80 $ $Date: 2017-01-20 12:21:11 +0800 (星期五, 20 一月 2017) $
 */
public class BigFileTransfer extends TestCase {

  public static final int BUFFER_SIZE = 1024 * 50;
  private byte[] buffer;

  /**
   * @param name
   */
  public BigFileTransfer(String name) {
    super(name);
    buffer = new byte[BUFFER_SIZE];
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

  private void startServer() throws Exception {
    ServerSocket socket = new ServerSocket(9000);
    Socket client = socket.accept();
    BufferedInputStream in = new BufferedInputStream(new FileInputStream("C:/TEMP/5m.jpg"));

    BufferedOutputStream out = new BufferedOutputStream(client.getOutputStream());

    int len = 0;
    while ((len = in.read(buffer)) > 0) {
      out.write(buffer, 0, len);
      System.out.print("#");
    }
    in.close();
    out.flush();
    out.close();
    client.close();
    socket.close();
    System.out.println("\nDone!");
  }

  private void startClient() throws Exception {
    Socket socket = new Socket("localhost", 9000);
    BufferedInputStream in = new BufferedInputStream(socket.getInputStream());

    BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("C:/TEMP/5m_new.jpg"));

    int len = 0;
    while ((len = in.read(buffer)) > 0) {
      out.write(buffer, 0, len);
      System.out.print("#");
    }
    in.close();
    out.flush();
    out.close();
    socket.close();
    System.out.println("\nDone!");
  }

  public void testBigFileTransfer() throws Exception {
    // Server Send message
    Thread server = new Thread(new Runnable(){
      @Override
      public void run() {
        try {
          startServer();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
    
    // Client Accept message
    Thread client = new Thread(new Runnable(){
      @Override
      public void run() {
        try {
          startClient();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
    // Start Server and Client
    server.start();
    client.start();
  }

}
