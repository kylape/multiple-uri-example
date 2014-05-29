package com.redhat.gss;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.servlet.ServletRequest;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.jboss.logging.Logger;
import org.jboss.ws.api.annotation.WebContext;

@WebService
@Stateless
@WebContext(urlPattern="services/hello")
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
