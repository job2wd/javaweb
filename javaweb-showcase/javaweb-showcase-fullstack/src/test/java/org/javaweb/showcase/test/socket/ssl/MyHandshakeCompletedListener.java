package org.javaweb.showcase.test.socket.ssl;

import javax.net.ssl.HandshakeCompletedEvent;
import javax.net.ssl.HandshakeCompletedListener;

public class MyHandshakeCompletedListener implements HandshakeCompletedListener {

  @Override
  public void handshakeCompleted(HandshakeCompletedEvent arg0) {
    System.out.println("Handshake finished successfully");
  }
}
