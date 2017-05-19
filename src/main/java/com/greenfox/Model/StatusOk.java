package com.greenfox.Model;


/**
 * Created by georgezsiga on 5/18/17.
 */

public class StatusOk implements Status {


  String status;

  public StatusOk() {
    this.status = "ok";
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
