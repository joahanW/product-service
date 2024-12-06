FROM openjdk:17

LABEL author="Joahan"

LABEL company="JMarket" website="https://www.jmarket.com"

RUN mkdir document

RUN echo "THIS Confidentiality DOCUMENT!" > "document/confidential.txt"

CMD cat "document/confidentail.txt"

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} serviceregistry.jar

ENTRYPOINT ["java", "-jar", "/serviceregistry.jar"]

EXPOSE 8085