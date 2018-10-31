/*
 * $Id: Docx4jHelper.java 137 2017-02-06 06:17:06Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-showcase/src/main/java/org/javaweb/showcase/util/Docx4jHelper.java $
 * Copyright (c) 2014 Company, guoyatech. All Rights Reserved.
 */
package org.javaweb.showcase.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.docx4j.XmlUtils;
import org.docx4j.dml.wordprocessingDrawing.Inline;
import org.docx4j.jaxb.Context;
import org.docx4j.model.structure.SectionWrapper;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.Part;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
import org.docx4j.openpackaging.parts.WordprocessingML.FooterPart;
import org.docx4j.openpackaging.parts.WordprocessingML.HeaderPart;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.relationships.Relationship;
import org.docx4j.wml.ContentAccessor;
import org.docx4j.wml.FooterReference;
import org.docx4j.wml.Ftr;
import org.docx4j.wml.Hdr;
import org.docx4j.wml.HdrFtrRef;
import org.docx4j.wml.HeaderReference;
import org.docx4j.wml.P;
import org.docx4j.wml.SectPr;
import org.docx4j.wml.Tbl;
import org.docx4j.wml.Text;
import org.docx4j.wml.Tr;

/**
 * 
 * @author wangwd
 * @version $Revision: 137 $, $Date: 2016-10-11 下午2:53:35$
 * @LastChanged $Author: job2wd $, $Date:: #$
 */
public class Docx4jHelper {

  private org.docx4j.wml.ObjectFactory objectFactory;

  private WordprocessingMLPackage      wordPackage;
  private MainDocumentPart             mainDocumentPart;

  public Docx4jHelper() {
    try {
      wordPackage = WordprocessingMLPackage.createPackage();
      initWordPackage();
    } catch (InvalidFormatException e) {
      throw new RuntimeException(e);
    }
  }

