package com.greenfox.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.NoArgsConstructor;

/**
 * Created by georgezsiga on 5/18/17.
 */
@Entity
public class StatusOk extends Status {

  @Id
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
