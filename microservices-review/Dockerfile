FROM openjdk:8
MAINTAINER Hari Kishore
EXPOSE 9081
ADD target/*.jar /msa-review.jar
RUN bash -c 'touch /msa-review.jar'
CMD ["java","-Dspring.profiles.active=docker","-jar","/msa-review.jar"]