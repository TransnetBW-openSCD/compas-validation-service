FROM eclipse-temurin:21-jre

WORKDIR /service

COPY ./app/target/quarkus-app/quarkus-run.jar .

EXPOSE 8080
USER 1001

ENV JAVA_OPTS="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"

ENTRYPOINT ["java", "-jar", "/service/quarkus-run.jar"]
