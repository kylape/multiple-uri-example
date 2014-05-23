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

**Note:** Please clone this project into a folder named `multipleUris.war`.

- Put `ant` on your `PATH`
- Set `JBOSS_HOME`
- Build and deploy: `ant deploy`
- Test: `./request.sh`
- Change the URL in `request.sh` to try out different URIs
