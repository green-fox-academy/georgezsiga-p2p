package com.greenfox.Controller;

import com.greenfox.Logic;
import com.greenfox.Model.Error;
import com.greenfox.Model.Felhasznalo;
import com.greenfox.Repository.UserRepository;
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

  Logic logic = new Logic();

  @ExceptionHandler(Exception.class)
  public String handleAllExceptions(Exception a) {
    System.out.println(a);
    System.out.println(logic.getLogMessage("/"));
    return "index";
  }

  @RequestMapping("/")
  public String home(@RequestParam(value = "username", required = false) String username,
      Model model, Model id) {
    System.out.println(logic.getLogMessage("/"));
    if (username == null) {
      return "redirect:/enter";
    } else {
      model.addAttribute("user", username);
      Felhasznalo user = userRepository.findFelhasznaloByUsername(username);
      long id2 = user.getId();
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
      return "redirect:/?username=" + username;
    }
    userRepository.save(new Felhasznalo(username));
    return "redirect:/?username=" + username;
  }

  @RequestMapping("/updateform")
  public String updateForm(@RequestParam(value = "username", required = false) String username,
      @RequestParam(value = "id") long id) {
    System.out.println(logic.getLogMessage("/updateForm"));
    if (username.equals("")) {
      System.out.println("null");
      return "redirect:/enter?error=nousername";
    }
      userRepository.delete(id);
    userRepository.save(new Felhasznalo(username));
    return "redirect:/?username=" + username;
  }
}
