FROM openjdk:8
MAINTAINER Hari Kishore
EXPOSE 7979
ADD target/*.jar /msa-hystrix.jar
RUN bash -c 'touch /msa-hystrix.jar'
CMD ["java","-Dspring.profiles.active=docker","-jar","/msa-hystrix.jar"]