FROM openjdk:8
MAINTAINER Hari Kishore
EXPOSE 8888
ADD target/*.jar /msa-configserver.jar
RUN bash -c 'touch /msa-configserver.jar'
CMD ["java","-Dspring.profiles.active=docker","-jar","/msa-configserver.jar"]