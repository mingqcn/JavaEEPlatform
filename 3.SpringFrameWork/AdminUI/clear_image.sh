#!/bin/bash
# Auth: Ming Qiu  Time: 2022-10-3-13:03
# remove the specific docker image

echo "clean image"
docker_cmd='docker images -q $1 | awk "{print $1}"'
for imageID in $(docker_cmd)
do
  echo "clean image ${imageID}"
  docker rmi imageID
done

