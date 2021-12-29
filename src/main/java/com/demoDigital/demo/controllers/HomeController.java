package com.demoDigital.demo.controllers;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

  @RequestMapping("/masterdata")
  public String masterdata() {
    return "skill_compentence.json";
  }

  @RequestMapping("/hello")
  @ResponseBody
  public String hello() {
    return "Hello Triet";
  }

  @GetMapping(value = "/users")
  public String getArticles(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient) {

    String token = authorizedClient.getAccessToken().getTokenValue() ;
    System.out.println(authorizedClient.getPrincipalName() + " ------------------->");
    System.out.println(authorizedClient.getAccessToken().getTokenType().getValue() + " token type");
    System.out.println(authorizedClient.getAccessToken().getTokenValue() + " token value");
    System.out.println(authorizedClient.getRefreshToken().getTokenValue() + " ftoken");

    return token;
  }
}
