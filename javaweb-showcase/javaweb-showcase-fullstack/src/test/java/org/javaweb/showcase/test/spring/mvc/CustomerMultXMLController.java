/**
 * $Id: CustomerMultXMLController.java 80 2017-01-20 04:21:11Z job2wd $
 * 
 * @mail job2wd@gmail.com
 */
package org.javaweb.showcase.test.spring.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * @author David
 * @version 1.0
 * @date 2012-4-26 下午02:24:01
 */
public class CustomerMultXMLController extends MultiActionController {

  public ModelAndView testaddCustomer(HttpServletRequest request, HttpServletResponse response) throws Exception {

    return new ModelAndView("customerPage", "msg", "add() method");

  }

  public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws Exception {

    return new ModelAndView("customerPage", "msg", "delete() method");

  }

  public ModelAndView update(HttpServletRequest request, HttpServletResponse response) throws Exception {

    return new ModelAndView("customerPage", "msg", "update() method");

  }

  public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws Exception {

    return new ModelAndView("customerPage", "msg", "list() method");

  }
}
