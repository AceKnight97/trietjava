package Group4.DigitalCV.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

  @RequestMapping("/")
  public String index() {
    return "index";
  }

  @RequestMapping("/masterdata")
  public String masterdata() {
    return "skill_compentence.json";
  }

  @RequestMapping("/hello")
  @ResponseBody
  public String hello() {
    return "Hello Triet";
  }
}
