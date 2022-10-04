#!/bin/bash
# Auth: Ming Qiu  Time: 2022-10-3-13:03
# remove the specific docker image

check_results=`docker images -q $1 | awk '{print $1}'`
echo $check_results
for imageID in $check_results
do
  echo "clean image ${imageID}"
  docker rmi imageID
done

