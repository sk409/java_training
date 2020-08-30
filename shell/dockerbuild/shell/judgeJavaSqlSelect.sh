#!/bin/bash

readonly UTIL_DIR=$HOME/bamboo/shell/util

execute() {
  $UTIL_DIR/executeJavaSql.sh 'docker logs $(docker-compose ps -q app)'
}

result="$(execute)"
readonly EXPECTED="person1,32,person2,43,person3,25,"

if [ "$result" == "$EXPECTED" ]; then
  echo "OK"
fi