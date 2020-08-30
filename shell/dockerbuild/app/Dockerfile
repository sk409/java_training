FROM openjdk:8-jdk

ENV DEBIAN_FRONTEND noninteractive

RUN apt-get update && apt-get install -y \
    default-mysql-client \
    locales \
    maven \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

RUN echo "ja_JP.UTF-8 UTF-8" > /etc/locale.gen \
    && locale-gen ja_JP.UTF-8 \
    && dpkg-reconfigure locales \
    && /usr/sbin/update-locale LANG=ja_JP.UTF-8

ENV LC_ALL ja_JP.UTF-8

ENV APP_DIR=/app

COPY project/pom.xml ${APP_DIR}/
COPY project/src/ ${APP_DIR}/src/

WORKDIR ${APP_DIR}

RUN mvn -B package

RUN mkdir /out

COPY dockerbuild/app/entrypoint.sh /
RUN chmod +x /entrypoint.sh

CMD ["/entrypoint.sh"]