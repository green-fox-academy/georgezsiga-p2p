package com.greenfox.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.NoArgsConstructor;

/**
 * Created by georgezsiga on 5/18/17.
 */

public class Client {


  String id;

  public Client() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
