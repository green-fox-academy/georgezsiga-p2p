package com.greenfox.Model;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.NoArgsConstructor;

/**
 * Created by georgezsiga on 5/17/17.
 */
@Entity
@NoArgsConstructor
public class Felhasznalo {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  long id;

  String username;
  Timestamp lastActive;

  public Felhasznalo(String username) {
    this.username = username;
    this.lastActive = new Timestamp(System.currentTimeMillis());
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Timestamp getLastActive() {
    return lastActive;
  }

  public void setLastActive() {
    this.lastActive = new Timestamp(System.currentTimeMillis());
  }

  public long getId() {
    return id;
  }
}
