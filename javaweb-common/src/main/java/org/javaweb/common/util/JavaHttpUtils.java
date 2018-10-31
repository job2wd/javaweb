/*
 * $Id: JavaHttpUtils.java 5096 2018-08-07 09:31:09Z wangweidong $
 */
package org.javaweb.common.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Set;


/**
 * @author wangwd
 * @version $Revision: 5096 $, 2014-5-28
 * @LastChanged $Author: wangweidong $, $Date:: 2018-08-07 17:31:09#$
 */
public class JavaHttpUtils {
  
  private static final String CHARSET_NAME = "UTF-8";

  public static String httpRequest(String url, RequestMethod requestMethod, File data) throws FileNotFoundException, IOException {
    return httpRequest(url, requestMethod, new FileInputStream(data));
  }

  public static String httpRequest(String url, RequestMethod requestMethod, InputStream data) throws IOException {
    HttpURLConnection connection = null;
    DataOutputStream out = null;
    InputStream in = null;
    try {
      connection = getHttpURLConnection(url, requestMethod);

      connection.setRequestProperty("Content-Length", String.valueOf(data.available()));
      connection.setRequestProperty("Content-type", "multipart/form-data");//; " +
      		//"boundary=---------------------------7d318fd100112");//application/octet-stream

      connection.connect();
      out = new DataOutputStream(connection.getOutputStream());

      outputData(out, data);

      in = connection.getInputStream();

      return read(in);
    } finally {
      release(out, in, connection);
    }
  }

  public static String httpRequest(String url, RequestMethod requestMethod, String data) throws IOException {
    HttpURLConnection connection = null;
    DataOutputStream out = null;
    InputStream in = null;
    try {
      connection = getHttpURLConnection(url, requestMethod);

      connection.setRequestProperty("Content-Length", String.valueOf(data.getBytes(CHARSET_NAME).length));
      
      connection.connect();
      out = new DataOutputStream(connection.getOutputStream());

      outputData(out, data);

      in = connection.getInputStream();

      return read(in);
    } finally {
      release(out, in, connection);
    }
  }

  public static String httpRequest(String url, RequestMethod requestMethod, Map<String, String> data) throws IOException {
    StringBuffer sb = new StringBuffer(256);
    
    Set<String> keys = data.keySet();
    int size = keys.size();
    int count = 1;
    
    for(String key : keys) {
      String value = data.get(key);
      sb.append(key).append("=").append(value);
      if(count < size) {
        sb.append("&");
      }
      count++;
    }
    return httpRequest(url, requestMethod, sb.toString());
  }

  private static void outputData(DataOutputStream outStream, Object data) throws IOException {
    if (data instanceof String) {
      outStream.write(((String) data).getBytes(CHARSET_NAME));
      outStream.flush();
    } else if (data instanceof InputStream) {
      writeInputStream2OuptStream((InputStream) data, outStream);
    } else if (data instanceof File) {
      writeInputStream2OuptStream(new FileInputStream((File)data), outStream);
    } else {
      //other ....
    }
  }
  
  private static void writeInputStream2OuptStream(InputStream inStream, OutputStream outStream) throws IOException {
    int len = 0;
    byte[] b = new byte[1024];
    
    while((len = inStream.read(b)) != -1) {
      outStream.write(b, 0, len);
      outStream.flush();
    }
  }

  private static String read(InputStream inStream) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(inStream, CHARSET_NAME));

    StringBuffer sb = new StringBuffer();
    int len = -1;
    char[] c = new char[256];

    while ((len = br.read(c)) != -1) {
      sb.append(c, 0, len);
    }
    return sb.toString();
  }

  private static void release(OutputStream outStream, InputStream inStream, HttpURLConnection connection) {
    try {
      if (outStream != null) {
        outStream.close();
      }
      if (inStream != null) {
        inStream.close();
      }
      if (connection != null) {
        connection.disconnect();
      }
    } catch (IOException e) {
      //ignore
    }
  }

  private static HttpURLConnection getHttpURLConnection(String url, RequestMethod requestMethod) throws IOException {
    HttpURLConnection connection = null;

    String requestUrl = url;// URLEncoder.encode(url, "UTF-8");
    URL netUrl = new URL(requestUrl);

    connection = (HttpURLConnection) netUrl.openConnection();

    connection.setDoOutput(true);// 如果通过post提交数据，必须设置允许对外输出数据
    connection.setDoInput(true);
    connection.setUseCaches(true);
    connection.setRequestMethod(requestMethod == null ? "GET" : (requestMethod == RequestMethod.GET ? "GET" : "POST"));
    connection.setConnectTimeout(60 * 1000 * 5);
    connection.setReadTimeout(60 * 1000 * 5);

    connection.setRequestProperty("Connection", "Keep-Alive");
    //connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
    connection.setRequestProperty("Charset", CHARSET_NAME);
    connection.setRequestProperty("pure-data", "yes");
    
    String contentType = defaultContentType;
//        contentType = "application/x-www-form-urlencoded; application/json; multipart/form-data; "
//            + "text/plain; text/html; text/xml; charset=" + CHARSET_NAME;
    
    contentType = "application/json";
    //connection.setRequestProperty("Client-Type", "abc123");
        
    String accept = defaultAccept;
    //accept = "application/json";
    
    connection.setRequestProperty("Content-Type", contentType);
    connection.setRequestProperty("Accept", accept);
    connection.setRequestProperty("Accept-Language", "zh-cn");
    connection.setRequestProperty("Accept-Encoding", "gzip, deflate");

    return connection;
  }
  
  public static enum RequestMethod {
    POST,
    GET;
  }
  
  private static final String defaultContentType = "text/plain; text/html; text/xml; image/gif; image/x-xbitmap; image/jpeg; image/pjpeg; application/vnd.ms-excel; "
      + "application/vnd.ms-powerpoint; application/msword; application/x-shockwave-flash; "
      + "application/x-quickviewplus; application/x-www-form-urlencoded; application/json; application/xml; "
      + "multipart/form-data; charset=" + CHARSET_NAME;
  
  private static final String defaultAccept = "text/plain, text/html, text/xml, image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, application/vnd.ms-excel, "
      + "application/vnd.ms-powerpoint, application/msword, application/x-shockwave-flash, "
      + "application/x-quickviewplus; charset=" + CHARSET_NAME;

}
