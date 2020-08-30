#!/bin/bash

readonly DIR=$(uuidgen)

mkdir $DIR

cp -r $HOME/bamboo/docker/java/sql/. $DIR/
cp App.java $DIR/project/src/main/java/training/tcmobile/

cd $DIR

docker-compose up -d --build > /dev/null 2>&1

while :
do
  has_app_terminated=$(docker-compose ps | grep app | grep Exit)
  if [ -n "$has_app_terminated" ]; then
    break
  fi
  sleep 1
done

command=$1
readonly RESULT=$(eval $command)
echo "$RESULT"

docker-compose down > /dev/null 2>&1

cd ..

rm -rf $DIR