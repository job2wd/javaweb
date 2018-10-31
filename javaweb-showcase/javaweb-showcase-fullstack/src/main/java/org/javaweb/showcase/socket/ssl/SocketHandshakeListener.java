package org.javaweb.showcase.socket.ssl;

import javax.net.ssl.HandshakeCompletedEvent;
import javax.net.ssl.HandshakeCompletedListener;

public class SocketHandshakeListener implements HandshakeCompletedListener {

  @Override
  public void handshakeCompleted(HandshakeCompletedEvent arg0) {
    Logger.info("Handshake finished successfully");
  }
}
