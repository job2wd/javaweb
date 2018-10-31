/*
 * $Id:$
 * $HeadURL:$
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.showcase.springboot.repository;

import java.util.List;

import org.javaweb.showcase.springboot.model.Document;
import org.springframework.data.solr.repository.SolrCrudRepository;

/**
 * 
 * @author wangwd
 * @version $Revision:$, $Date: 2018年10月25日 上午9:31:11$
 * @LastChanged $Author:$, $Date::                    #$
 */
public interface DocumentRepository extends SolrCrudRepository<Document, String> {
  
  List<Document> findByDocTitleEndsWith(String title); // find documents whose docTitle ends with specified string
  
  List<Document> findByDocTitleStartsWith(String title); // find documents whose docTitle starts with specified string
  
  List<Document> findByDocTypeEndsWith(String type); //find documents whose docType ends with specified string
  
  List<Document> findByDocTypeStartsWith(String type);//find documents whose docType start with specified string
}
