/**
 * $Id: SimpleConverter.java 80 2017-01-20 04:21:11Z job2wd $
 */
package org.javaweb.showcase.test.xstream;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 * @author David
 * @version $Revision: 80 $ $Date: 2017-01-20 12:21:11 +0800 (星期五, 20 一月 2017) $
 */
public class SimpleConverter implements Converter {

  /**
   * 
   */
  public SimpleConverter() {
    super();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.thoughtworks.xstream.converters.Converter#marshal(java.lang.Object,
   * com.thoughtworks.xstream.io.HierarchicalStreamWriter,
   * com.thoughtworks.xstream.converters.MarshallingContext)
   */
  @Override
  public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
    SimpleJavaBean bean = (SimpleJavaBean) source;
    writer.startNode("node");
    setAttributeValues(bean, writer);

    createNodes(bean, writer);
  }

  private void createNodes(SimpleJavaBean bean, HierarchicalStreamWriter writer) {
    if (bean == null) {
      return;
    }
    for (SimpleJavaBean child : bean.getChildren()) {
      writer.startNode("node");
      setAttributeValues(child, writer);
      createNodes(child, writer);
    }
    writer.endNode();
  }

  private void setAttributeValues(SimpleJavaBean bean, HierarchicalStreamWriter writer) {
    writer.addAttribute("id", bean.getId());
    writer.addAttribute("name", bean.getName());
    if (bean.getParentNode() != null && !bean.getParentNode().equals("")) {
      writer.addAttribute("parentNode", bean.getParentNode());
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.thoughtworks.xstream.converters.Converter#unmarshal(com.thoughtworks
   * .xstream.io.HierarchicalStreamReader,
   * com.thoughtworks.xstream.converters.UnmarshallingContext)
   */
  @Override
  public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
    //reader.moveDown();
    //reader.moveUp();
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.thoughtworks.xstream.converters.ConverterMatcher#canConvert(java.lang
   * .Class)
   */
  @Override
  public boolean canConvert(Class type) {
    return type.equals(SimpleJavaBean.class);
  }

}
