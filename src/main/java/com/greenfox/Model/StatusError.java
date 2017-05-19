package com.greenfox.Model;

/**
 * Created by georgezsiga on 5/18/17.
 */
public class StatusError implements Status {

  String status;
  String message;

  public StatusError(String message) {
    this.status = "error";
    this.message = message;
  }

  public StatusError() {
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
