/**
 * $Id: SimpleXMLStore.java 80 2017-01-20 04:21:11Z job2wd $
 */
package org.javaweb.showcase.test.xstream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * @author Wang WD
 * @version $Revision: 80 $ $Date: 2017-01-20 12:21:11 +0800 (星期五, 20 一月 2017) $
 */
public class SimpleXMLStore {

  private File           file;
  private SimpleJavaBean bean;

  public SimpleXMLStore(String file) {
    this(new File(file));
  }

  public SimpleXMLStore(File file) {
    this.file = file;
  }

  public SimpleXMLStore(String file, SimpleJavaBean bean) {
    this(new File(file), bean);
  }

  public SimpleXMLStore(File file, SimpleJavaBean bean) {
    this.file = file;
    this.bean = bean;
    //load();
  }

  private XStream getXStream() {
    XStream xstream = new XStream(new DomDriver("UTF-8"));
    xstream.alias("root", SimpleJavaBean.class);
    xstream.registerConverter(new SimpleConverter());
    return xstream;
  }

  public void store() throws Exception {
    if (!file.getParentFile().exists()) {
      file.getParentFile().mkdirs();
    }
    if (!file.exists()) {
      File.createTempFile("simpleBeanXStream", ".xml", file);
    }
    XStream xstream = getXStream();
    xstream.toXML(bean, new FileOutputStream(file));
  }

  public void load() throws Exception {
    XStream xstream = getXStream();
    bean = (SimpleJavaBean) xstream.fromXML(new FileInputStream(file));
  }

  /**
   * @return the file
   */
  public File getFile() {
    return file;
  }

  /**
   * @param file
   *          the file to set
   */
  public void setFile(File file) {
    this.file = file;
  }

  /**
   * @return the bean
   */
  public SimpleJavaBean getBean() {
    return bean;
  }

  /**
   * @param bean
   *          the bean to set
   */
  public void setBean(SimpleJavaBean bean) {
    this.bean = bean;
  }

}
