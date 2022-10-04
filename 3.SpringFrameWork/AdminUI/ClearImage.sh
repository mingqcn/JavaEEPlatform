#!/bin/sh
# Auth: Ming Qiu  Time: 2022-10-3-13:03
# remove the specific docker image

echo "      _                   _                             "
echo "  ___| | ___  __ _ _ __  (_)_ __ ___   __ _  __ _  ___  "
echo " / __| |/ _ \/ _` | '__| | | '_ ` _ \ / _` |/ _` |/ _ \ "
echo "| (__| |  __/ (_| | |    | | | | | | | (_| | (_| |  __/ "
echo " \___|_|\___|\__,_|_|    |_|_| |_| |_|\__,_|\__, |\___| "
echo "                                            |___/       "

for imageName in `docker images $1 |sort -u`
do
  docker rmi $1
done
