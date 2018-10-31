package org.javaweb.showcase.test.chaos;

import org.xml.sax.helpers.DefaultHandler;

public class MultipartDSignXMLHandler extends DefaultHandler {
  // private DSignBody dSignBody;
  // private Map<String , DSign> map = new HashMap<String, DSign>();
  // private StringBuffer target = new StringBuffer(20);
  // public DSignBody getDSignBody() {
  // return dSignBody;
  // }
  // private String currentID ;
  // private DeferredFileOutputStream currentFos;
  // private File repository;
  // protected final String TARGET_DSIGN_RESPONSE = "/DSignContext/Response";
  // protected final String TARGET_DSIGN_RESPONSE_RESULT =
  // "/DSignContext/Response/doDSignResult";
  // protected final String TARGET_DSIGN_RESPONSE_DATA =
  // "/DSignContext/Response/DSignData";
  // protected final String ATTRIBUTE_RESPONSE_SVCID = "svcid";
  // protected final String ATTRIBUTE_RESPONSE_ID = "id";
  // protected final String ATTRIBUTE_RESULT_STATUS = "status";
  // protected final String OPTION_TYPE = "doDSign";
  // public MultipartDSignXMLHandler(){
  //
  // }
  // @Override
  // public void startDocument() throws SAXException {
  // super.startDocument();
  // dSignBody = new DSignBody();
  // // System.out.println("START");
  // }
  // @Override
  // public void startElement(String uri, String localName, String name,
  // Attributes attributes) throws SAXException {
  // this.appendTarget(name);
  // // System.out.println("BEGIN ELEMENT : " + this.target);
  // if(this.TARGET_DSIGN_RESPONSE.equals(this.target.toString())){
  // String type = attributes.getValue(this.ATTRIBUTE_RESPONSE_SVCID);
  // if(OPTION_TYPE.equals(type)){
  // DSign dSign = new DSign();
  // String id = attributes.getValue(this.ATTRIBUTE_RESPONSE_ID);
  // map.put(id, dSign);
  // this.currentID = id;
  // }
  // }
  // if(this.TARGET_DSIGN_RESPONSE_RESULT.equals(this.target.toString())){
  // String status = attributes.getValue(this.ATTRIBUTE_RESULT_STATUS);
  // DSign dSign = map.get(this.currentID);
  // dSign.setStatus(Boolean.getBoolean(status));
  // }
  // if(this.TARGET_DSIGN_RESPONSE_DATA.equals(this.target.toString())){
  // currentFos = new
  // DeferredFileOutputStream(Constants.BUFFER_MAX_SIZE,this.getTempFile());
  // }
  // }
  //
  // @Override
  // public void characters(char[] ch, int start, int length) throws
  // SAXException {
  // if(this.TARGET_DSIGN_RESPONSE_DATA.equals(this.target.toString())){
  // if(ch != null && ch.length >0){
  // Reader input = null;
  // try {
  // input = new CharArrayReader(ch,start,length);
  // IOUtils.copy(input, currentFos);
  // } catch (IOException e) {
  // IOUtils.closeQuietly(currentFos);
  // e.printStackTrace();
  // throw new SAXException(e);
  // }finally{
  // IOUtils.closeQuietly(input);
  // }
  // }
  // }
  // }
  // @Override
  // public void endElement(String uri, String localName, String name) throws
  // SAXException {
  // // System.out.println("END ELEMENT : " + this.target);
  // if(this.TARGET_DSIGN_RESPONSE_DATA.equals(this.target.toString())){
  // DSign dsign = map.get(this.currentID);
  // dsign.setFos(currentFos);
  // IOUtils.closeQuietly(currentFos);
  // }
  // this.deleteTarget(name);
  // }
  // @Override
  // public void endDocument() throws SAXException {
  // dSignBody.setDSignMap(map);
  // }
  //
  // protected void appendTarget(String elementName){
  // target.append("/").append(elementName);
  // }
  // protected void deleteTarget(String elementName){
  // target.delete(target.lastIndexOf(elementName)-1>0?target.lastIndexOf(elementName)-1:0,
  // target.length());
  // }
  // protected File getTempFile() {
  // File tempDir = repository;
  // if (tempDir == null) {
  // tempDir = new File(System.getProperty("java.io.tmpdir"));
  // }
  //
  // String tempFileName = UUID.randomUUID().toString().replace('-', '_') +
  // ".tmp";
  // // System.out.println(tempDir);
  // File f = new File(tempDir, tempFileName);
  // return f;
  // }
  // public static void main(String[] args) {
  // MultipartDSignXMLHandler handler = new MultipartDSignXMLHandler();
  // handler.appendTarget("a");
  // handler.appendTarget("b");
  // // System.out.println(handler.target);
  // handler.deleteTarget("b");
  // // System.out.println(handler.target);
  // String s = "a";
  // byte[] bs = s.getBytes();
  // for(byte b:bs){
  // // System.out.println(b);
  // }
  //
  // // System.out.println(Character.getDirectionality('a'));
  // }
}
