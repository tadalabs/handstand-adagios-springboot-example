# Tomcat 7 image with build artifact baked

FROM tomcat:7.0.79-jre8

MAINTAINER Andrew Parise <andrewparise1994@gmail.com>
ENV REFRESHED_AT 2018-10-16

RUN ["rm", "-fr", "/usr/local/tomcat/webapps/ROOT"]

COPY ./target/sample-web-app-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

COPY ./conf/tomcat-users.xml /usr/local/tomcat/conf/tomcat-users.xml
COPY ./conf/context.xml /usr/local/tomcat/conf/context.xml
COPY ./conf/server.xml /usr/local/tomcat/conf/server.xml

EXPOSE 8082
EXPOSE 8003

ENV JPDA_ADDRESS 8003
CMD ["catalina.sh", "jpda", "run", "--debug"]