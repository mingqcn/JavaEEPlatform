#在Docker Swarm上部署MySQL的应用
##在主服务器上创建swarm集群
`docker swarm init`
按照上述命令的提示，将`docker swarm join`拷贝粘贴到其他服务器上运行，将这些服务器作为节点加入docker swarm 集群
用`docker node ls`命令检查这些节点是否加入了集群
##在docker swarm中建立自己的网络(my-net)
`docker network create --driver overlay my-net`
##安装MySQL镜像
首先在目标服务器上安装MySQL镜像，并将MySQL的配置文件和运行脚本拷贝到JavaEEPlatform/mysql目录下
`docker pull mysql`
##更新目标服务器的label
为目标服务器定义label，方便docker swarm在创建Service时，将Service部署在目标服务器上，以下我们在node6上定义了一个label `server=mysql`
`docker node update --label-add server=mysql node6`
##在docker swarm中创建服务
将机器上的MySQL配置目录和运行SQL脚本映射到docker容器中，并把mysql的服务加入到my-net网络
`docker service create --name mysql --constraint node.labels.server==mysql --mount type=bind,source=/root/JavaEEPlatform/mysql/sql,destination=/sql,readonly --mount type=bind,source=/root/JavaEEPlatform/mysql/conf.d,destination=/etc/mysql/conf.d,readonly  --network my-net -e MYSQL_ROOT_PASSWORD=123456  -d mysql:latest`
其中`-e MYSQL_ROOT_PASSWORD=123456`是设定数据库root账户密码
##在运行mysql服务的节点上运行sql脚本
看一下mysql的服务运行在哪台服务器
`docker service ps mysql`
切换到运行mysql服务的机器，看一下mysql容器在这台机器的container id，将容器的CONTAINER ID拷贝替换下述命令中[CONTAINER ID],用这个容器运行mysql的命令
`docker exec -it [CONTAINER ID] mysql -uroot -p`
用root账号登录mysql服务器，在运行起来的mysql命令行中用`source /sql/database.sql`建立oomall_demo数据库
用`use oomall_demo`切换数据库
用`source /sql/data.sql`插入初始数据
##下载部署工程
选择一台机器用`git clone`下载JavaEEPlatform工程
在工程中用`mvn clean pre-integration-test -Dmaven.skip.test=true`编译部署工程的docker的image
##在管理机上创建工程的服务
`docker service create --name goodsdemoaop  --network my-net --publish published=8080,target=8080 -d xmu-javaee/goodsdemoaop:0.0.1-SNAPSHOT`
用`docker service ps goodsdemoaop`即可已看到该服务运行在哪个节点上
切换到运行goodsdemoaop服务的机器，看一下goodsdemoaop容器在这台机器的container id，将容器的CONTAINER ID拷贝替换下述命令中[CONTAINER ID],可以看到服务运行的输出
`docker logs [CONTAINER ID]`
如果发现服务器运行正常，你就可以访问这台服务器了