FROM openjdk:17-jdk-slim
WORKDIR /app
COPY PetServer.java .
RUN javac PetServer.java
EXPOSE 8080
CMD ["java","PetServer"]
