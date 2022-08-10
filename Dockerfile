# syntax=docker/dockerfile:1
# Install Tomcat & openjdk 8 (openjdk has java and javac)
FROM tomcat:jdk8-openjdk
# Copy source files to tomcat folder structure
COPY . /usr/local/tomcat/webapps/
# -cp, Adding compile time classpath as Tomcat's /lib/servlet-api.jar file.
# - d, destination output location.
RUN ["javac", "-cp", ".:/usr/local/tomcat/lib/servlet-api.jar", "-d", "/usr/local/tomcat/webapps/java_web_demo/WEB-INF/classes/", "C:/wamp/www/java_decagon/src/main/java/com/java_web/AccountManagementServlet.java"]

# RUN ["javac", "-cp", ".:/usr/local/tomcat/lib/servlet-api.jar", "-d", "/usr/local/tomcat/webapps/myApp/WEB-INF/classes/", "/usr/local/tomcat/webapps/myApp/src/ContactFormServlet.java"]

# Serve Tomcat
EXPOSE 4000
CMD ["catalina.sh", "run"]