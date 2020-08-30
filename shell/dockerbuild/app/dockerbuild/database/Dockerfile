FROM mysql:5

ENV DEBIAN_FRONTEND noninteractive

RUN apt-get update && apt-get install -y \
    locales \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

RUN echo "ja_JP.UTF-8 UTF-8" > /etc/locale.gen \
    && locale-gen ja_JP.UTF-8 \
    && dpkg-reconfigure locales \
    && /usr/sbin/update-locale LANG=ja_JP.UTF-8

ENV LC_ALL ja_JP.UTF-8

COPY initdb/ /docker-entrypoint-initdb.d/