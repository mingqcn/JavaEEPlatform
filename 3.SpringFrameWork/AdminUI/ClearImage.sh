#!/bin/sh
# Auth: Ming Qiu  Time: 2022-10-3-13:03
# remove the specific docker image

printf "      _                   _                             \n"
printf "  ___| | ___  __ _ _ __  (_)_ __ ___   __ _  __ _  ___  \n"
printf " / __| |/ _ \/ _` | '__| | | '_ ` _ \ / _` |/ _` |/ _ \ \n"
printf "| (__| |  __/ (_| | |    | | | | | | | (_| | (_| |  __/ \n"
printf " \___|_|\___|\__,_|_|    |_|_| |_| |_|\__,_|\__, |\___| \n"
printf "                                            |___/       \n"

for imageName in `docker images $1 |sort -u`
do
  docker rmi $1
done
