/**
 * $Id: TransferBigFile.java 80 2017-01-20 04:21:11Z job2wd $
 */
package org.javaweb.showcase.test.chaos;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author weidong_wang
 * @version $Revision: 80 $ $Date: 2017-01-20 12:21:11 +0800 (星期五, 20 一月 2017) $
 */
public class TransferBigFile {

  public static final int BUFFER_SIZE = 1024 * 50;
  private byte[] buffer;

  /**
   * 
   */
  public TransferBigFile() {
    buffer = new byte[BUFFER_SIZE];
  }

  private void startServer() throws Exception {
    long start = System.currentTimeMillis();

    ServerSocket socket = new ServerSocket(9000);
    Socket client = socket.accept();
    BufferedInputStream in = new BufferedInputStream(new FileInputStream("C:/TEMP/100m.rar"));

    BufferedOutputStream out = new BufferedOutputStream(client.getOutputStream());

    int len = 0;
    while ((len = in.read(buffer)) > 0) {
      out.write(buffer, 0, len);
      // System.out.print("#");
    }
    in.close();
    out.flush();
    out.close();
    client.close();
    socket.close();
    System.out.println("\nDone!");

    long end = System.currentTimeMillis();

    System.out.println("Server send Take time: " + (end - start) / 1000 + " Seconds");
  }

  private void startClient() throws Exception {
    long start = System.currentTimeMillis();

    Socket socket = new Socket("localhost", 9000);
    BufferedInputStream in = new BufferedInputStream(socket.getInputStream());

    BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("C:/TEMP/100m_new.rar"));

    int len = 0;
    while ((len = in.read(buffer)) > 0) {
      out.write(buffer, 0, len);
      // System.out.print("#");
    }
    in.close();
    out.flush();
    out.close();
    socket.close();
    System.out.println("\nDone!");

    long end = System.currentTimeMillis();

    System.out.println("Client accept Take time: " + (end - start) / 1000 + " Seconds");
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    final TransferBigFile transfer = new TransferBigFile();

    // Server Send message
    Thread server = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          transfer.startServer();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });

    // Client Accept message
    Thread client = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          transfer.startClient();
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
