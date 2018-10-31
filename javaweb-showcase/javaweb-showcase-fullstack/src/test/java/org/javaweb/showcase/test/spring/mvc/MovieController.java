/**
 * $Id: MovieController.java 80 2017-01-20 04:21:11Z job2wd $
 * 
 * @mail job2wd@gmail.com
 */
package org.javaweb.showcase.test.spring.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author David
 * @version 1.0
 * @date 2012-4-26 下午01:32:12
 */
@Controller
@RequestMapping("/movie")
public class MovieController {

  @RequestMapping(value = "/{name}", method = RequestMethod.GET)
  public String getMovie(@PathVariable String name, ModelMap model) {

    model.addAttribute("movie", name);
    return "list";

  }

}
