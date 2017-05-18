package com.greenfox.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.NoArgsConstructor;

/**
 * Created by georgezsiga on 5/18/17.
 */
@Entity
@NoArgsConstructor
public class StatusError extends Status {

  @Id
  String status;
  String message;

  public StatusError(String message) {
    this.status = "error";
    this.message = message;
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
