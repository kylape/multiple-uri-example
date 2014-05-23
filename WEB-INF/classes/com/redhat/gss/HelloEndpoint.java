package com.redhat.gss;

import javax.jws.WebService;
import javax.annotation.Resource;
import javax.xml.ws.WebServiceContext;
import org.jboss.logging.Logger;
import javax.xml.ws.handler.MessageContext;
import javax.servlet.ServletRequest;

@WebService
public class HelloEndpoint {
  private static Logger log = Logger.getLogger(HelloEndpoint.class);

  @Resource
  WebServiceContext wsContext;

  public String sayHello(String name) {
    ServletRequest servletRequest = (ServletRequest)wsContext.getMessageContext().get(MessageContext.SERVLET_REQUEST);
    String originalUri = servletRequest.getParameter("originalUri");
    if(originalUri != null) {
      log.info("Original URI: " + originalUri);
    }
    return "Hello, " + name;
  }
}
