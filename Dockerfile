FROM openjdk:8u151-jdk
MAINTAINER 449190067@qq.com

COPY target/springBootDemo-1.0-SNAPSHOT.jar /springBootDemo.jar

ENTRYPOINT ["java", "-jar", "/springBootDemo.jar"]