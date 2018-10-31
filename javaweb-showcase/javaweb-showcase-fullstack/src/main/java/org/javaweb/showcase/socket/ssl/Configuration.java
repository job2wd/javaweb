package org.javaweb.showcase.socket.ssl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {

  private static Properties config;
  public static String configLocation;

  public static Properties getConfig() throws Exception {
    if(config != null) {
      return config;
    }
    
    File configFile = new File(configLocation);

    if (configFile.exists() && configFile.isFile() && configFile.canRead()) {
      InputStream input = new FileInputStream(configFile);
            
      config = new Properties();
      config.load(input);
      
      input.close();
    }
    
    return config;
  }
}
