
# Fintrack

Fintrack es una aplicación para la gestión de finanzas personales. Este repositorio contiene el código fuente y las configuraciones necesarias para ejecutar el proyecto localmente utilizando Docker Compose.

## Requisitos previos

Antes de comenzar, asegúrate de tener las siguientes herramientas instaladas en tu máquina:

- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/)

## Pasos para levantar el proyecto localmente

1. **Clonar el repositorio**

   Clona este repositorio en tu máquina local:

   ```bash
   git clone <URL_DEL_REPOSITORIO>
   cd fintrack
   ```

2. **Configuración del entorno**

   El proyecto ya incluye configuraciones predefinidas para entornos locales en el archivo `application-local.properties`. No es necesario realizar modificaciones adicionales a menos que desees personalizar las propiedades.

3. **Levantar los servicios con Docker Compose**

   Ejecuta el siguiente comando para levantar los servicios definidos en el archivo `docker-compose.yml`:

   ```bash
   docker-compose up --build
   ```

   Este comando hará lo siguiente:
    - Construirá la imagen de la aplicación.
    - Levantará un contenedor de PostgreSQL para la base de datos.
    - Levantará un contenedor con la aplicación Spring Boot.

4. **Acceso a la aplicación**

   Una vez que los servicios estén corriendo, puedes acceder a los siguientes recursos:

    - **Aplicación principal:** [http://localhost:8080](http://localhost:8080)
    - **Documentación Swagger:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

5. **Conexión a la base de datos**

   Si necesitas conectarte a la base de datos PostgreSQL para depuración o consultas, utiliza las siguientes credenciales:

    - **Host:** `localhost`
    - **Puerto:** `5432`
    - **Base de datos:** `fintrackdb`
    - **Usuario:** `admin`
    - **Contraseña:** `admin`

   Puedes usar herramientas como [DBeaver](https://dbeaver.io/), [pgAdmin](https://www.pgadmin.org/), o la línea de comandos:

   ```bash
   psql -h localhost -p 5432 -U admin -d fintrackdb
   ```

6. **Ver logs**

   Para monitorear los logs de la aplicación, utiliza:

   ```bash
   docker-compose logs -f
   ```

7. **Apagar los servicios**

   Para detener y eliminar los contenedores levantados, ejecuta:

   ```bash
   docker-compose down
   ```

   Si deseas eliminar también los volúmenes de la base de datos para un reinicio limpio, usa:

   ```bash
   docker-compose down -v
   ```

## Troubleshooting

- Si encuentras problemas con las tablas de la base de datos, asegúrate de que los scripts de inicialización no generen conflictos.
- Si realizas cambios en el código fuente, recuerda reconstruir la imagen de Docker utilizando:

  ```bash
  docker-compose up --build
  ```


