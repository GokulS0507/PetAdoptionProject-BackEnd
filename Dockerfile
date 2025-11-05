# Use stable and small JDK image
FROM eclipse-temurin:17-jdk-jammy

# Set working directory
WORKDIR /app

# Copy server code
COPY PetServer.java .

# Compile code
RUN javac PetServer.java

# Expose port for Render
EXPOSE 8080
ENV PORT=8080

# Run Java server
CMD ["java", "PetServer"]
