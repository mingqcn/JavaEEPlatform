#!/bin/sh
# Auth: Ming Qiu  Time: 2022-10-3-13:03
# remove the specific docker image

echo "clean image"
cmd='docker images -q $1'
if [ -n cmd ];then
  docker rmi $(docker images -q $1)
fi

