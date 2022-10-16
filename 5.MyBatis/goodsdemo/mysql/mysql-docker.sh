#https://github.com/docker-library/docs/tree/master/mysql
docker pull mysql

docker service create --name mysql --constraint node.labels.database=mysql --network my-net -e MYSQL_ROOT_PASSWORD=123456  -v  -d mysql:latest
docker service ps mysql
docker run -it --network some-network --rm mysql mysql -hsome-mysql -uexample-user -p