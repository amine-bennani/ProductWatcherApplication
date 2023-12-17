# Use OpenJDK 17 as the base image
FROM adoptopenjdk:17-jdk-hotspot

# Set the working directory
WORKDIR /app

# Copy the JAR file into the container at /app
COPY build/libs/product-watcher.jar /app/product-watcher.jar

# Expose the port your application runs on
EXPOSE 8080

# Specify the command to run on container start
CMD ["java", "-jar", "product-watcher.jar"]
