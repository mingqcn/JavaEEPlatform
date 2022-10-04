#!/bin/bash
# Auth: Ming Qiu  Time: 2022-10-3-13:03
# remove the specific docker image

docker_name=$1
check_results=`docker images -q $docker_name | awk '{print $1}'`
for imageID in $check_results
do
  echo "clean image ${imageID}"
  docker rmi imageID
done

