FROM java:8-jdk-alpine
MAINTAINER kayode emmanuel <ekayode700@gmail.com>
WORKDIR /usr/app
ADD target/*.jar /usr/app/
EXPOSE 8990
ENTRYPOINT ["java", "-jar", "StockApp-Service-0.0.1-SNAPSHOT.jar"]
