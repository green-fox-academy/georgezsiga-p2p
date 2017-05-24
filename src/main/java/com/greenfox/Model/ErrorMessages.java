package com.greenfox.Model;

/**
 * Created by georgezsiga on 5/18/17.
 */
public enum ErrorMessages {

  NOUSERNAME("nousername", "The username field is empty"),
  USERNAMEALREADYTAKEN("usernamealreadytaken", "Username is taken, choose an other one"),
  SESSIONTIMEDOUT("sessiontimedout", "Your session timed out, please log in again!"),
  NOMESSAGE("nomessage" , "You need to type in something to send");

  private final String name;
  private final String message;

  ErrorMessages(String name, String message) {
    this.name = name;
    this.message = message;
  }

  public String getName() {
    return name;
  }

  public String getMessage() {
    return message;
  }

  @Override
  public String toString() {
    return message;
  }
}
