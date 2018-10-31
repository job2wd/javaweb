/**
 * $Id: CustomerMultiActionAnnotationController.java 80 2017-01-20 04:21:11Z job2wd $
 * 
 * @mail job2wd@gmail.com
 */
package org.javaweb.showcase.test.spring.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author David
 * @version 1.0
 * @date 2012-4-26 下午02:56:27
 */
@Controller
public class CustomerMultiActionAnnotationController {

  @RequestMapping("/customer/add")
  public ModelAndView add(HttpServletRequest request, HttpServletResponse response) throws Exception {

    return new ModelAndView("customerPage", "msg", "add() method in CustomerMultiActionAnnotationController.");

  }

  @RequestMapping("/customer/delete")
  public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws Exception {

    return new ModelAndView("customerPage", "msg", "delete() method in CustomerMultiActionAnnotationController.");

  }

  @RequestMapping("/customer/update.htm")
  public ModelAndView update(HttpServletRequest request, HttpServletResponse response) throws Exception {

    return new ModelAndView("customerPage", "msg", "update() method in CustomerMultiActionAnnotationController.");

  }

  @RequestMapping("/customer/list.htm")
  public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws Exception {

    return new ModelAndView("customerPage", "msg", "list() method in CustomerMultiActionAnnotationController.");

  }

}
