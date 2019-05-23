FROM openjdk:8
MAINTAINER Hari Kishore
EXPOSE 9083
ADD target/*.jar /msa-context.jar
RUN bash -c 'touch /msa-context.jar'
CMD ["java","-Dspring.profiles.active=docker","-jar","/msa-context.jar"]