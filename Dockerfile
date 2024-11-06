# Dockerfile

# Etapa 1: Construcción usando Maven con Java 21
FROM maven:3.9.5-eclipse-temurin-21 AS build
WORKDIR /build

# Copiar archivos pom.xml y src al contenedor
COPY pom.xml .
COPY src ./src

# Compilar el proyecto, ejecutar pruebas y empaquetar el ejecutable
RUN mvn clean verify

# Etapa 2: Preparar la imagen de ejecución con Java 21
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copiar solo el JAR de la etapa de construcción a la etapa de ejecución
COPY --from=build /build/target/fintrack-0.0.1-SNAPSHOT.jar /app/fintrack.jar

EXPOSE 80

# Activar el perfil `docker` al iniciar el contenedor

CMD ["java", "-jar", "fintrack.jar", "--spring.profiles.active=docker"]
