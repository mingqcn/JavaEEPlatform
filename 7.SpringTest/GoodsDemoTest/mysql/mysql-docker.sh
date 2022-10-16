#https://github.com/docker-library/docs/tree/master/mysql
docker pull mysql
docker service create --name mysql --constraint node.labels.database=mysql --network my-net -e MYSQL_ROOT_PASSWORD=123456  -d mysql:latest
docker service ps mysql
docker exec -it mysql -uroot -p