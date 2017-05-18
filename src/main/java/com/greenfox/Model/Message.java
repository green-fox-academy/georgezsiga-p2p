package com.greenfox.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Created by georgezsiga on 5/18/17.
 */
@Entity
@NoArgsConstructor
public class Message {

  @Id
  long id;
  String username;
  String text;
  Timestamp timestamp;


  public Message(String message, String felhasznalo) {
    this.id = (int) (Math.random() * 8999999 + 1000000);
    this.username = felhasznalo;
    this.text = message;
    this.timestamp = new Timestamp(System.currentTimeMillis());
  }

  public Message(long id, String username, String text, Timestamp timestamp) {
    this.id = id;
    this.username = username;
    this.text = text;
    this.timestamp = timestamp;
  }

  public Timestamp getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Timestamp timestamp) {
    this.timestamp = timestamp;
  }

  public long getId() {
    return id;
  }

  public void generateNewId() {
    this.id = (int) (Math.random() * 8999999 + 1000000);
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public void setId(long id) {
    this.id = id;
  }
}
