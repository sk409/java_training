FROM maven:3.6.3-jdk-8

ARG APP_DIR=/app

RUN apt-get update && apt-get install -y \
    locales-all \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

ENV LANG ja_JP.UTF-8
ENV LANGUAGE ja_JP:ja
ENV LC_ALL ja_JP.UTF-8

WORKDIR ${APP_DIR}
COPY project/pom.xml .
RUN mvn -B package

CMD ["mvn", "compile", "exec:java", "-Dexec.mainClass=training.tcmobile.App"]