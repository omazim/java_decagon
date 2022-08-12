FROM tomcat:9.0.65
COPY target/java_web_demo.war /usr/local/tomcat/webapps/

# Serve Tomcat
# EXPOSE 4000