#Webervice with multiple URIs in JBoss EAP 6

If you try something like this:

```
<servlet>
  <servlet-name>HelloEndpoint</servlet-name>
  <servlet-class>com.redhat.gss.HelloEndpoint</servlet-class>
</servlet>
<servlet-mapping>
  <servlet-name>HelloEndpoint</servlet-name>
  <url-pattern>/services/hello</url-pattern>
  <url-pattern>/services_/hello</url-pattern>
  <url-pattern>/local/hello</url-pattern>
</servlet-mapping>
```
You will get a CXF exception saying it cannot obtain a destination for
`/<context-root>/local/hello`.  This project is a valve-based solution to work
around this issue.

##Building and Deploying

- Put `mvn` on your `PATH`
- Set `JBOSS_HOME`

###POJO
- Build: `mvn clean install`
- Deploy: `cd endpoint-pojo; mvn jboss-as:deploy`

###EJB
- Use the `./installModuleZip.sh` to build and deploy the shared valve.  This
  will also install the global valve in the JBoss configuration.  **NOTE**:
  Make sure JBoss is running before issuing this command.
- Deploy EJB: `cd endpoint-ejb; mvn jboss-as:deploy`

##Testing
- Test: `./request.sh`
- Change the URL in `request.sh` to try out different URIs
