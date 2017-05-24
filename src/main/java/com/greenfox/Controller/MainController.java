package com.greenfox.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenfox.Logic;
import com.greenfox.Model.ErrorMessages;
import com.greenfox.Model.Felhasznalo;
import com.greenfox.Model.IncomingMessage;
import com.greenfox.Model.Message;
import com.greenfox.Model.StatusOk;
import com.greenfox.Repository.MessageRepository;
import com.greenfox.Repository.UserRepository;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import javax.swing.JEditorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

/**
 * Created by georgezsiga on 5/17/17.
 */
@Controller
public class MainController {

  @Autowired
  UserRepository userRepository;

  @Autowired
  MessageRepository messageRepository;

  Logic logic = new Logic();

  ObjectMapper mapper = new ObjectMapper();

  String viktor = "https://chat-p2p.herokuapp.com/api/message/receive";
  String patrik = "https://phorv1chatapp.herokuapp.com/api/message/receive";
  String ramin = "https://greenfox-chat-app.herokuapp.com/api/message/receive";
  String nora = "https://peertopeerchat.herokuapp.com/api/message/receive";

  RestTemplate restTemplate = new RestTemplate();
  Timestamp lastLogin;

  @ExceptionHandler(Exception.class)
  public String handleAllExceptions(Exception a) {
    System.out.println(a);
    System.out.println(logic.getLogMessage("/"));
    return "index";
  }

  @RequestMapping("/")
  public String home(@RequestParam(value = "error", required = false) String error, Model message,
      Model model, Model id, Model users) {
    System.out.println(logic.getLogMessage("/"));
    Map<String, String> env = System.getenv();
    for (String envName : env.keySet()) {
      System.out.format("%s=%s%n",
          envName,
          env.get(envName));
    }
    if (logic.userTimeout(userRepository)) {
      return "redirect:/enter?error=sessiontimedout";
    } else {
      Felhasznalo user = userRepository.findFirstByOrderByLastActiveDesc();
      long id2 = user.getId();
      message.addAttribute("message",
          messageRepository.findAllByOrderByTimestampDesc());
      model.addAttribute("user", user.getUsername());
      users.addAttribute("users", messageRepository.findAllByOrderByTimestamp());
      id.addAttribute("id", id2);
      if (error != null) {
        error = error.toUpperCase();
        message.addAttribute("error", ErrorMessages.valueOf(error).toString());
      }
      return "index";
    }
  }

  @RequestMapping("/new")
  public String sinceLastLogin(@RequestParam(value = "time", required = false) String error, Model message,
      Model model, Model id, Model users) {
    System.out.println(logic.getLogMessage("/new"));
    System.out.println(lastLogin);
    Map<String, String> env = System.getenv();
    for (String envName : env.keySet()) {
      System.out.format("%s=%s%n",
          envName,
          env.get(envName));
    }
    if (logic.userTimeout(userRepository)) {
      return "redirect:/enter?error=sessiontimedout";
    } else {
      Felhasznalo user = userRepository.findFirstByOrderByLastActiveDesc();
      long id2 = user.getId();
//      message.addAttribute("message",
//          messageRepository.findAllByOrderByTimestampDesc());
      message.addAttribute("message", messageRepository.findAllByTimestampIsAfterOrderByTimestampDesc(lastLogin));
      model.addAttribute("user", user.getUsername());
//      users.addAttribute("users", messageRepository.findAllByOrderByTimestamp());
      users.addAttribute("users", messageRepository.findAllByOrderByTimestamp());
      id.addAttribute("id", id2);
      if (error != null) {
        error = error.toUpperCase();
        message.addAttribute("error", ErrorMessages.valueOf(error).toString());
      }
      return "time";
    }
  }

  @RequestMapping("/user")
  public String user(@RequestParam(value = "username") String username,
      @RequestParam(value = "error", required = false) String error, Model message,
      Model model, Model id, Model users, Model usermessages) {
    System.out.println(logic.getLogMessage("/"));
    Map<String, String> env = System.getenv();
    for (String envName : env.keySet()) {
      System.out.format("%s=%s%n",
          envName,
          env.get(envName));
    }
    if (logic.userTimeout(userRepository)) {
      return "redirect:/enter?error=sessiontimedout";
    } else {
      Felhasznalo user = userRepository.findFirstByOrderByLastActiveDesc();
      long id2 = user.getId();
      message.addAttribute("message",
          messageRepository.findAllByOrderByTimestampDesc());
      model.addAttribute("user", user.getUsername());
      users.addAttribute("users", messageRepository.findAllByOrderByTimestamp());
      usermessages.addAttribute("usermessages", messageRepository.findAllByUsernameOrderByTimestampDesc(username));
      id.addAttribute("id", id2);
      if (error != null) {
        error = error.toUpperCase();
        message.addAttribute("error", ErrorMessages.valueOf(error).toString());
      }
      return "user";
    }
  }

