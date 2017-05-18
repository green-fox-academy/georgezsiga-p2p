package com.greenfox;

import com.greenfox.Model.Felhasznalo;
import com.greenfox.Model.Message;
import com.greenfox.Repository.MessageRepository;
import com.greenfox.Repository.UserRepository;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by georgezsiga on 5/17/17.
 */
public class Logic {


  public String getLogMessage(String path) {

    return LocalDate.now().toString() + " " + LocalTime.now().toString() + " INFO Request " + path;
  }

  public Boolean userTimeout(UserRepository userRepository) {
    Felhasznalo felhasznalo = userRepository.findFirstByOrderByLastActiveDesc();
    System.out.println(felhasznalo);
    Long mytime = felhasznalo.getLastActive().getTime();
    Long timelimit = new Timestamp(System.currentTimeMillis() - 550000).getTime();
    if (mytime > timelimit) {
      return false;
    } else {
      return true;
    }
  }

  public Boolean checkId(MessageRepository messageRepository, long id) {
    if (messageRepository.findOne(id) == null) {
      return false;
    } else {
      return true;
    }
  }

  public void updateLastActive(UserRepository userRepository, long id) {
    userRepository.findFelhasznaloById(id).setLastActive();
    userRepository.save(userRepository.findFelhasznaloById(id));
  }

  public void updateLastActive(UserRepository userRepository, String username) {
    userRepository.findFelhasznaloByUsername(username).setLastActive();
    userRepository.save(userRepository.findByUsername(username));
  }

  public String checkAllFields(Message message) {
    if (message.getId() != 0 && message.getText() != null && message.getTimestamp() != null && message.getUsername() != null) {
      return "ok";
    }
    System.out.println("in");
    String errorString = "Missing field(s): ";
    if (message.getId() == 0) {
      System.out.println("1");
      errorString += " id;";
    } else if (message.getText() == null) {
      System.out.println("2");
      errorString += " text;";
    } else if (message.getTimestamp() == null) {
      System.out.println("3");
      errorString += " timestamp;";
    } else if (message.getUsername() == null) {
      System.out.println("4");
      errorString += " username;";
    }
    System.out.println("6");
    return errorString;
  }

}
