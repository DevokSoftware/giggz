# Stage 1: Build application
FROM gradle:8.6.0-jdk17-alpine AS build
WORKDIR /giggz
COPY . .
RUN chmod +x ./gradlew
RUN ./gradlew clean build

# Stage 2: Create final image
FROM gradle:8.6.0-jdk17-alpine
WORKDIR /giggz
COPY --from=build /giggz/giggz-application/build/libs/giggz-application.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]