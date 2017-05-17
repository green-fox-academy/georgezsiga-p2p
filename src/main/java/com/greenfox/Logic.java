package com.greenfox;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by georgezsiga on 5/17/17.
 */
public class Logic {

  public String getLogMessage(String path) {

    return LocalDate.now().toString() + " " + LocalTime.now().toString() + " INFO Request " + path;
  }

}
