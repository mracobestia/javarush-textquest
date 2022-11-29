FROM tomcat:9.0.69-jdk17-corretto

COPY ./target/text-quest.war /usr/local/tomcat/webapps