package com.greenfox.Model;

/**
 * Created by georgezsiga on 5/18/17.
 */
public enum Error {

  NOUSERNAME("nousername", "The username field is empty"),
  SESSIONTIMEDOUT("sessiontimedout", "Your session timed out, please log in again!"),
  NOMESSAGE("nomessage" , "You need to type in something to send");

  private final String name;
  private final String message;

  Error(String name, String message) {
    this.name = name;
    this.message = message;
  }

  public String getName() {
    return name;
  }

  public String getMessage() {
    return message;
  }
}
