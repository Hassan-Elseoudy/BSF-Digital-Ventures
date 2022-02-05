FROM maven:3.8.4-openjdk-17
WORKDIR /root
COPY . .
RUN mvn clean install -Dmaven.test.skip=true
CMD mvn spring-boot:run -Dmaven.test.skip=true