FROM digiotech/doc_signer:jdk17-arm64

WORKDIR /app

LABEL author="Johan" \
      website="johan.com"

RUN echo "Hello world" > hello.txt

# ARG JAR_FILE=*.jar
ARG JAR_FILE=product-service-h2.jar

COPY ${JAR_FILE} product-service.jar

ENTRYPOINT ["java", "-jar", "product-service.jar"]

EXPOSE 8085