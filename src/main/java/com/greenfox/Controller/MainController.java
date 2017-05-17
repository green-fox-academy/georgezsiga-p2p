package com.greenfox.Controller;

import com.greenfox.Logic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by georgezsiga on 5/17/17.
 */
@Controller
public class MainController {

  Logic logic = new Logic();

//  @ExceptionHandler(Exception.class)
//  public String handleAllExceptions() {
//    return "Error happend";
//  }

  @RequestMapping("/")
  public String home() {
    System.out.println(logic.getLogMessage("/"));
    return "index";
  }
}
