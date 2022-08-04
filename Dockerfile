FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

RUN mkdir -p /app/config

COPY /modules/application/build/libs/application.jar /app/application.jar
COPY /modules/application/src/main/resources/application.yml /app/config/application.yml

ENV JAVA_OPTS="-server -Xms2G -Xmx2G -XX:MaxMetaspaceSize=1G -XX:+UseG1GC -XX:+OptimizeStringConcat -XX:+UseStringDeduplication -Djavax.xml.accessExternalDTD=all -Dfile.encoding=UTF-8 -Dspring.output.ansi.enabled=always -Duser.timezone=America/Bogota -Dspring.config.location=/app/config/"
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/application.jar"]

EXPOSE 8080