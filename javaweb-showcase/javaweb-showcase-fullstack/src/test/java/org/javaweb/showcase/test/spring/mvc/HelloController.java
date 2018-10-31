/**
 * $Id: HelloController.java 80 2017-01-20 04:21:11Z job2wd $
 * 
 * @mail job2wd@gmail.com
 */
package org.javaweb.showcase.test.spring.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author David
 * @version 1.0
 * @date 2012-4-26 上午11:16:09
 */
@Controller
@RequestMapping("/welcome")
public class HelloController {

  @RequestMapping(method = RequestMethod.GET)
  public String printWelcome(ModelMap model) {

    model.addAttribute("message", "Spring 3 MVC Hello World");
    return "hello";

  }

}
