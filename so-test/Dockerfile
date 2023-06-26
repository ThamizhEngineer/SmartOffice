FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.war
COPY ${JAR_FILE} app.war
ENTRYPOINT ["java","-Xmx256m","-XX:+UseG1GC","-XX:-UseGCOverheadLimit","-jar","/app.war"]