FROM openjdk:8
MAINTAINER Hari Kishore
EXPOSE 9080
ADD target/*.jar /msa-catalog.jar
RUN bash -c 'touch /msa-catalog.jar'
CMD ["java","-Dspring.profiles.active=docker","-jar","/msa-catalog.jar"]