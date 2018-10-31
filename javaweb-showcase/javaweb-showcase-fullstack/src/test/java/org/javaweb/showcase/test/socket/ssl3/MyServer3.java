package org.javaweb.showcase.test.socket.ssl3;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.TrustManagerFactory;

public class MyServer3 {

  private final static Logger logger = Logger.getLogger(MyServer3.class.getName());

  public static void main(String[] args) {
    Selector selector = null;
    ServerSocketChannel serverSocketChannel = null;

    try {
      // Selector for incoming time requests
      selector = Selector.open();

      // Create a new server socket and set to non blocking mode
      serverSocketChannel = ServerSocketChannel.open();
      serverSocketChannel.configureBlocking(false);

      // Bind the server socket to the local host and port
      serverSocketChannel.socket().setReuseAddress(true);
      serverSocketChannel.socket().bind(new InetSocketAddress(10000));

      // Register accepts on the server socket with the selector. This
      // step tells the selector that the socket wants to be put on the
      // ready list when accept operations occur, so allowing multiplexed
      // non-blocking I/O to take place.
      serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

      // Here's where everything happens. The select method will
      // return when any operations registered above have occurred, the
      // thread has been interrupted, etc.
      while (selector.select() > 0) {
        // Someone is ready for I/O, get the ready keys
        Iterator<SelectionKey> it = selector.selectedKeys().iterator();

        // Walk through the ready keys collection and process date requests.
        while (it.hasNext()) {
          SelectionKey readyKey = it.next();
          it.remove();

          // The key indexes into the selector so you
          // can retrieve the socket that's ready for I/O
          execute((ServerSocketChannel) readyKey.channel());
        }
      }
    } catch (ClosedChannelException ex) {
      logger.log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      logger.log(Level.SEVERE, null, ex);
    } finally {
      try {
        selector.close();
      } catch (Exception ex) {
      }
      try {
        serverSocketChannel.close();
      } catch (Exception ex) {
      }
    }
  }
  private SSLContext     sslContext;
//the ssl context initialization
  private void createSSLContext() throws GeneralSecurityException, FileNotFoundException, IOException {
    KeyStore ks = KeyStore.getInstance("JKS");
    KeyStore ts = KeyStore.getInstance("JKS");

    char[] passphrase = "changeit".toCharArray();

    ks.load(new FileInputStream("D:/server.jks"), passphrase);
    ts.load(new FileInputStream("D:/serverTrust.jks"), passphrase);

    KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
    kmf.init(ks, passphrase);

    TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
    tmf.init(ts);

    SSLContext sslCtx = SSLContext.getInstance("SSL");

    sslCtx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

    sslContext = sslCtx;
  }
  private SSLEngine      sslEngine;
  private void createSSLEngines() {
    sslEngine = sslContext.createSSLEngine("127.0.0.1", 33444);
    sslEngine.setUseClientMode(false);//work in a server mode
    sslEngine.setNeedClientAuth(true);//need client authentication
  }
  
  private static void execute(ServerSocketChannel serverSocketChannel) throws IOException {
    SocketChannel socketChannel = null;
    try {
      socketChannel = serverSocketChannel.accept();
      MyRequestObject myRequestObject = receiveData(socketChannel);
      logger.log(Level.INFO, myRequestObject.toString());

      MyResponseObject myResponseObject = new MyResponseObject("response for " + myRequestObject.getName(), "response for "
          + myRequestObject.getValue());
      sendData(socketChannel, myResponseObject);
      logger.log(Level.INFO, myResponseObject.toString());
    } finally {
      try {
        socketChannel.close();
      } catch (Exception ex) {
      }
    }
  }

  private static MyRequestObject receiveData(SocketChannel socketChannel) throws IOException {
    MyRequestObject myRequestObject = null;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ByteBuffer buffer = ByteBuffer.allocate(1024);

    try {
      byte[] bytes;
      int size = 0;
      while ((size = socketChannel.read(buffer)) >= 0) {
        buffer.flip();
        bytes = new byte[size];
        buffer.get(bytes);
        baos.write(bytes);
        buffer.clear();
      }
      bytes = baos.toByteArray();
      Object obj = SerializableUtil.toObject(bytes);
      myRequestObject = (MyRequestObject) obj;
    } finally {
      try {
        baos.close();
      } catch (Exception ex) {
      }
    }
    return myRequestObject;
  }

  private static void sendData(SocketChannel socketChannel, MyResponseObject myResponseObject) throws IOException {
    byte[] bytes = SerializableUtil.toBytes(myResponseObject);
    ByteBuffer buffer = ByteBuffer.wrap(bytes);
    socketChannel.write(buffer);
  }
}
