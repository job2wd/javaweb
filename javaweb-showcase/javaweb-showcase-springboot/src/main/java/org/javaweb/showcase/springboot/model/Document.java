/*
 * $Id:$
 * $HeadURL:$
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.showcase.springboot.model;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

/**
 * 
 * @author wangwd
 * @version $Revision:$, $Date: 2018年10月25日 上午9:31:46$
 * @LastChanged $Author:$, $Date::                    #$
 */
@SolrDocument(collection = "SpringBootDocumentExample")
public class Document {
  
  @Id
  @Field
  private String id;
  @Field
  private String docType;
  
  @Field
  private String docTitle;
  
  public Document() {
  }
  
  public Document(String id, String docType, String docTitle) {
    this.id = id;
    this.docTitle = docTitle;
    this.docType = docType;
  }
  
  public void setId(String id) {
    this.id = id;
  }
  
  public String getId() {
    return this.id;
  }
  
  @Override
  public String toString() {
    return "Document{" + "id='" + id + '\'' + ", docType='" + docType + '\'' + ", docTitle='" + docTitle + '\'' + '}';
  }
  
  public String getDocType() {
    return docType;
  }
  
  public void setDocType(String docType) {
    this.docType = docType;
  }
  
  public String getDocTitle() {
    return docTitle;
  }
  
  public void setDocTitle(String docTitle) {
    this.docTitle = docTitle;
  }
}
