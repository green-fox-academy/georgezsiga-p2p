package com.greenfox.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by georgezsiga on 5/17/17.
 */
@Controller
public class MainController {

  @ExceptionHandler(Exception.class)
  public String handleAllExceptions() {
    return "Error happend";
  }

  @RequestMapping("/")
  public String home() {
    System.out.println(LocalDate.now().toString() + " " + LocalTime.now().toString() + " INFO" + " Request " + "/index");
    return "index";
  }
}
