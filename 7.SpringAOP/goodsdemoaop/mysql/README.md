#在Docker Swarm上部署MySQL的应用
##安装MySQL镜像
首先在目标服务器上安装MySQL镜像，并将MySQL的配置文件和运行脚本拷贝到JavaEEPlatform/mysql目录下
·docker pull mysql·
##更新目标服务器的label
为目标服务器定义label，方便docker swarm在创建Service时，将Service部署在目标服务器上，以下我们在node6上定义了一个label server=mysql
·docker node update --label-add server=mysql node6·
##在docker swarm中创建服务
将机器上的MySQL配置目录和运行SQL脚本映射到docker容器中
‘docker service create --name mysql --constraint node.labels.server==mysql --mount type=bind,source=/root/JavaEEPlatform/mysql/sql,destination=/sql,readonly --mount type=bind,source=/root/JavaEEPlatform/mysql/conf.d,destination=/etc/mysql/conf.d,readonly  --network my-net -e MYSQL_ROOT_PASSWORD=123456  -d mysql:latest·
看一下mysql的服务运行在哪台服务器
·docker service ps mysql·
切换到运行服务的机器，看一下容器在这台机器的container id，用container id运行mysql的命令
docker c -it mysql -uroot -p