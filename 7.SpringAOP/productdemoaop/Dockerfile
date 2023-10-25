FROM ubuntu:20.04
MAINTAINER mingqiu mingqiu@xmu.edu.cn
WORKDIR /app

# 设置环境变量
ENV JDK_VERSION 17
ENV JDK_HOME /opt/jdk
ENV PATH $JDK_HOME/bin:$PATH

# 安装JDK17
RUN apt-get update && \
    apt-get install -y wget && \
    wget --no-check-certificate --no-cookies --header "Cookie: oraclelicense=accept-securebackup-cookie"  && \
    tar -xzf jdk-$JDK_VERSION_linux-x64_bin.tar.gz -C /opt && \
    rm -f jdk-$JDK_VERSION_linux-x64_bin.tar.gz && \
    mv /opt/jdk-$JDK_VERSION /opt/jdk

# 配置Java环境变量
RUN echo "export JAVA_HOME=$JDK_HOME" >> /etc/profile && \
    echo "export PATH=$JDK_HOME/bin:$PATH" >> /etc/profile

ARG JAR_FILE
ADD ${JAR_FILE} /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
CMD ["--spring.datasource.url=jdbc:mysql://mysql:3306/oomall_demo?serverTimezone=Asia/Shanghai","--spring.datasource.username=demouser", "--spring.datasource.password=123456"]