  public Docx4jHelper(String docTemplateFile) {
    try {
      //URL url = this.getClass().getResource("/web/comm/wordPackage/rpt-wordPackage.docx");
      //String docxPath = url.getPath();
      wordPackage = WordprocessingMLPackage.load(new FileInputStream(new File(docTemplateFile)));
      initWordPackage();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private void initWordPackage() {
    mainDocumentPart = wordPackage.getMainDocumentPart();
    objectFactory = new org.docx4j.wml.ObjectFactory();

    Relationship styleRel = mainDocumentPart.getStyleDefinitionsPart().getSourceRelationships().get(0);
    mainDocumentPart.getRelationshipsPart().removeRelationship(styleRel);
  }

  public void writeDocxToStream(String targetFile) throws IOException, Docx4JException {
    File f = new File(targetFile);
    wordPackage.save(f);
  }

  public void replacePlaceholder(String name, String placeholder) {
    List<Object> texts = getAllElementFromObject(mainDocumentPart, Text.class);

    for (Object text : texts) {
      Text textElement = (Text) text;
      if (textElement.getValue().equals(placeholder)) {
        textElement.setValue(name);
      }
    }
  }

  public void replaceParagraph(String placeholder, String textToAdd) {
    // 1. get the paragraph
    List<Object> paragraphs = getAllElementFromObject(mainDocumentPart, P.class);

    P toReplace = null;
    for (Object p : paragraphs) {
      List<Object> texts = getAllElementFromObject(p, Text.class);

      for (Object t : texts) {
        Text content = (Text) t;
        if (content.getValue().equals(placeholder)) {
          toReplace = (P) p;
          break;
        }
      }
    }

    // we now have the paragraph that contains our placeholder: toReplace
    // 2. split into seperate lines
    String as[] = StringUtils.splitPreserveAllTokens(textToAdd, '\n');

    for (int i = 0; i < as.length; i++) {
      String ptext = as[i];

      // 3. copy the found paragraph to keep styling correct
      P copy = XmlUtils.deepCopy(toReplace);

      // replace the text elements from the copy
      List<?> texts = getAllElementFromObject(copy, Text.class);
      if (texts.size() > 0) {
        Text textToReplace = (Text) texts.get(0);
        textToReplace.setValue(ptext);
      }

      // add the paragraph to the document
      mainDocumentPart.getContent().add(copy);
    }

    // 4. remove the original one
    ((ContentAccessor) toReplace.getParent()).getContent().remove(toReplace);

  }

  public void fillTable(String[] placeholders, List<Map<String, String>> textToAdd) throws Docx4JException, JAXBException {
    List<Object> tables = getAllElementFromObject(mainDocumentPart, Tbl.class);

    // 1. find the table
    Tbl tempTable = getTemplateTable(tables, placeholders[0]);
    List<Object> rows = getAllElementFromObject(tempTable, Tr.class);

    // first row is header, second row is content
    if (rows.size() == 2) {
      // this is our wordPackage row
      Tr templateRow = (Tr) rows.get(1);

      for (Map<String, String> replacements : textToAdd) {
        // 2 and 3 are done in this method
        addRowToTable(tempTable, templateRow, replacements);
      }

      // 4. remove the wordPackage row
      tempTable.getContent().remove(templateRow);
    }
  }

  public void insertHeaderImage(String imageName) throws Exception {
    byte[] image = IOUtils.toByteArray(new FileInputStream(new File(imageName)));
    this.insertHeaderImage(image);
  }

  public void insertHeaderImage(byte[] image) throws Exception {
    this.createHeaderReference(this.createHeaderPart(image));
  }

  public void insertFooterImage(String imageName) throws Exception {
    byte[] image = IOUtils.toByteArray(new FileInputStream(new File(imageName)));
    this.insertFooterImage(image);
  }

  public void insertFooterImage(byte[] image) throws Exception {
    this.createFooterReference(this.createFooterPart(image));
  }

  public void insertImage(byte[] bytes, String filenameHint, String altText, int id1, int id2, Long cx) throws Exception {
    org.docx4j.wml.P p = this.newImage(null, bytes, filenameHint, altText, id1, id2, cx);
    mainDocumentPart.addObject(p);
  }

  private void createFooterReference(Relationship relationship) throws Exception {
    List<SectionWrapper> sections = wordPackage.getDocumentModel().getSections();
    SectPr sectPr = sections.get(sections.size() - 1).getSectPr();

    if (sectPr == null) {
      sectPr = objectFactory.createSectPr();
      mainDocumentPart.addObject(sectPr);
      sections.get(sections.size() - 1).setSectPr(sectPr);
    }

    FooterReference footerReference = objectFactory.createFooterReference();

    footerReference.setId(relationship.getId());
    footerReference.setType(HdrFtrRef.DEFAULT);

    sectPr.getEGHdrFtrReferences().add(footerReference);
  }

  private Relationship createFooterPart(byte[] image) throws Exception {
    FooterPart footerPart = new FooterPart();
    footerPart.setPackage(wordPackage);
    footerPart.setJaxbElement(getFtr(footerPart, image));

    return mainDocumentPart.addTargetPart(footerPart);
  }

  private Ftr getFtr(Part sourcePart, byte[] image) throws Exception {
    Ftr ftr = objectFactory.createFtr();
    ftr.getContent().add(this.newImage(sourcePart, image, "filename", "alttext", 1, 2, 500l));
    return ftr;
  }

  private List<Object> getAllElementFromObject(Object obj, Class<?> toSearch) {
    List<Object> result = new ArrayList<Object>();
    if (obj == null) {
      return result;
    }

    if (obj instanceof JAXBElement) {
      obj = ((JAXBElement<?>) obj).getValue();
    }

    if (obj.getClass().equals(toSearch)) {
      result.add(obj);
    } else if (obj instanceof ContentAccessor) {
      List<?> children = ((ContentAccessor) obj).getContent();
      for (Object child : children) {
        result.addAll(getAllElementFromObject(child, toSearch));
      }

    }
    return result;
  }

  private Tbl getTemplateTable(List<Object> tables, String templateKey) throws Docx4JException, JAXBException {
    for (Iterator<Object> iterator = tables.iterator(); iterator.hasNext();) {
      Object tbl = iterator.next();
      List<?> textElements = getAllElementFromObject(tbl, Text.class);

      for (Object text : textElements) {
        Text textElement = (Text) text;
        if (textElement.getValue() != null && textElement.getValue().equals(templateKey)) {
          return (Tbl) tbl;
        }
      }
    }
    return null;
  }

  private void addRowToTable(Tbl reviewtable, Tr templateRow, Map<String, String> replacements) {
    Tr workingRow = XmlUtils.deepCopy(templateRow);
    List<?> textElements = getAllElementFromObject(workingRow, Text.class);

    for (Object object : textElements) {
      Text text = (Text) object;
      String replacementValue = replacements.get(text.getValue());

      if (replacementValue != null) {
        text.setValue(replacementValue);
      }
    }

    reviewtable.getContent().add(workingRow);
  }

  private org.docx4j.wml.P newImage(Part sourcePart, byte[] bytes, String filenameHint, String altText, int id1, int id2, Long cx)
      throws Exception {
    BinaryPartAbstractImage imagePart = null;

    if (sourcePart != null) {
      imagePart = BinaryPartAbstractImage.createImagePart(wordPackage, sourcePart, bytes);
    } else {
      imagePart = BinaryPartAbstractImage.createImagePart(wordPackage, bytes);
    }

    Inline inline = null;

    if (cx == null) {
      inline = imagePart.createImageInline(filenameHint, altText, id1, id2, false);
    } else {
      inline = imagePart.createImageInline(filenameHint, altText, id1, id2, cx, false);
    }

    // Now add the inline in w:p/w:r/w:drawing
    org.docx4j.wml.ObjectFactory factory = Context.getWmlObjectFactory();
    org.docx4j.wml.P p = factory.createP();
    org.docx4j.wml.R run = factory.createR();

    p.getContent().add(run);

    org.docx4j.wml.Drawing drawing = factory.createDrawing();
    run.getContent().add(drawing);
    drawing.getAnchorOrInline().add(inline);

    return p;
  }

  private Relationship createHeaderPart(byte[] image) throws Exception {
    HeaderPart headerPart = new HeaderPart();
    Relationship rel = mainDocumentPart.addTargetPart(headerPart);

    // After addTargetPart, so image can be added properly
    headerPart.setJaxbElement(getHdr(headerPart, image));

    return rel;
  }

  private Hdr getHdr(Part sourcePart, byte[] image) throws Exception {
    Hdr hdr = objectFactory.createHdr();
    hdr.getContent().add(this.newImage(sourcePart, image, "filename", "alttext", 1, 2, 500l));

    return hdr;
  }

  private void createHeaderReference(Relationship relationship) throws InvalidFormatException {
    List<SectionWrapper> sections = wordPackage.getDocumentModel().getSections();

    SectPr sectPr = sections.get(sections.size() - 1).getSectPr();
    // There is always a section wrapper, but it might not contain a sectPr
    if (sectPr == null) {
      sectPr = objectFactory.createSectPr();
      mainDocumentPart.addObject(sectPr);
      sections.get(sections.size() - 1).setSectPr(sectPr);
    }

    HeaderReference headerReference = objectFactory.createHeaderReference();

    headerReference.setId(relationship.getId());
    headerReference.setType(HdrFtrRef.DEFAULT);

    sectPr.getEGHdrFtrReferences().add(headerReference);// add header or
    // footer references
  }

  public org.docx4j.wml.ObjectFactory getObjectFactory() {
    return objectFactory;
  }

  public WordprocessingMLPackage getWordPackage() {
    return wordPackage;
  }

  public MainDocumentPart getMainDocumentPart() {
    return mainDocumentPart;
  }

  //////////////////////////////////////testing///////////////////////////////////////
  public static void main(String[] args) throws Exception {
    //testDocx4j();
    testPrismRPT();
  }
  
  @SuppressWarnings("unchecked")
  private static void testDocx4j() throws Exception {
    Docx4jHelper helper = new Docx4jHelper("D:/Job/java office/docx4j/docx4j-test-template.docx");
    
    String placeholder = "SJ_EX1";
    String toAdd = "jos\ndirksen";
         
    helper.replaceParagraph(placeholder, toAdd);
    
    Map<String,String> repl1 = new HashMap<String, String>();
    repl1.put("SJ_FUNCTION", "function1");
    repl1.put("SJ_DESC", "desc1");
    repl1.put("SJ_PERIOD", "period1");
 
    Map<String,String> repl2 = new HashMap<String, String>();
    repl2.put("SJ_FUNCTION", "function2");
    repl2.put("SJ_DESC", "desc2");
    repl2.put("SJ_PERIOD", "period2");
 
    Map<String,String> repl3 = new HashMap<String, String>();
    repl3.put("SJ_FUNCTION", "function3");
    repl3.put("SJ_DESC", "desc3");
    repl3.put("SJ_PERIOD", "period3");
 
    helper.fillTable(new String[]{"SJ_FUNCTION","SJ_DESC","SJ_PERIOD"}, Arrays.asList(repl1,repl2,repl3));
    
    helper.insertHeaderImage("D:/prism.png");
    helper.insertFooterImage("D:/prism.png");
    
    helper.writeDocxToStream("D:/Job/java office/docx4j/docx4j-test-res.docx");
    
    System.out.println("OK");
  }
  
  private static void testPrismRPT() throws Exception {
    Docx4jHelper helper = new Docx4jHelper("D:/Job/java office/docx4j/prism-rpt-template.docx");
    
    helper.insertHeaderImage("D:/prism.png");
    helper.insertFooterImage("D:/prism.png");
    
    //helper.replacePlaceholder("北京东方棱镜科技有限公司监测报告", "RPT_TITLE");
    
    //helper.replaceParagraph("RPT_TITLE", "北京东方棱镜科技有限公司监测报告");
    helper.replaceParagraph("RPT_TITLE_SUB1", "日报告");
    //helper.replaceParagraph("RPT_GEN_DATE", "报告时间：2016年10月13日 凌晨");
    
    //helper.replacePlaceholder(wordPackage, name, "RPT_TITLE");
    
    helper.writeDocxToStream("D:/Job/java office/docx4j/棱镜业务互联流量监测系统网络安全监测报告 2016-09-11.docx");
    
    System.out.println("OK");
  }
  
}
