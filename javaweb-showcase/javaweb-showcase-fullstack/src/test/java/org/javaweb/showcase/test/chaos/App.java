package org.javaweb.showcase.test.chaos;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
    
    public int retrieveJavaSystemProperties() {
      Properties props = System.getProperties();
      Set keySet = props.keySet();
      int num = 0;
      for(Object key : keySet) {
        System.out.println(key + "=" + props.getProperty(key.toString()));
        num++;
      }
      return num;
    }
    
    public int retrieveJavaSystemEnv() {
      Map<String, String> envs = System.getenv();
      int num = 0;
      for(String key : envs.keySet()) {
        System.out.println(key + "=" + envs.get(key));
        num++;
      }
      return num;
    }
}