  @RequestMapping("/refresh")
  public String refresh() {
    System.out.println(logic.getLogMessage("/refresh"));
    JEditorPane editorPane = new JEditorPane();
    try {
      editorPane.setPage("http://localhost8080/");
    } catch (Exception e) {
      System.out.println("error");
    }
    return "redirect:/test";
  }

  @RequestMapping("/enter")
  public String register(@RequestParam(value = "error", required = false) String error,
      Model message) {
    System.out.println(logic.getLogMessage("/enter"));
    if (error != null) {
      error = error.toUpperCase();
      message.addAttribute("error", ErrorMessages.valueOf(error).toString());
    }
    return "register";
  }

  @RequestMapping("/enterform")
  public String registerForm(@RequestParam(value = "username", required = false) String username) {
    System.out.println(logic.getLogMessage("/enterform"));
    if (username.equals("")) {
      return "redirect:/enter?error=nousername";
    }
    if (userRepository.findFelhasznaloByUsername(username) != null) {
      lastLogin = userRepository.findFelhasznaloByUsername(username).getLastActive();
      logic.updateLastActive(userRepository, username);
      return "redirect:/new";
    }
    if (messageRepository.findAllByUsername(username) != null) {
      return "redirect:/enter?error=usernamealreadytaken";
    }
    userRepository.save(new Felhasznalo(username));
    return "redirect:/";
  }

  @RequestMapping("/updateform")
  public String updateForm(@RequestParam(value = "username", required = false) String username,
      @RequestParam(value = "id") long id, @RequestParam(value="checkbox", required = false) String checkbox) {
    System.out.println(logic.getLogMessage("/updateform"));
    if (username.equals("")) {
      return "redirect:/?error=nousername";
    }
    if (userRepository.findFelhasznaloByUsername(username) != null) {
      return "redirect:/?error=usernamealreadytaken";
    }
    if (messageRepository.findAllByUsername(username) != null) {
      return "redirect:/?error=usernamealreadytaken";
    }
    if (checkbox == null) {
      userRepository.findFelhasznaloById(id).setUsername(username);
      logic.updateLastActive(userRepository, id);
      return "redirect:/";
    }
    List<Message> userMessages = messageRepository.findAllByUsername(userRepository.findFelhasznaloById(id).getUsername());
    for (Message m : userMessages) {
      m.setUsername(username);
      messageRepository.save(m);
    }
    userRepository.findFelhasznaloById(id).setUsername(username);
    logic.updateLastActive(userRepository, id);
    return "redirect:/";
  }

  @RequestMapping("/newmessageform")
  public String newMessageForm(@RequestParam(value = "message", required = false) String message,
      @RequestParam(value = "id") long id) {
    System.out.println(logic.getLogMessage("/newmessageform"));
    if (message.equals("")) {
      return "redirect:/?error=nomessage";
    }
    Message message1 = new Message(message, userRepository.findFelhasznaloById(id).getUsername());
    while (logic.checkId(messageRepository, message1.getId())) {
      message1.generateNewId();
    }
    messageRepository.save(message1);
    IncomingMessage incom = new IncomingMessage(message1);
    Felhasznalo user = userRepository.findFirstByOrderByLastActiveDesc();
    incom.getClient().setId(System.getenv("CHAT_APP_UNIQUE_ID"));
    System.out.println(System.getenv("CHAT_APP_UNIQUE_ID"));
    try {
      String jsonInString = mapper.writeValueAsString(incom);
      System.out.println(jsonInString);
    } catch (Exception e) {
      System.out.println("exception");
    }

    restTemplate.postForObject(nora, incom, StatusOk.class);
//   StatusOk newMessage =  restTemplate.postForObject(patrik, incom, StatusOk.class);
    logic.updateLastActive(userRepository, id);
    return "redirect:/";
  }
}
