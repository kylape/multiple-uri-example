package com.redhat.gss;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import javax.jws.WebService;

@javax.ejb.Stateless
@WebService
public class ClientEndpoint {
  
  public void test() throws Exception {
    URL url = new URL("http://localhost:8080/multipleUris/services_/hello?wsdl");
    QName qname = new QName("http://gss.redhat.com/", "HelloEndpointService");
    QName portQ = new QName("http://gss.redhat.com/", "HelloEndpointPort");
    Service service = Service.create(url, qname);
    Hello hello = service.getPort(portQ, Hello.class);
    // ((BindingProvider)hello).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "http://localhost:8080/multipleUris/services_/hello");
    System.out.println(hello.sayHello("Kyle"));
  }
}

