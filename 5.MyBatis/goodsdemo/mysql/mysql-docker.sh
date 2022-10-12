docker pull mysql

docker service create --name mysql --constraint node.labels.database=mysql --network my-net -e MYSQL_ROOT_PASSWORD=