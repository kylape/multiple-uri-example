package com.redhat.gss;

import javax.jws.WebService;

@WebService
public class HelloEndpoint {
  public String sayHello(String name) {
    return "Hello, " + name;
  }
}
