#!/bin/bash
# Auth: Ming Qiu  Time: 2022-10-3-13:03
# remove the specific docker image

result=$(`docker images -q $1 | awk '{print $1}'`)
echo "cmd result is : $result"
for imageID in $result
do
  echo "clean image ${imageID}"
  docker rmi imageID
done

