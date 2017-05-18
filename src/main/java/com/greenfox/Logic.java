package com.greenfox;

import com.greenfox.Model.Felhasznalo;
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
}
