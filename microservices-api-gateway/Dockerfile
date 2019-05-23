FROM openjdk:8
MAINTAINER Hari Kishore
EXPOSE 9000
ADD target/*.jar /msa-gateway.jar
RUN bash -c 'touch /msa-gateway.jar'
CMD ["java","-Dspring.profiles.active=docker","-jar","/msa-gateway.jar"]