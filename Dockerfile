FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /build
COPY pom.xml .
COPY validation/pom.xml validation/
COPY api/pom.xml api/
COPY app/pom.xml app/

RUN mvn dependency:go-offline -B

COPY validation/src validation/src
COPY api/src api/src
COPY app/src app/src

RUN mvn package -DskipTests -B

FROM eclipse-temurin:21-jre

WORKDIR /deployments

COPY --from=build /build/app/target/quarkus-app/lib/ lib/
COPY --from=build /build/app/target/quarkus-app/*.jar .
COPY --from=build /build/app/target/quarkus-app/app/ app/
COPY --from=build /build/app/target/quarkus-app/quarkus/ quarkus/

EXPOSE 8080
USER 1001

ENV JAVA_OPTS="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"

ENTRYPOINT ["java", "-jar", "quarkus-run.jar"]
