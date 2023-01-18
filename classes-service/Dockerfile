FROM openjdk:11
ENV MONGO_DB_USERNAME=rootuser \
    MONGO_DB_PWD=rootpass
COPY  target/classes-service.jar classes-service.jar
ENTRYPOINT ["java","-jar","/classes-service.jar"]