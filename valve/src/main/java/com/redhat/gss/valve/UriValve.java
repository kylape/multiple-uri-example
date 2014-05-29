package com.redhat.gss.valve;

import java.io.IOException;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.valves.ValveBase;
import org.apache.tomcat.util.buf.CharChunk;
import org.jboss.logging.Logger;

public class UriValve extends ValveBase {
  private static final String ENDPOINT_URI_FRAGMENT = "/services/hello";
  private static final Pattern pattern = Pattern.compile("(services(_){1})|local");
  private static final Logger log = Logger.getLogger(UriValve.class);

  public void invoke(Request request, Response response) throws IOException, ServletException {
    String contextPath = request.getContextPath();
    String originalUri = request.getRequestURI();
    String endpointUri = contextPath + ENDPOINT_URI_FRAGMENT;
    if(pattern.matcher(originalUri).find()) {
      if(log.isDebugEnabled()) {
        log.debug("Changing " + originalUri + " to " + endpointUri);
      }
      request.getCoyoteRequest().requestURI().setString(null);
      CharChunk chunk = request.getCoyoteRequest().requestURI().getCharChunk();
      chunk.recycle();
      chunk.append(endpointUri);
      request.getCoyoteRequest().requestURI().toChars();
      request.addParameter("originalUri", new String[] {originalUri});
      request.getMappingData().recycle();
      try {
        request.getConnector()
               .getProtocolHandler()
               .getAdapter()
               .service(request.getCoyoteRequest(), response.getCoyoteResponse());
      } catch (Exception e) {
          // This doesn't actually happen in the Catalina adapter implementation
      }   
    } else {
      getNext().invoke(request, response);
    }
  }
}
