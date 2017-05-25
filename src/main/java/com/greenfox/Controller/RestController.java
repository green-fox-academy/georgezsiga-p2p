package com.greenfox.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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

  String marci = "https://p2p-chat-seed0forever.herokuapp.com/api/message/receive";
  String zsolt = "https://p2p-by-nagyza.herokuapp.com/api/message/receive";
  RestTemplate restTemplate = new RestTemplate();
  ObjectMapper mapper = new ObjectMapper();
  Logic logic = new Logic();

  private StatusOk statusOk = new StatusOk();
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
    try {
      String jsonInString = mapper.writeValueAsString(incomingMessage);
      System.out.println(jsonInString);
    } catch (Exception e) {
      System.out.println("exception");
    }
    if (logic.checkAllFields(incomingMessage).equals("ok")) {
      if (incomingMessage.getClient().getId().equals(System.getenv("CHAT_APP_UNIQUE_ID"))) {
        return new ResponseEntity<>(statusOk, HttpStatus.OK);
      }
      while (logic.checkId(messageRepository, incomingMessage.getMessage().getId())) {
        incomingMessage.getMessage().generateNewId();
      }
      messageRepository.save(incomingMessage.getMessage());
//      restTemplate.postForObject(marci, incomingMessage, Status.class);
      restTemplate.postForObject(zsolt, incomingMessage, Status.class);
      return new ResponseEntity<>(statusOk, HttpStatus.OK);
    }
    statusError = new StatusError(logic.checkAllFields(incomingMessage));
    return new ResponseEntity<>(statusError, HttpStatus.BAD_REQUEST);
  }
}
