package com.greenfox.Model;

import com.greenfox.Repository.MessageRepository;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.NoArgsConstructor;

/**
 * Created by georgezsiga on 5/18/17.
 */
@Entity
@NoArgsConstructor
public class Message {

  @Id
  long id;

  String message;
  String felhasznalo;
  Timestamp created;

  public Message(String message, String felhasznalo) {
    this.id = (int) (Math.random() * 8999999 + 1000000);
    this.message = message;
    this.felhasznalo = felhasznalo;
    this.created = new Timestamp(System.currentTimeMillis());
  }

  public Timestamp getCreated() {
    return created;
  }

  public void setCreated(Timestamp created) {
    this.created = created;
  }

  public long getId() {
    return id;
  }

  public void generateNewId() {
    this.id = (int) (Math.random() * 8999999 + 1000000);
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getFelhasznalo() {
    return felhasznalo;
  }

  public void setFelhasznalo(String felhasznalo) {
    this.felhasznalo = felhasznalo;
  }
}
