FROM eclipse-temurin:25-jre

WORKDIR /service

#COPY ./app/target/quarkus-app/quarkus-run.jar .

EXPOSE 8080
USER 1001

ENV JAVA_OPTIONS="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"
ENV JAVA_MAX_MEM_RATIO="70"
# We make four distinct layers so if there are application changes the library layers can be re-used
COPY --chown=1001 app/target/quarkus-app/lib/ /service/lib/
COPY --chown=1001 app/target/quarkus-app/*.jar /service/
COPY --chown=1001 app/target/quarkus-app/app/ /service/app/
COPY --chown=1001 app/target/quarkus-app/quarkus/ /service/quarkus/

ENTRYPOINT ["java", "-jar", "/service/quarkus-run.jar"]
