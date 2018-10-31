/*
 * $Id: UTF8StringHttpMessageConverter.java 135 2017-02-06 06:15:11Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-showcase/src/main/java/org/javaweb/showcase/common/UTF8StringHttpMessageConverter.java $
 * Copyright (c) 2014 Company, guoyatech. All Rights Reserved.
 */
package org.javaweb.web.springmvc;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.FileCopyUtils;


/**
 * 
 * @author wangwd
 * @version $Revision: 135 $, $Date: 2015-8-20 下午4:39:43$
 * @LastChanged $Author: job2wd $, $Date:: 2017-02-06 14:15:11#$
 */
public class UTF8StringHttpMessageConverter extends AbstractHttpMessageConverter<String> {

  public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
  private final List<Charset> availableCharsets;

  public UTF8StringHttpMessageConverter() {
    this(DEFAULT_CHARSET);
  }

  public UTF8StringHttpMessageConverter(Charset defaultCharset) {
    super(new MediaType("text", "plain", defaultCharset), MediaType.ALL);
    this.availableCharsets = new ArrayList<Charset>(Charset.availableCharsets().values());
  }

  @Override
  protected boolean supports(Class<?> clazz) {
    return String.class.equals(clazz);
  }

  @Override
  protected String readInternal(Class<? extends String> clazz, HttpInputMessage inputMessage) throws IOException,
      HttpMessageNotReadableException {
    MediaType contentType = inputMessage.getHeaders().getContentType();
    Charset charset = contentType.getCharset() != null ? contentType.getCharset() : DEFAULT_CHARSET;
    return FileCopyUtils.copyToString(new InputStreamReader(inputMessage.getBody(), charset));
  }

  @Override
  protected void writeInternal(String t, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
    MediaType contentType = outputMessage.getHeaders().getContentType();
    Charset charset = contentType.getCharset() != null ? contentType.getCharset() : DEFAULT_CHARSET;
    FileCopyUtils.copy(t, new OutputStreamWriter(outputMessage.getBody(), charset));
  }

  protected List<Charset> getAcceptedCharsets() {
    return this.availableCharsets;
  }

  @Override
  protected Long getContentLength(String s, MediaType contentType) {
    if (contentType != null && contentType.getCharset() != null) {
      Charset charset = contentType.getCharset();
      try {
        return (long) s.getBytes(charset.name()).length;
      } catch (UnsupportedEncodingException ex) {
        throw new InternalError(ex.getMessage());
      }
    } else {
      return null;
    }
  }
  
}
