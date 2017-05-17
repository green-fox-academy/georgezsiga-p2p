package com.greenfox.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by georgezsiga on 5/17/17.
 */
@Controller
public class MainController {

  @RequestMapping("/")
  public String home() {
    return "index";
  }
}
