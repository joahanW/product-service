FROM digiotech/doc_signer:jdk17-arm64

LABEL author="Johan"
      website="johan.com"

RUN mkdir app
RUN echo "Hello world" > app/hello.txt

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} product-service.jar

CMD ["java", "-jar", "product-service.jar"]

EXPOSE 8085

