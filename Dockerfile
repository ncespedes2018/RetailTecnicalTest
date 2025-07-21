# Use a full JDK 21 image (build and runtime together)
FROM eclipse-temurin:21-jdk

# Set working directory inside container
WORKDIR /app

# Copy Gradle wrapper and project files
COPY gradlew .
COPY gradlew.bat .
COPY gradle gradle
COPY . .

# Give execution permission to Gradle wrapper
RUN chmod +x gradlew

# Build the application (skip tests to speed up)
RUN ./gradlew clean build -x test

# Optional: define environment variables
ENV JAVA_OPTS="\
  -XX:+UseContainerSupport \
  -XX:+UseG1GC \
  -XX:+UseStringDeduplication \
  -XX:MaxRAMPercentage=75.0 \
  -XX:InitialRAMPercentage=40.0 \
  -XX:+ExitOnOutOfMemoryError \
  -Djava.security.egd=file:/dev/./urandom \
  -Dspring.profiles.active=default"

# Expose Spring Boot default port
EXPOSE 8080

# Run the Spring Boot app
CMD ["sh", "-c", "java $JAVA_OPTS -jar build/libs/Products-0.0.1-SNAPSHOT.jar"]
