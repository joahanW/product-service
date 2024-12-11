FROM openjdk:17

LABEL author="Johan"
LABEL company="TX" website="https://tx.com"

RUN mkdir document
RUN echo "This confidential product!!!" > document/secret.txt
RUN cat document/secret.txt

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} product-service.jar

ENTRYPOINT ["java", "-jar", "./product-service.jar"]

EXPOSE 8085