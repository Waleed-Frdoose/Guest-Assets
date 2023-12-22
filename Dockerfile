FROM openjdk:17-slim as build

#Add Maintainer Info
#LABEL maintainer="your-email@example.com"

#Make port 8085 available to the world outside this container
#EXPOSE 9191

COPY . /oad/osoolAlDeyafah

WORKDIR /oad/osoolAlDeyafah

#Install Maven
RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/*

#Use Maven to build the application
RUN mvn clean install

#Start the application
ENTRYPOINT ["java", "-jar", "target/osoolAlDeyafah-0.0.1-SNAPSHOT.jar"]