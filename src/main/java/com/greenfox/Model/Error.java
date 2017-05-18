package com.greenfox.Model;

/**
 * Created by georgezsiga on 5/18/17.
 */
public enum Error {

  NOUSERNAME("nousername", "The username field is empty");

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
