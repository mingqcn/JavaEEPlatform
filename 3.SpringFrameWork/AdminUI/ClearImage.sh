#!/bin/sh
# Auth: Ming Qiu  Time: 2022-10-3-13:03
# remove the specific docker image

echo "clean image"
if [-n docker images -q $1];then
  docker rmi $(docker images -q $1)
fi

