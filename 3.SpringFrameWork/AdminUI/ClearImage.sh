#!/bin/sh
# Auth: Ming Qiu  Time: 2022-10-3-13:03
# remove the specific docker image

echo "clean image"

for imageName in `docker images $1 |sort -u`
do
  docker rmi $1
done
