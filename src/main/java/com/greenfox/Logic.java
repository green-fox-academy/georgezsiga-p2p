package com.greenfox;

import com.greenfox.Model.Client;
import com.greenfox.Model.Felhasznalo;
import com.greenfox.Model.IncomingMessage;
import com.greenfox.Model.Message;
import com.greenfox.Model.StatusOk;
import com.greenfox.Repository.MessageRepository;
import com.greenfox.Repository.UserRepository;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by georgezsiga on 5/17/17.
 */
public class Logic {


  public String getLogMessage(String path
//      , String method, String requestData
  ) {

    return LocalDate.now().toString() + " " + LocalTime.now().toString() + " INFO Request " + path
//        + " " + method + " " + requestData
        ;
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

  public String checkAllFields(IncomingMessage incomingMessage) {
    Message message = incomingMessage.getMessage();
    Client client = incomingMessage.getClient();
    if (message.getId() != 0 && message.getText() != null && message.getTimestamp() != null && message.getUsername() != null && client.getId() != null) {
      return "ok";
    }
    String errorString = "Missing field(s): ";
    if (message.getId() == 0) {
      errorString += " id;";
    } else if (message.getText() == null) {
      errorString += " text;";
    } else if (message.getTimestamp() == null) {
      errorString += " timestamp;";
    } else if (message.getUsername() == null) {
      errorString += " username;";
    } else if (client.getId() == null) {
      errorString += " client.id;";
    }
    return errorString;
  }

  public List<Message> findDistinctUsernamesFromMessages(MessageRepository messageRepository) {
    List<Message> finallist = new ArrayList<>();
    List<String> names = new ArrayList<>();
    for (Message m : messageRepository.findAllByOrderByTimestampDesc()) {
      if (names.contains(m.getUsername())) {

      } else {
        names.add(m.getUsername());
        if (finallist.size() <= 25) {
        finallist.add(m);
        }
      }
    }
    return finallist;
  }


}
