FROM openjdk:11-jre
MAINTAINER mingqiu mingqiu@xmu.edu.cn
WORKDIR /app
ARG JAR_FILE
ADD ${JAR_FILE} /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.boot.admin.client.instance.service-base-url="]
CMD ["http://localhost:8080"]
