FROM maven:3.8.3-openjdk-17 as build
WORKDIR /app

RUN mkdir -p /root/.m2 \
    && mkdir /root/.m2/repository

COPY settings.xml /root/.m2

COPY . /app/
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk
COPY --from=build /app/target/common-1.2.1.jar /app/myapp.jar
CMD java -jar /app/myapp.jar