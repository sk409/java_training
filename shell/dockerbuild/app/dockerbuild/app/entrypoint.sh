#!/bin/bash

while :
do
    is_alive=$(mysqladmin ping -h database -u $DB_USER -p$DB_PASSWORD 2> /dev/null)
    if [ "$is_alive" = "mysqld is alive" ]; then
      break
    fi
    sleep 1
done

cd $APP_DIR

mvn -q compile exec:java -Dexec.mainClass=training.tcmobile.App