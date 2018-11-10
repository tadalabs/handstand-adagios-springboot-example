FROM garland/aws-cli-docker

MAINTAINER Andrew Parise <andrewparise1994@gmail.com>
ENV REFRESHED_AT 2018-10-16

# Install Bash in the Container
RUN apk update && apk add bash

# Add the Create Script to the Container
COPY ./scripts/createTables.sh /etc/createTables.sh

# Add the Table Configuration To the Container
COPY ./scripts/tables/Session.json /etc/Session.json
COPY ./scripts/tables/Todo.json /etc/Todo.json

RUN ["chmod", "+x", "/etc/createTables.sh"]

ENTRYPOINT ["/etc/createTables.sh"]