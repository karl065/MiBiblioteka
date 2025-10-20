FROM amazoncorretto:21-alpine3.18-jdk

COPY api/target/api-0.0.1-SNAPSHOT.jar /api-v1.jar

ENTRYPOINT ["java","-jar","/api-v1.jar"]