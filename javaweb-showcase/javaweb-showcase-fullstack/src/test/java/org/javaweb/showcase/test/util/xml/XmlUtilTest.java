package org.javaweb.showcase.test.util.xml;

import junit.framework.TestCase;

public class XmlUtilTest extends TestCase{


//  public static void main(String[] args) {
//    String docPath = "D:/PKI/workspace/testexample/myXmlTest.xml";
//    String xpath = "/ServicesConfig/CARootCert/RootCert[@name='aaa'][@id='111']";
//    //testGetXmlFileDocument(docPath);
//    //testGetElement(docPath,xpath,1);
//    //testGetElementCount(docPath,xpath);
//    //String xpath1 = "/ServicesConfig/CARootCert/RootCert/name";
//    //testGetAttributeValues(docPath,xpath1);
//    //testGetElementText(docPath,xpath1,0);
//    //testGetElements(docPath,xpath);
//    //testSetAttributeValues(docPath,xpath);
//    //testSetElementText(docPath,xpath1);
//    String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><ServicesConfig version=\"1.0\"><CARootCert><RootCert name=\"aaa\" id=\"111\"><name>Hello beijing 2008!!!</name><dn>CN=DemoCA,O=JIT,C=CN</dn><sn>11BD03009E9A971E</sn><issuer>CN=DemoCA,O=JIT,C=CN</issuer><srcfile>cert_11bd03009e9a971eFaaaa.cer</srcfile></RootCert><RootCert name=\"aaa\" id=\"222\"><name>rootcert</name><dn>CN=DemoCA,O=JIT,C=CN</dn><sn>11BD03009E9A971E</sn><issuer>CN=DemoCA,O=JIT,C=CN</issuer><srcfile>cert_11bd03009e9a971eFbbb.cer</srcfile></RootCert></CARootCert></ServicesConfig>";
//    testGetXmlStrDocument(xmlStr);
//    testGetElement(xpath,0,xmlStr);
//  }
//
//    //==========================以下方法为测试用=========================//
//
//  /**
//   * 测试方法: getXmlFileDocument(String docFilePath)
//   * @param docPath
//   */
//  private static void testGetXmlFileDocument(String docPath){
//    try {
//      Document doc = XmlUtils.getXmlFileDocument(docPath);
//      System.out.println(doc);
//    } catch (Exception e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
//  }
//  private static void testGetXmlStrDocument(String xmlStr){
//    try {
//      Document doc = XmlUtils.getXmlStrDocument(xmlStr);
//      System.out.println(doc);
//    } catch (Exception e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
//  }
//  private static void testGetElement(String xpath,int index,String xmlStr){
//    try {
//      Document doc = XmlUtils.getXmlStrDocument(xmlStr);
//      Element el = XmlUtils.getElement(doc,xpath,index);
//      System.out.println(el);
//    } catch (Exception e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
//  }
//  /**
//   * 测试方法: getElement(Document doc, String xpath, int ino)
//   * @param docPath
//   * @param xpath
//   * @param index
//   */
//  private static void testGetElement(String docPath,String xpath,int index){
//    try {
//      Document doc = XmlUtils.getXmlFileDocument(docPath);
//      Element el = XmlUtils.getElement(doc,xpath,index);
//      System.out.println(el);
//    } catch (Exception e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
//  }
//
//  /**
//   * 测试方法: getElementCount(Document doc, String xpath)
//   *
//   */
//  private static void testGetElementCount(String docPath,String xpath){
//    try {
//      Document doc = XmlUtils.getXmlFileDocument(docPath);
//      int count = XmlUtils.getElementCount(doc,xpath);
//      System.out.println(count);
//    } catch (Exception e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
//  }
//  /**
//   * 测试方法: getAttributeValues(String docPath,String xpath)
//   *
//   * @param docPath
//   * @param xpath
//   */
//  private static void testGetAttributeValues(String docPath, String xpath) {
//    try {
//      Document doc = XmlUtils.getXmlFileDocument(docPath);
//      Hashtable ht = XmlUtils.getAttributeValues(doc, xpath,1);
//      for (Iterator iter = ht.keySet().iterator(); iter.hasNext();) {
//        String key = (String)iter.next();
//        System.out.println(key+"="+ht.get(key));
//      }
//    } catch (Exception e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
//  }
//
//  private static void testGetElementText(String docPath, String xpath,int index){
//    try {
//      Document doc = XmlUtils.getXmlFileDocument(docPath);
//      String elemText = XmlUtils.getElementText(doc,xpath,index);
//      System.out.println(elemText);
//    } catch (Exception e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
//  }
//
//  private static void testGetElementText(Element parentElement, String xpath,
//      int index){
//    try {
//      String elemText = XmlUtils.getElementText(parentElement,xpath,index);
//      System.out.println(elemText);
//    } catch (Exception e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
//  }
//
//  private static void testGetElements(String docPath, String xpath){
//    try {
//      Document doc = XmlUtils.getXmlFileDocument(docPath);
//      List list = XmlUtils.getElements(doc,xpath);
//      System.out.println(((Element)list.get(0)).getAttributeValue("id"));
//    } catch (Exception e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
//  }
//
//  private static void testSetAttributeValues(String docPath, String xpath){
//    try {
//      Document doc = XmlUtils.getXmlFileDocument(docPath);
//      Hashtable attrs = new Hashtable();
//      attrs.put("name","aaa");
//      attrs.put("id","111");
//      attrs.put("nonode","wwww");
//      XmlUtils.setAttributeValues(doc,xpath,attrs,0);
//      XmlUtils.writeXmlFile(doc,docPath);
//    } catch (Exception e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
//
//  }
//  private static void testSetElementText(String docPath, String xpath){
//    try {
//      Document doc = XmlUtils.getXmlFileDocument(docPath);
//      XmlUtils.setElementText(doc,xpath,"Hello beijing 2008!!!",0);
//      XmlUtils.writeXmlFile(doc,docPath);
//    } catch (Exception e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
//  }
}
