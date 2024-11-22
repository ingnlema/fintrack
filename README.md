
# Fintrack

Fintrack es una aplicación para la gestión de finanzas personales. Este repositorio contiene el código fuente y las configuraciones necesarias para ejecutar el proyecto localmente utilizando Docker Compose o directamente desde un IDE.

## Requisitos previos

Antes de comenzar, asegúrate de tener las siguientes herramientas instaladas en tu máquina:

- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/)
- [Java JDK 17+](https://adoptium.net/) (para ejecutar desde un IDE)
- [Maven](https://maven.apache.org/) (opcional, para gestión de dependencias)

## Opciones para levantar el proyecto

### Opción 1: Usar Docker Compose

1. **Clonar el repositorio**

   Clona este repositorio en tu máquina local:

   ```bash
   git clone <URL_DEL_REPOSITORIO>
   cd fintrack
   ```

2. **Configuración del entorno**

   El proyecto utiliza un archivo `docker-compose.yml` para levantar la aplicación y la base de datos PostgreSQL. Este archivo ya está configurado para entornos locales.

3. **Levantar los servicios con Docker Compose**

   Ejecuta el siguiente comando para construir y levantar los servicios:

   ```bash
   docker-compose up --build
   ```

   Este comando:
   - Construirá la imagen de la aplicación.
   - Levantará un contenedor de PostgreSQL como base de datos.
   - Levantará un contenedor con la aplicación Spring Boot.

4. **Acceso a la aplicación**

   Una vez que los servicios estén corriendo, accede a los siguientes recursos:

   - **Aplicación principal:** [http://localhost:8080](http://localhost:8080)
   - **Documentación Swagger:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

5. **Conexión a la base de datos**

   Para conectarte a la base de datos PostgreSQL, utiliza las siguientes credenciales:

   - **Host:** `localhost`
   - **Puerto:** `5432`
   - **Base de datos:** `fintrackdb`
   - **Usuario:** `admin`
   - **Contraseña:** `admin`

   Puedes usar herramientas como [DBeaver](https://dbeaver.io/) o la línea de comandos:

   ```bash
   psql -h localhost -p 5432 -U admin -d fintrackdb
   ```

6. **Ver logs**

   Para monitorear los logs de la aplicación:

   ```bash
   docker-compose logs -f
   ```

7. **Apagar los servicios**

   Para detener y eliminar los contenedores:

   ```bash
   docker-compose down
   ```

   Si deseas eliminar los volúmenes de la base de datos:

   ```bash
   docker-compose down -v
   ```

### Opción 2: Ejecutar desde un IDE

1. **Clonar el repositorio**

   Clona este repositorio en tu máquina local:

   ```bash
   git clone <URL_DEL_REPOSITORIO>
   cd fintrack
   ```

2. **Configuración del entorno**

   Para ejecutar localmente desde un IDE, la aplicación utiliza H2 como base de datos en memoria. Asegúrate de que el perfil `local` esté activo en el archivo `application-local.properties`, ubicado en:

   ```
   src/main/resources/application-local.properties
   ```

   Configuración relevante para H2:
   ```properties
   spring.datasource.url=jdbc:h2:mem:testdb
   spring.datasource.username=sa
   spring.datasource.password=
   spring.h2.console.enabled=true
   ```

3. **Ejecutar la aplicación**

   Abre el proyecto en tu IDE preferido (IntelliJ IDEA, Eclipse, etc.) y ejecuta la clase principal:

   ```
   src/main/java/com/ucu/fintrack/FintrackApplication.java
   ```

4. **Acceso a la aplicación**

   Una vez que la aplicación esté corriendo, accede a los siguientes recursos:

   - **Aplicación principal:** [http://localhost:8080](http://localhost:8080)
   - **Documentación Swagger:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
   - **Consola H2:** [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

   Credenciales para la consola H2:
   - **JDBC URL:** `jdbc:h2:mem:testdb`
   - **User Name:** `sa`
   - **Password:** *(deja vacío)*

5. **Ejecutar consultas en H2**

   Desde la consola H2, puedes ejecutar consultas SQL. Por ejemplo:

   ```sql
   SELECT * FROM banks;
   SELECT * FROM users;
   ```

## Troubleshooting

- Si el puerto 8080 está en uso, detén el proceso que lo está utilizando o configura un puerto diferente en los archivos de propiedades (`application-local.properties` o `docker-compose.yml`).
- Para cambios en el código fuente, recuerda reconstruir la imagen de Docker con:

  ```bash
  docker-compose up --build
  ```

