package trietjava.javalang.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import trietjava.javalang.model.User;
import trietjava.javalang.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {

  @Autowired
  UserRepository userRepo;

  @GetMapping("/")
  public String index() {
    return "index";
  }

  @GetMapping("/user")
  public String getUser() {
    return "Hello world!";
  }

  @GetMapping("/users")
  public List<User> getUsers() {
    return userRepo.findAll();
  }

  @PostMapping("/{id}")
  public String postTest(@PathVariable String id) {
    return id;
  }

}
