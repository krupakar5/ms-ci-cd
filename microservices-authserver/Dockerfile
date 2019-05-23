FROM openjdk:8
MAINTAINER Hari Kishore
EXPOSE 9999
ADD target/*.jar /msa-auth.jar
RUN bash -c 'touch /msa-auth.jar'
CMD ["java","-Dspring.profiles.active=docker","-jar","/msa-auth.jar"]