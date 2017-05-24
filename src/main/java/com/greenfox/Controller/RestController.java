package com.greenfox.Controller;

import com.greenfox.Logic;
import com.greenfox.Model.Client;
import com.greenfox.Model.IncomingMessage;
import com.greenfox.Model.Message;
import com.greenfox.Model.Status;
import com.greenfox.Model.StatusError;
import com.greenfox.Model.StatusOk;
import com.greenfox.Repository.MessageRepository;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.sql.Timestamp;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;

/**
 * Created by georgezsiga on 5/18/17.
 */
@org.springframework.web.bind.annotation.RestController()
@CrossOrigin("*")
public class RestController {

  @Autowired
  MessageRepository messageRepository;

  Logic logic = new Logic();
  String marci = "https://p2p-chat-seed0forever.herokuapp.com/api/message/receive";
  String viktor = "https://chat-p2p.herokuapp.com/api/message/receive";
  String zsolt = "https://p2p-by-nagyza.herokuapp.com/api/message/receive";
  RestTemplate restTemplate = new RestTemplate();

  StatusOk statusOk;
  StatusError statusError;

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({Exception.class, SecurityException.class})
  public String handleAllExceptions(Error a) {
    System.out.println(a.toString());
    System.out.println("error");
    System.out.println(logic.getLogMessage("/api/message/receive"));
    return "index";
  }

  @PostMapping("/api/message/receive")
  public ResponseEntity<?> receiveMessage(@RequestBody IncomingMessage incomingMessage) {
    System.out.println(logic.getLogMessage("/api/message/receive"));
    String status = logic.checkAllFields(incomingMessage);
    if (status.equals("ok")) {
      if (incomingMessage.getClient().getId().equals(System.getenv("CHAT_APP_UNIQUE_ID"))) {
        return new ResponseEntity<>(statusOk, HttpStatus.OK);
      }
      while (logic.checkId(messageRepository, incomingMessage.getMessage().getId())) {
        incomingMessage.getMessage().generateNewId();
      }
      messageRepository.save(incomingMessage.getMessage());
      restTemplate.postForObject(zsolt, incomingMessage, StatusOk.class);
      statusOk = new StatusOk();
      return new ResponseEntity<>(statusOk, HttpStatus.OK);
    }
    statusError = new StatusError(status);
    return new ResponseEntity<>(statusError, HttpStatus.BAD_REQUEST);
  }
}
