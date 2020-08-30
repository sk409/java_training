FROM maven:3.6.3-jdk-8

ARG APP_DIR=/app

COPY project/pom.xml ${APP_DIR}/

WORKDIR ${APP_DIR}

RUN mvn -B package

CMD ["mvn", "compile", "exec:java", "-Dexec.mainClass=training.tcmobile.App"]