FROM amazonlinux

ARG USER=ec2-user

RUN yum -y update && yum -y install \
    gzip \
    shadow-utils \
    sudo \
    tar \
    util-linux-ng \
    && rm -rf /var/cache/yum/* \
    && yum clean all

ARG DOCKER_VERSION=18.09.1
RUN curl -fL -o docker.tgz "https://download.docker.com/linux/static/test/x86_64/docker-$DOCKER_VERSION.tgz" \
    && tar --strip-component=1 -xvaf docker.tgz -C /usr/bin
RUN curl -L https://github.com/docker/compose/releases/download/1.24.1/docker-compose-`uname -s`-`uname -m` > /usr/local/bin/docker-compose \
    && chmod +x /usr/local/bin/docker-compose

RUN useradd ${USER} \
    && echo "${USER} ALL=NOPASSWD: ALL" >> /etc/sudoers.d/${USER}

ENV PATH=$PATH:/home/${USER}/bamboo/shell

USER ${USER}

WORKDIR /home/${USER}/bamboo