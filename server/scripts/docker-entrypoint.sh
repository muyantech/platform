#!/usr/bin/env bash

# Construct JDBC_DATABASE_URL
if [ $PAAS_VENDOR = 'render' ]; then
  export JDBC_DATABASE_URL=jdbc:$JDBC_DATABASE_TYPE://$JDBC_DATABASE_HOST:$JDBC_DATABASE_PORT/$JDBC_DATABASE_DB
fi

# Extract information from DATABASE_URL and build JDBC_DATABASE_URL
# /jdbc.sh will not work as the environment variable setted will not be
# exposed to current script
# https://stackoverflow.com/questions/496702/can-a-shell-script-set-environment-variables-of-the-calling-shell
if [ $PAAS_VENDOR = 'heroku' ] || [ $PAAS_VENDOR = 'fly' ] || [ $PAAS_VENDOR = 'dokku' ]; then
 . /jdbc.sh
fi

printenv

java \
    "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005" \
    -Dgrails.env=$GRAILS_ENV \
    -Dserver.port=$PORT \
    -Dgorm.tenantId=muyan \
    $JAVA_TOOL_OPTIONS \
    $JAVA_OPTS \
    -jar /app/boot.jar
