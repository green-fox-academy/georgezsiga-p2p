package com.greenfox.Controller;

import com.greenfox.Logic;
import com.greenfox.Model.Client;
import com.greenfox.Model.IncomingMessage;
import com.greenfox.Model.Message;
import com.greenfox.Model.Status;
import com.greenfox.Model.StatusError;
import com.greenfox.Model.StatusOk;
import com.greenfox.Repository.MessageRepository;
import java.sql.Time;
import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
  String url = "localhost8080/api/message/received";
  RestTemplate restTemplate = new RestTemplate();

  StatusOk statusOk;
  StatusError statusError;

  @ExceptionHandler(Exception.class)
  public String handleAllExceptions(Error a) {
    System.out.println(a.toString());
    System.out.println("error");
    System.out.println(logic.getLogMessage("/api/message/receive"));
    return "index";
  }

  @PostMapping("/api/message/receive")
  public Status receiveMessage(@RequestBody IncomingMessage incomingMessage) {
    System.out.println(logic.getLogMessage("/api/message/receive"));
    String status = logic.checkAllFields(incomingMessage.getMessage());
    if (status.equals("ok")) {
      messageRepository.save(incomingMessage.getMessage());
      statusOk = new StatusOk();
      return statusOk;
    }
    statusError = new StatusError(status);
    return statusError;
  }

  @GetMapping("/add")
  public Message message(@RequestParam(name = "id") Long id, @RequestParam(name="username") String username, @RequestParam(name="text") String text, @RequestParam(name="timestamp") Timestamp timestamp) {
    System.out.println("hi");
    Message message = new Message(id, username, text, timestamp);
    Message newMessage = restTemplate.postForObject(url, message, Message.class);
    return newMessage;
  }



}
