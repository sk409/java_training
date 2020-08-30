#!/bin/bash

readonly UTIL_DIR=$HOME/bamboo/shell/util

execute() {
  $UTIL_DIR/executeJavaSql.sh "docker-compose exec database sh -c \"mysql -u $DB_USER -p$DB_PASSWORD -sN $DB_DATABASE -e'SELECT COUNT(*) FROM persons' 2> /dev/null\""
}

result=$(execute)
result=$(echo $result | sed -e 's/[^0-9]//g')

readonly EXPECTED="2"

if [ $result == $EXPECTED ]; then
  echo "OK"
fi