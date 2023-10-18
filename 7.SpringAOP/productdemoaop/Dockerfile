FROM openjdk:11-jre
MAINTAINER mingqiu mingqiu@xmu.edu.cn
WORKDIR /app
ARG JAR_FILE
ADD ${JAR_FILE} /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
CMD ["--spring.datasource.url=jdbc:mysql://mysql:3306/oomall_demo?serverTimezone=Asia/Shanghai","--spring.datasource.username=demouser", "--spring.datasource.password=123456"]