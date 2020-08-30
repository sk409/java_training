#!/bin/bash

readonly UTIL_DIR=$HOME/bamboo/shell/util

execute() {
  $UTIL_DIR/executeJavaSql.sh "docker-compose exec database sh -c \"mysql -u $DB_USER -p$DB_PASSWORD -sN $DB_DATABASE -e'SELECT name, age FROM persons' 2> /dev/null\""
}

result="$(execute)"
result=$(echo "$result" | tr -d "\r" | tr "\n" "," | tr "\t" ",")

readonly EXPECTED="person1,32,person2,43,person3,25,person4,28,"

if [ "$result" == "$EXPECTED" ]; then
  echo "OK"
fi