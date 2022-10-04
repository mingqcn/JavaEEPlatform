#!/bin/sh
# Auth: Ming Qiu  Time: 2022-10-3-13:03
# remove the specific docker image

echo "clean image"
docker rmi $(docker images $1)

