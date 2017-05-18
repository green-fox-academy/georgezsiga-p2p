package com.greenfox.Controller;

import com.greenfox.Logic;
import com.greenfox.Model.Error;
import com.greenfox.Model.Felhasznalo;
import com.greenfox.Model.Message;
import com.greenfox.Repository.MessageRepository;
import com.greenfox.Repository.UserRepository;
import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

  @ExceptionHandler(Exception.class)
  public String handleAllExceptions(Exception a) {
    System.out.println(a);
    System.out.println(logic.getLogMessage("/"));
    return "index";
  }

  @RequestMapping("/")
  public String home(@RequestParam(value = "error", required = false) String error, Model message, Model model, Model id) {
    System.out.println(logic.getLogMessage("/"));
if (logic.userTimeout(userRepository)) {
  return "redirect:/enter?error=sessiontimedout";
} else {
      Felhasznalo user = userRepository.findFirstByOrderByLastActiveDesc();
      long id2 = user.getId();
      message.addAttribute("message", messageRepository.findAll());
      model.addAttribute("user", user.getUsername());
      id.addAttribute("id", id2);
      return "index";
    }
  }

  @RequestMapping("/enter")
  public String register(@RequestParam(value = "error", required = false) String error,
      Model message) {
    System.out.println(logic.getLogMessage("/enter"));
//    message.addAttribute("error", Error.valueOf(error).getMessage());
    return "register";
  }

  @RequestMapping("/enterform")
  public String registerForm(@RequestParam(value = "username", required = false) String username) {
    System.out.println(logic.getLogMessage("/enterform"));
    if (username.equals("")) {
      return "redirect:/enter?error=nousername";
    }
    if (userRepository.findFelhasznaloByUsername(username) != null) {
      userRepository.findFelhasznaloByUsername(username).setLastActive();
      userRepository.save(userRepository.findByUsername(username));
      return "redirect:/";
    }
    userRepository.save(new Felhasznalo(username));
    return "redirect:/";
  }

  @RequestMapping("/updateform")
  public String updateForm(@RequestParam(value = "username", required = false) String username,
      @RequestParam(value = "id") long id) {
    System.out.println(logic.getLogMessage("/updateForm"));
    if (username.equals("")) {
      return "redirect:/enter?error=nousername";
    }
    userRepository.findFelhasznaloById(id).setUsername(username);
    userRepository.findFelhasznaloById(id).setLastActive();
    userRepository.save(userRepository.findFelhasznaloById(id));
    return "redirect:/";
  }

  @RequestMapping("/newmessageform")
  public String newMessageForm(@RequestParam(value = "message", required = false) String message,
      @RequestParam(value = "id") long id) {
    System.out.println(logic.getLogMessage("/updateForm"));
    if (message.equals("")) {
      System.out.println("null");
      return "redirect:/?error=nomessage";
    }
    Message message1 = new Message(message, userRepository.findFelhasznaloById(id).getUsername());
    while (logic.checkId(messageRepository, message1.getId())) {
      message1.generateNewId();
    }
    messageRepository.save(message1);
    userRepository.findFelhasznaloById(id).setLastActive();
    userRepository.save(userRepository.findFelhasznaloById(id));
    return "redirect:/";
  }
}
