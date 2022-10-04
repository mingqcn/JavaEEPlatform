#!/bin/bash
# Auth: Ming Qiu  Time: 2022-10-3-13:03
# remove the specific docker image

echo "clean image"
docker images -q $1
for imageID in $cmd
do
  echo "clean image ${imageID}"
  docker rmi imageID
done

